package snake;

//Class in Java which produces pseudo-random numbers. This allows for the Random class to be accessed and used within this class.
import java.util.Random;

//What this class will do is generate random questions along with corresponding correct and incorrect answers.
public class Questions {

	//	Array for the verbalised questions. The type is set as String of array size 3.
	private String[] question = new String[3];

	// 	This array is used for various values throughout calculation. This is used to store and access these values.
	//	For the array below, the structure is:
	//	-	[0] - num1
	//	-	[1]	- num2
	//	-	[2] - correct answer
	//	-	[3] - incorrect answer
	//	-	[4]	- incorrect answer
	//	-	[5] - num3
	//	-	[6] - first answer in brackets for 2 operand questions
	private int[] values = new int[7];

	//	A random number generator. This uses the random method.
	private Random rand = new Random();

	//	An array of the possible operators that can be used, this consists of 2 of the 4 chosen mathematical operations (+ and -). This is used for the easy difficulty.
	private String[] easyOps = { "+", "-" };

	//	An array of the possible operators that can be used, this consists of 2 of the 4 chosen mathematical operations (/ and *). This is used for the medium difficulty.
	private String[] mediumOps = { "x", "÷" };

	//	An array of the possible operators that can be used, this consists of all 4 of the chosen mathematical operations (+, -, /, *). This is used for the hard difficulty
	private String[] hardOps = { "+", "-", "x", "÷" };

	//This is to ensure that we do not get a decimal value when dividing (meaning we get an integer value). The children will only work with integer values.
	private int wholeDivision;

	//The first operator selected
	private String theOp;

	//The second operator selected
	private String theOp2;

	//This is the randomly selected question which is returned.
	private static String theQ;

	//This is used for choosing the difficulty.
	//Where 0 = Easy: this features addition and subtraction.
	//Where 1 = Medium: this features multiplication and division.
	//Where 2 = Hard: this features addition, subtraction, multiplication and division.
	public static int difficultyChosen;

	//This is the number limit. This controls the maximum value in which any of the number terms within the question can take. The initial value is set at 5.
	public static int numLimit;
	//This is the number limit for the 2 operand questions
	public static int numLimit2;

	//This is the constructor of the class.
	public Questions() {

		//Selects the difficulty based on the difficulty chosen
		switch (difficultyChosen) {
		//Easy difficulty
		case 0:
			easyDifficulty();
			break;
		//Medium difficulty
		case 1:
			mediumDifficulty();
			break;
		//Hard difficulty
		case 2:
			hardDifficulty();
			break;
		}
	}

	//	Method to return the verbalised question. This is set as public so other classes can access it.
	public String getQuestion() {
		return theQ;
	}

	//	Method to return the correct answer for a question. This is set as public so other classes can access it.
	public int getCorrect() {
		return values[2];
	}

	//Method to generate a false answer based on the correct answer. This value is then returned. This is set as public so other classes can access it.
	public int generateIncorrect() {
		//Declaring and initialising the variable Incorrect
		int incorrect = 0;
		//This decides whether to add or subtract from the correct answer to get an incorrect answer
		int addSub = rand.nextInt(2);
		//This is used for addition or subtraction
		int randNum;
		//This will determine whether to use addition or subtraction
		if (addSub == 0) {
			//Use addition
			randNum = rand.nextInt(70) + 1;
		} else {
			//Use subtraction
			randNum = -(rand.nextInt(70) + 1);
		}

		//Takes the correct value for the question and adds or subtracts a random number between 1-70 to get the incorrect value
		incorrect = getCorrect() + randNum;

		//Returns the incorrect value
		return incorrect;
	}

