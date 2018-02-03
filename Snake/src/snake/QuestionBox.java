package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
//This class is used as a component to fit on the JPanel, this will be where the Question Box is placed.
public class QuestionBox extends JPanel {

	//Declaring a new colour called 'blue'.
	public static Color blue = new Color(5263553);

	//Sets the dimension to a new dimension of (600, 100)
	public static Dimension dim2 = new Dimension(600, 120);

	//This overrides the getPrefferedSize method, this sets the PrefferedSize as returning a dimension of (600, 100).
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(dim2.width, dim2.height);
	}

	//Setting a font of small as decided in the specification
	public static Font small = new Font("Verdana", Font.PLAIN, 12);
	//Setting a font of medium as decided in the specification
	public static Font medium = new Font("Verdana", Font.PLAIN, 15);
	//Setting a font of large as decided in the specification
	public static Font large = new Font("Verdana", Font.PLAIN, 22);
	//Setting a font of title as decided in the specification
	public static Font title = new Font("Verdana", Font.BOLD, 40);

	public static String diffText;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Setting the colour in which graphics objects are made to the colour defined in the colour 'blue'
		g.setColor(blue);
		//Filling in a rectangle which has a width and height of (600, 120). This will be where the QuestionBox will be placed.
		g.fillRect(0, 0, dim2.width, dim2.height);

		//Setting colour to black
		g.setColor(Color.BLACK);

		String question = Board.question;
		//Sets the font to be of large style
		g.setFont(large);
		int stringLenQuestion = (int) g.getFontMetrics()
				.getStringBounds(question, g).getWidth();
		//Draw the string of 'question' in position (x, y)    
		g.drawString(question,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dim2.width / 2 - stringLenQuestion / 2), (int) 20);

		//Calls the method answers and has parameter Graphics g
		answers(g);

		//Difficulty in text
		diffText = null;

		//This gets the difficulty in text.
		switch (Questions.difficultyChosen) {
		//easy
		case 0:
			diffText = "Easy";
			break;
		//medium
		case 1:
			diffText = "Medium";
			break;
		//hard
		case 2:
			diffText = "Hard";
			break;
		}

		//Setting colour to black
		g.setColor(Color.BLACK);
		//The updating data in the QuestionBox – this includes the score, difficulty and players name.
		String data = "Score: " + Board.score + "     " + "Difficulty: "
				+ diffText + "     " + "Name: " + NameScreen.inName;
		//Sets the font to be of small size
		g.setFont(small);
		//Draw the string of 'data' in position (x, y)    
		g.drawString(data,
		//(int) is casted because drawString takes int values but not doubles.
				(int) 5, (int) dim2.height - 10);
	}

	//This method is used to get the string of the answers.
	public static void answers(Graphics g) {
		//This is used to store the string of the fruit
		String strFruit;
		//Red will be in middle, purple to the left of it and brown to the right
		//Declaring and initialising the answers variables. These will be used to output the various answers.
		int intRed = 0;
		int intBrown = 0;
		int intPurple = 0;

		//based on what the colour of fruit1 is
		switch (Board.colourSelected[0]) {
		//If red
		case 0:
			//Set the intRed to be the correct answer
			intRed = Board.corAns;
			break;
		//if brown
		case 1:
			//Set the intBrown to be the correct answer
			intBrown = Board.corAns;
			break;
		//if purple
		case 2:
			//Set the intPurple to be the correct answer
			intPurple = Board.corAns;
			break;
		}

		//based on what the colour of fruit2 is
		switch (Board.colourSelected[1]) {
		//If red
		case 0:
			//Set the intRed to be the first incorrect answer
			intRed = Board.incorAns1;
			break;
		//if brown
		case 1:
			//Set the intBrown to be the first incorrect answer
			intBrown = Board.incorAns1;
			break;
		//if purple
		case 2:
			//Set the intPurple to be the first incorrect answer
			intPurple = Board.incorAns1;
			break;
		}

		//based on what the colour of fruit3 is
		switch (Board.colourSelected[2]) {
		//If red
		case 0:
			//Set the intRed to be the second incorrect answer
			intRed = Board.incorAns2;
			break;
		//if brown
		case 1:
			//Set the intBrown to be the second incorrect answer
			intBrown = Board.incorAns2;
			break;
		//if purple
		case 2:
			//Set the intPurple to be the second incorrect answer
			intPurple = Board.incorAns2;
			break;
		}

		//Sets the string strFruit.
		strFruit = "[PURPLE] " + intPurple + "     " + "[RED] " + intRed
				+ "     " + "[BROWN] " + intBrown;
		//Setting colour to black
		g.setColor(Color.BLACK);
		//Sets the font to be of medium style
		g.setFont(medium);
		//Finding the width of the string
		int stringLenQuestion = (int) g.getFontMetrics()
				.getStringBounds(strFruit, g).getWidth();
		//Draw the string of 'strFruit' in position (x, y)    
		g.drawString(strFruit,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dim2.width / 2 - stringLenQuestion / 2), (int) 50);
	}
}