	//This is the easy difficulty setting. This setting includes 2 of the 4  chosen mathematical operations (+ and -), this is chosen when difficultyChosen = 0.
	public void easyDifficulty() {
		//	Assign three random values as the numbers for the equation. This random number can be between 1 to the numLimit.
		values[0] = rand.nextInt(numLimit) + 1;
		values[1] = rand.nextInt(numLimit) + 1;
		values[5] = rand.nextInt(numLimit) + 1;

		//	Randomly choose an operator to use
		theOp = easyOps[rand.nextInt(2)];
		theOp2 = easyOps[rand.nextInt(2)];

		//This is used to present a chance for a 2 operand - 3 number question to appear. Assigns a number from 0-4 to test20, this will give a 2 operand question a 20% chance of appearing.
		int test20 = rand.nextInt(5);

		//Checks if test20 has a value equal to the value of 0. This is done because we need a 20% chance that there will be a 2 operand - 3 number question. If the condition is met, the question will be set as a 3 operand question.
		if (test20 == 0) {

			//To make the 2 operand questions easier, I will be dividing the number limit by 2 and rounding to the nearest 10 to get the new numLimit2.
			numLimit2 = ((numLimit / 2) + 5) / 10 * 10;

			//If the number limit is 5 for the 2 operand 3 number question, keep the number limit at 5.
			if (numLimit == 5) {
				//	Assign four random values as the numbers for the equation. This random number can be between 1 to the numLimit.
				values[0] = rand.nextInt(numLimit) + 1;
				values[1] = rand.nextInt(numLimit) + 1;
				values[5] = rand.nextInt(numLimit) + 1;
			} else {
				//Assigns values based on the new numLimit2.
				values[0] = rand.nextInt(numLimit2) + 1;
				values[1] = rand.nextInt(numLimit2) + 1;
				values[5] = rand.nextInt(numLimit2) + 1;
			}

			//This is the test which is done if test20 is equal to the number 0. What this does is it selects the first random operand between the two numbers, this will then be assigned to values[6], this effectively acts as a bracket, it performs the first operation before the second (this will come in the next switch statement).
			switch (theOp) {
			case "+":
				//values[6] is equal to the first and second value added together.
				values[6] = values[0] + values[1];
				break;
			case "-":
				//values[6] is equal to the first minus the second value.
				values[6] = values[0] - values[1];
				break;
			}

			//This next switch statement calculates the correct answer. What this does is it takes values[6] and then 'puts' the operator and 3rd random number into that, this means that we effectively have calculates the first 2 numbers first and then included the 3rd number after to generate the correct answer.
			switch (theOp2) {
			case "+":
				//Correct value is equal to the first and second value added together. These first two numbers are placed in brackets followed by the second operand and the third number.
				values[2] = values[6] + values[5];
				break;
			case "-":
				//Correct value is equal to the first minus the second value. These first two numbers are placed in brackets followed by the second operand and the third number.
				values[2] = values[6] - values[5];
				break;
			}

			// This question is the question which appears if the question is chosen to be a 2 operand - 3 number question.
			// 	Format is:	“What would [(FIRST NUMBER) (FIRST OPERAND) (SECOND NUMBER)] (SECOND OPERAND) (THIRD NUMBER) be equal to?”
			//	Assigning the value of the variable theQ.
			theQ = "What would (" + values[0] + " " + theOp + " " + values[1]
					+ ") " + theOp2 + " " + values[5] + " be equal to?";

			//If test20 does not equal 0, it instead performs the code below. This code uses only 2 numbers and one operation.	
		} else {
			//	This compares theOp to the cases available. This means that dependent on the operator, this generates the correct answer. The breaks ends the switch statement, meaning that it does not run through all of the cases.
			switch (theOp) {
			case "+":
				//Correct value is equal to the first and second value added together
				values[2] = values[0] + values[1];
				break;
			case "-":
				//Correct value is equal to the first minus the second value
				values[2] = values[0] - values[1];
				break;
			}

			// For the array below, the structure is:
			// 	- 	[0] - "What is (MATHEMATICAL QUESTION) equal to?"
			// 	- 	[1] - "What is the result of (MATHEMATICAL QUESTION)?"
			// 	- 	[2] - "(MATHEMATICAL QUESTION) is equal to what?"
			//Where (MATHEMATICAL QUESTION) refers to a randomly generated mathematical question based on the difficulty selected. 
			question[0] = "What is " + values[0] + " " + theOp + " "
					+ values[1] + " equal to?";
			question[1] = "What is the result of " + values[0] + " " + theOp
					+ " " + values[1] + " ?";
			question[2] = values[0] + " " + theOp + " " + values[1]
					+ " is equal to what?";
			//Assigning the value of the variable theQ (the selected question) as a randomly selected selected question.
			theQ = question[rand.nextInt(3)];
		}
	}

	//This is the medium difficulty setting. This setting includes 2 of the 4 chosen mathematical operations (/ and *), this is chosen when difficultyChosen = 1.
	public void mediumDifficulty() {
		//	Assign three random values as the numbers for the equation. This random number can be between 1 to the numLimit.
		values[0] = rand.nextInt(numLimit) + 1;
		values[1] = rand.nextInt(numLimit) + 1;
		values[5] = rand.nextInt(numLimit) + 1;

		//	Randomly choose an operator to use
		theOp = mediumOps[rand.nextInt(2)];
		theOp2 = mediumOps[rand.nextInt(2)];

		//This is used to present a chance for a 2 operand - 3 number question to appear. Assigns a number from 0-4 to test20, this will give a 2 operand question a 20% chance of appearing.
		int test20 = rand.nextInt(5);

		//Checks if test20 has a value equal to the value of 0. This is done because we need a 20% chance that there will be a 2 operand - 3 number question. If the condition is met, the question will be set as a 3 operand question.
		if (test20 == 0) {

			//To make the 2 operand questions easier, I will be dividing the number limit by 2 and rounding to the nearest 10 to get the new numLimit2.
			numLimit2 = ((numLimit / 2) + 5) / 10 * 10;

			//If the number limit is 5 for the 2 operand 3 number question, keep the number limit at 5.
			if (numLimit == 5) {
				//	Assign four random values as the numbers for the equation. This random number can be between 1 to the numLimit.
				values[0] = rand.nextInt(numLimit) + 1;
				values[1] = rand.nextInt(numLimit) + 1;
				values[5] = rand.nextInt(numLimit) + 1;
				wholeDivision = rand.nextInt(numLimit) + 1;
			} else {
				//Assigns values based on the new numLimit2.
				values[0] = rand.nextInt(numLimit2) + 1;
				values[1] = rand.nextInt(numLimit2) + 1;
				values[5] = rand.nextInt(numLimit2) + 1;
				wholeDivision = rand.nextInt(numLimit2) + 1;
			}

			//This is the test which is done if test20 is equal to the number 0. What this does is it selects the first random operand between the two numbers, this will then be assigned to values[6], this effectively acts as a bracket, it performs the first operation before the second (this will come in the next switch statement).
			switch (theOp) {
			case "x":
				//values[6] is equal to the first multiplied by the second value. These first two numbers are placed in brackets followed by the second operand and the third number.
				values[6] = values[0] * values[1];
				break;
			case "÷":
				//We assign the values[0] to the number which will be divided by values[1].
				values[0] = wholeDivision;

				//If the number divided is 1
				if (values[0] == 1) {
					//Number to divide is set to 1
					values[1] = values[0];
				}
				//If the number which is dividing is more than the number to be divided (this will prevent a whole number division)
				if (values[1] > values[0]) {
					//While dividing number is larger
					while (values[1] > values[0]) {
						//If the numLimit is 5 (in other words numLimit2 is zero)
						if (numLimit2 == 0) {
							//Create a new random number within the numLimit and assign it to the dividing variables values[1]
							values[1] = rand.nextInt(numLimit) + 1;
						} else {
							//Create a new random number within the numLimit2 and assign it to the dividing variables values[1]
							values[1] = rand.nextInt(numLimit2) + 1;
						}
					}
					//We are using the modulus here (represented by the %), this is the remainder of a integer division, leaving an integer remainder (the number of whole times a number divides into another). What we are testing is the modulus of values[0]/values[1], in other words, this gives us the remainder of a whole number division. What this means is that if the remainder isn't zero, it hasn't divided completely into an integer value. As integer values are required, we use a loop to increment the values[1] until we get a modulus of zero. 
					while (values[0] % values[1] != 0) {
						//Increments values[1]
						values[1]++;
					}
				} else {
					//We are using the modulus here (represented by the %), this is the remainder of a integer division, leaving an integer remainder (the number of whole times a number divides into another). What we are testing is the modulus of values[0]/values[1], in other words, this gives us the remainder of a whole number division. What this means is that if the remainder isn't zero, it hasn't divided completely into an integer value. As integer values are required, we use a loop to increment the values[1] until we get a modulus of zero. 
					while (values[0] % values[1] != 0) {
						//Increments values[1]
						values[1]++;
					}
				}
				//values[6] is equal to values[0] / values[1]
				values[6] = values[0] / values[1];
				break;
			}

			//This next switch statement calculates the correct answer. What this does is it takes values[6] and then 'puts' the operator and 3rd random number into that, this means that we effectively have calculates the first 2 numbers first and then included the 3rd number after to generate the correct answer.
			switch (theOp2) {
			case "x":
				//Correct value is equal to the first multiplied by the second value. These first two numbers are placed in brackets followed by the second operand and the third number.
				values[2] = values[6] * values[5];
				break;
			case "÷":
				//Calls the divisionTheOp2 method
				divisionTheOp2();
				//If the numLimit is equal to 5
				if (numLimit == 5) {
					//While the division values[5] is larger than numLimit (so that all the numbers in the question are below the numLimit)
					while (values[5] > numLimit) {
						//testing if values[5] is more than numLimit
						if (values[5] > numLimit) {
							values[5] = rand.nextInt(numLimit) + 1;
						}
						//Call the method
						divisionTheOp2();
					}
				} else {
					while (values[5] > numLimit2) {
						if (values[5] > numLimit2) {
							values[5] = rand.nextInt(numLimit2) + 1;
						}
						divisionTheOp2();
					}
				}
				//the correct answer is equal to the multiplication of the two numbers divided by the second number, this will always ensure that we are left with an integer value.. These first two numbers are placed in brackets followed by the second operand and the third number.
				values[2] = values[6] / values[5];
				break;
			}

			// This question is the question which appears if the question is chosen to be a 2 operand - 3 number question.
			// 	Format is:	“What would [(FIRST NUMBER) (FIRST OPERAND) (SECOND NUMBER)] (SECOND OPERAND) (THIRD NUMBER) be equal to?”
			//	Assigning the value of the variable theQ.
			theQ = "What would (" + values[0] + " " + theOp + " " + values[1]
					+ ") " + theOp2 + " " + values[5] + " be equal to?";

			//If test20 does not equal 0, it instead performs the code below. This code uses only 2 numbers and one operation.	
		} else {
			//	This compares theOp to the cases available. This means that dependent on the operator, this generates the correct answer. The breaks ends the switch statement, meaning that it does not run through all of the cases.
			switch (theOp) {
			case "x":
				//Correct value is equal to the first multiplied by the second value
				values[2] = values[0] * values[1];
				break;
			case "÷":
				//This gives the wholeDivision a value which can be used
				wholeDivision = rand.nextInt(numLimit) + 1;
				//We assign the values[0] to the number which will be divided by values[1].
				values[0] = wholeDivision;
				//If the number to be divided is 1
				if (values[0] == 1) {
					//Number to divide is set to 1
					values[1] = values[0];
				}
				//While dividing number is larger
				while (values[1] > values[0]) {
					//Create a new random number within the numLimit and assign it to the dividing variables values[1]
					values[1] = rand.nextInt(numLimit) + 1;
				}
				//We are using the modulus here (represented by the %), this is the remainder of a integer division, leaving an integer remainder (the number of whole times a number divides into another). What we are testing is the modulus of values[0]/values[1], in other words, this gives us the remainder of a whole number division. What this means is that if the remainder isn't zero, it hasn't divided completely into an integer value. As integer values are required, we use a loop to increment the values[1] until we get a modulus of zero. 
				while (values[0] % values[1] != 0) {
					//Increments values[1]
					values[1]++;
				}
				//values[2] is equal to values[0] / values[1]
				values[2] = values[0] / values[1];
				break;
			}

			// For the array below, the structure is:
			// 	- 	[0] - "What is (MATHEMATICAL QUESTION) equal to?"
			// 	- 	[1] - "What is the result of (MATHEMATICAL QUESTION)?"
			// 	- 	[2] - "(MATHEMATICAL QUESTION) is equal to what?"
			//Where (MATHEMATICAL QUESTION) refers to a randomly generated mathematical question based on the difficulty selected. 
			question[0] = "What is " + values[0] + " " + theOp + " "
					+ values[1] + " equal to?";
			question[1] = "What is the result of " + values[0] + " " + theOp
					+ " " + values[1] + " ?";
			question[2] = values[0] + " " + theOp + " " + values[1]
					+ " is equal to what?";
			//Assigning the value of the variable theQ (the selected question) as a randomly selected selected question.
			theQ = question[rand.nextInt(3)];
		}
	}

	//This is the hard difficulty setting. This setting includes all 4 of the chosen mathematical operations (+, -, /, *), this is chosen when difficultyChosen = 2.
	public void hardDifficulty() {
		//	Assign three random values as the numbers for the equation. This random number can be between 1 to the numLimit.
		values[0] = rand.nextInt(numLimit) + 1;
		values[1] = rand.nextInt(numLimit) + 1;
		values[5] = rand.nextInt(numLimit) + 1;

		//	Randomly choose an operator to use
		theOp = hardOps[rand.nextInt(4)];
		theOp2 = hardOps[rand.nextInt(4)];

		//This is used to present a chance for a 2 operand - 3 number question to appear. Assigns a number from 0-4 to test20, this will give a 2 operand question a 20% chance of appearing.
		int test20 = rand.nextInt(5);

		//Checks if test20 has a value equal to the value of 0. This is done because we need a 20% chance that there will be a 2 operand - 3 number question. If the condition is met, the question will be set as a 3 operand question.
		if (test20 == 0) {

			//To make the 2 operand questions easier, I will be dividing the number limit by 2 and rounding to the nearest 10 to get the new numLimit2.
			numLimit2 = ((numLimit / 2) + 5) / 10 * 10;

			//If the number limit is 5 for the 2 operand 3 number question, keep the number limit at 5.
			if (numLimit == 5) {
				//	Assign four random values as the numbers for the equation. This random number can be between 1 to the numLimit.
				values[0] = rand.nextInt(numLimit) + 1;
				values[1] = rand.nextInt(numLimit) + 1;
				values[5] = rand.nextInt(numLimit) + 1;
				wholeDivision = rand.nextInt(numLimit) + 1;
			} else {
				//Assigns values based on the new numLimit2.
				values[0] = rand.nextInt(numLimit2) + 1;
				values[1] = rand.nextInt(numLimit2) + 1;
				values[5] = rand.nextInt(numLimit2) + 1;
				wholeDivision = rand.nextInt(numLimit2) + 1;
			}

			//This is the test which is done if test20 is equal to the number 0. What this does is it selects the first random operand between the two numbers, this will then be assigned to values[6], this effectively acts as a bracket, it performs the first operation before the second (this will come in the next switch statement).
			switch (theOp) {
			case "+":
				//values[6] is equal to the first and second value added together.
				values[6] = values[0] + values[1];
				break;
			case "-":
				//values[6] is equal to the first minus the second value.

				values[6] = values[0] - values[1];
				break;
			case "x":
				//values[6] is equal to the first multiplied by the second value. These first two numbers are placed in brackets followed by the second operand and the third number.
				values[6] = values[0] * values[1];
				break;
			case "÷":
				//We assign the values[0] to the number which will be divided by values[1].
				values[0] = wholeDivision;

				//If the number divided is 1
				if (values[0] == 1) {
					//Number to divide is set to 1
					values[1] = values[0];
				}

				//If the number which is dividing is more than the number to be divided (this will prevent a whole number division)
				if (values[1] > values[0]) {
					//While dividing number is larger
					while (values[1] > values[0]) {
						//If the numLimit is 5 (in other words numLimit2 is zero)
						if (numLimit2 == 0) {
							//Create a new random number within the numLimit and assign it to the dividing variables values[1]
							values[1] = rand.nextInt(numLimit) + 1;
						} else {
							//Create a new random number within the numLimit2 and assign it to the dividing variables values[1]
							values[1] = rand.nextInt(numLimit2) + 1;
						}
					}
					//We are using the modulus here (represented by the %), this is the remainder of a integer division, leaving an integer remainder (the number of whole times a number divides into another). What we are testing is the modulus of values[0]/values[1], in other words, this gives us the remainder of a whole number division. What this means is that if the remainder isn't zero, it hasn't divided completely into an integer value. As integer values are required, we use a loop to increment the values[1] until we get a modulus of zero. 
					while (values[0] % values[1] != 0) {
						//Increments values[1]
						values[1]++;
					}
				} else {
					//We are using the modulus here (represented by the %), this is the remainder of a integer division, leaving an integer remainder (the number of whole times a number divides into another). What we are testing is the modulus of values[0]/values[1], in other words, this gives us the remainder of a whole number division. What this means is that if the remainder isn't zero, it hasn't divided completely into an integer value. As integer values are required, we use a loop to increment the values[1] until we get a modulus of zero. 
					while (values[0] % values[1] != 0) {
						//Increments values[1]
						values[1]++;
					}
				}
				//values[6] is equal to values[0] / values[1]
				values[6] = values[0] / values[1];

				break;
			}

			//This next switch statement calculates the correct answer. What this does is it takes values[6] and then 'puts' the operator and 3rd random number into that, this means that we effectively have calculates the first 2 numbers first and then included the 3rd number after to generate the correct answer.
			switch (theOp2) {
			case "+":
				//Correct value is equal to the first and second value added together. These first two numbers are placed in brackets followed by the second operand and the third number.
				values[2] = values[6] + values[5];
				break;
			case "-":
				//Correct value is equal to the first minus the second value. These first two numbers are placed in brackets followed by the second operand and the third number.
				values[2] = values[6] - values[5];
				break;
			case "x":
				//Correct value is equal to the first multiplied by the second value. These first two numbers are placed in brackets followed by the second operand and the third number.
				values[2] = values[6] * values[5];
				break;
			case "÷":

				//Calls the divisionTheOp2 method
				divisionTheOp2();

				//If the numLimit is equal to 5
				if (numLimit == 5) {
					//While the division values[5] is larger than numLimit (so that all the numbers in the question are below the numLimit)
					while (values[5] > numLimit) {
						//testing if values[5] is more than numLimit
						if (values[5] > numLimit) {
							values[5] = rand.nextInt(numLimit) + 1;
						}
						//Call the method
						divisionTheOp2();
					}
				} else {
					//While the division values[5] is larger than numLimit2
					while (values[5] > numLimit2) {
						//Checking again whether it is now smaller than
						if (values[5] > numLimit2) {
							//If it is larger, a random number is produced and the while loop continues.
							values[5] = rand.nextInt(numLimit2) + 1;
						}
						//calls the method
						divisionTheOp2();
					}
				}

				//the correct answer is equal to the multiplication of the two numbers divided by the second number, this will always ensure that we are left with an integer value.. These first two numbers are placed in brackets followed by the second operand and the third number.
				values[2] = values[6] / values[5];
				break;
			}

			// This question is the question which appears if the question is chosen to be a 2 operand - 3 number question.
			// 	Format is:	“What would [(FIRST NUMBER) (FIRST OPERAND) (SECOND NUMBER)] (SECOND OPERAND) (THIRD NUMBER) be equal to?”
			//	Assigning the value of the variable theQ.
			theQ = "What would (" + values[0] + " " + theOp + " " + values[1]
					+ ") " + theOp2 + " " + values[5] + " be equal to?";

			//If test20 does not equal 0, it instead performs the code below. This code uses only 2 numbers and one operation.	
		} else {
			//	This compares theOp to the cases available. This means that dependent on the operator, this generates the correct answer. The breaks ends the switch statement, meaning that it does not run through all of the cases.
			switch (theOp) {
			case "+":
				//Correct value is equal to the first and second value added together
				values[2] = values[0] + values[1];
				break;
			case "-":
				//Correct value is equal to the first minus the second value
				values[2] = values[0] - values[1];
				break;
			case "x":
				//Correct value is equal to the first multiplied by the second value
				values[2] = values[0] * values[1];
				break;
			case "÷":

				//This gives the wholeDivision a value which can be used
				wholeDivision = rand.nextInt(numLimit) + 1;

				//We assign the values[0] to the number which will be divided by values[1].
				values[0] = wholeDivision;

				//If the number to be divided is 1
				if (values[0] == 1) {
					//Number to divide is set to 1
					values[1] = values[0];
				}

				//While dividing number is larger
				while (values[1] > values[0]) {
					//Create a new random number within the numLimit and assign it to the dividing variables values[1]
					values[1] = rand.nextInt(numLimit) + 1;
				}

				//We are using the modulus here (represented by the %), this is the remainder of a integer division, leaving an integer remainder (the number of whole times a number divides into another). What we are testing is the modulus of values[0]/values[1], in other words, this gives us the remainder of a whole number division. What this means is that if the remainder isn't zero, it hasn't divided completely into an integer value. As integer values are required, we use a loop to increment the values[1] until we get a modulus of zero. 
				while (values[0] % values[1] != 0) {
					//Increments values[1]
					values[1]++;
				}

				//values[2] is equal to values[0] / values[1]
				values[2] = values[0] / values[1];
				break;
			}

			// For the array below, the structure is:
			// 	- 	[0] - "What is (MATHEMATICAL QUESTION) equal to?"
			// 	- 	[1] - "What is the result of (MATHEMATICAL QUESTION)?"
			// 	- 	[2] - "(MATHEMATICAL QUESTION) is equal to what?"
			//Where (MATHEMATICAL QUESTION) refers to a randomly generated mathematical question based on the difficulty selected. 
			question[0] = "What is " + values[0] + " " + theOp + " "
					+ values[1] + " equal to?";
			question[1] = "What is the result of " + values[0] + " " + theOp
					+ " " + values[1] + " ?";
			question[2] = values[0] + " " + theOp + " " + values[1]
					+ " is equal to what?";
			//Assigning the value of the variable theQ (the selected question) as a randomly selected selected question.
			theQ = question[rand.nextInt(3)];
		}
	}

	//This method is only used for 2 operand - 3 number questions where the operand on the outside is a division. What this does is it ensures that values[5], the dividing number, will be altered until values[6] / values[5] gives an integer value
	public void divisionTheOp2() {
		//If the value of the calculation of values[6] renders the answer of 1 or -1, we must divide by 1 to get an integer value, this is why values[5] is set to 1.
		if (values[6] == 1 || values[6] == -1) {
			values[5] = 1;
		}

		//Checks if the absolute value of values[6] is less than the values[5] which it is going to be divided by. This will ensure that the value of values[6]/values[5] will always render an integer result.
		//We use absolute here as we need to compare the magnitude, for example, if we have a subtraction for the first operand, we may get a negative values[6], this means this condition will always be true, therefore an infinite loop.
		if (values[5] > Math.abs(values[6])) {
			//Run a while loop if values[5] -- the dividing number, is less than the absolute value of values[6] -- the number being divided. 
			while (values[5] > Math.abs(values[6])) {
				//Checks the numLimit to ensure we are using the correct value
				if (numLimit2 == 0) {
					//Creates a new random value for values[5] within the numLimit.
					values[5] = rand.nextInt(numLimit) + 1;
				} else
					//Creates a new random value for values[5] within the numLimit.
					values[5] = rand.nextInt(numLimit2) + 1;
			}

			//We are using the modulus here (represented by the %), this is the remainder of a integer division, leaving an integer remainder (the number of whole times a number divides into another). What we are testing is the modulus of values[6]/values[5], in other words, this gives us the remainder of a whole number division. What this means is that if the remainder isn't zero, it hasn't divided completely into an integer value. As integer values are required, we use a loop to increment the values[5] until we get a modulus of zero. 
			while (Math.abs(values[6]) % values[5] != 0) {
				//Increments values[5]
				values[5]++;
			}
		} else {
			//We are using the modulus here (represented by the %), this is the remainder of a integer division, leaving an integer remainder (the number of whole times a number divides into another). What we are testing is the modulus of values[6]/values[5], in other words, this gives us the remainder of a whole number division. What this means is that if the remainder isn't zero, it hasn't divided completely into an integer value. As integer values are required, we use a loop to increment the values[5] until we get a modulus of zero. 
			while (Math.abs(values[6]) % values[5] != 0) {
				//Increments values[5]
				values[5]++;
			}
		}
	}
}