package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
//This class will draw the fruit which will be displayed on the board in perfect alignment.
public class Fruit extends JPanel {

	//Declaring the colour 'red' as the hex colour code (turned to decimal using a hex calculator so Java can use it) which was chosen in the design stage. This is used for the fruit.
	public static Color red = new Color(8005929);
	//Declaring the colour 'brown' as the hex colour code (turned to decimal using a hex calculator so Java can use it) which was chosen in the design stage. This is used for the fruit.
	public static Color brown = new Color(9067566);
	//Declaring the colour 'purple' as the hex colour code (turned to decimal using a hex calculator so Java can use it) which was chosen in the design stage. This is used for the fruit.
	public static Color purple = new Color(6684774);

	//Sets the dimension to a new dimension of (600, 600) -- the same size of the board.
	public static Dimension dim4 = new Dimension(600, 600);

	//This overrides the getPrefferedSize method, this sets the PrefferedSize as returning a dimension of (600, 20).
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(dim4.width, dim4.height);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Based upon the random selection of integer, the colour is assigned as follows.
		switch (Board.selectColour) {
		case 0:
			//This sets the colour selected to the colourSelected array. This will allow us to ensure that no 2 fruit are of the same colour, which will be done in the next 2 fruit where if statements are made. 
			//What this does is it assigns the value for the colour of the fruit1 to be the selectColour value. This will allow comparisons at a later point.
			Board.colourSelected[0] = Board.selectColour;
			//Sets the colour to red
			g.setColor(red);
			break;
		case 1:
			Board.colourSelected[0] = Board.selectColour;
			//Sets the colour to brown
			g.setColor(brown);
			break;
		case 2:
			Board.colourSelected[0] = Board.selectColour;
			//Sets the colour to purple
			g.setColor(purple);
			break;
		}

		//This draws the first fruit (containing correct answer).
		g.fillRect(Board.fruit1.x * Board.SCALE, Board.fruit1.y * Board.SCALE,
				Board.SCALE, Board.SCALE);

		//Checks what colour the fruit1 is, and based on that assigns a colour for fruit2
		switch (Board.colourSelected[0]) {
		//if red
		case 0:
			//Sets the value of the colour for fruit2 as being a random colour between brown and purple
			Board.colourSelected[1] = Board.selectColour2;
			//If the colourSelected[1] is equal to the value 0
			if (Board.colourSelected[1] == 0) {
				//Sets the colour as being brown
				g.setColor(brown);
				//Sets colour as brown for fruit2
				Board.colourSelected[1] = 1;
			} else {
				//Sets the colour as being purple
				g.setColor(purple);
				//Sets colour as purple for fruit2
				Board.colourSelected[1] = 2;
			}
			break;
		//if brown
		case 1:
			//Sets the value of the colour for fruit2 as being a random colour between red and purple
			Board.colourSelected[1] = Board.selectColour2;
			//If the colourSelected[1] is equal to the value 0
			if (Board.colourSelected[1] == 0) {
				//Sets the colour as being red
				g.setColor(red);
				//Sets colour as red for fruit2
				Board.colourSelected[1] = 0;
			} else {
				//Sets the colour as being purple
				g.setColor(purple);
				//Sets colour as purple for fruit2
				Board.colourSelected[1] = 2;
			}
			break;
		//if purple
		case 2:
			//Sets the value of the colour for fruit2 as being a random colour between brown and red
			Board.colourSelected[1] = Board.selectColour2;
			//If the colourSelected[1] is equal to the value 0
			if (Board.colourSelected[1] == 0) {
				//Sets the colour as being red
				g.setColor(red);
				//Sets colour as red for fruit2
				Board.colourSelected[1] = 0;
			} else {
				//Sets the colour as being brown
				g.setColor(brown);
				//Sets colour as brown for fruit2
				Board.colourSelected[1] = 1;
			}
			break;
		}

		//This draws the second fruit (containing first incorrect answer).
		g.fillRect(Board.fruit2.x * Board.SCALE, Board.fruit2.y * Board.SCALE,
				Board.SCALE, Board.SCALE);

		//This changes the colour of fruit3 based on the colours in fruit1 and fruit2, this ensures that all 3 colours are the same.
		//Based on the colour of fruit1
		switch (Board.colourSelected[0]) {
		//if fruit1 is red
		case 0:
			//Based on the colour of fruit2
			switch (Board.colourSelected[1]) {
			//If fruit2 is brown
			case 1:
				//Sets the colour as being purple
				g.setColor(purple);
				//Sets colour as purple for fruit3
				Board.colourSelected[2] = 2;
				break;
			//if fruit2 is purple
			case 2:
				//Sets the colour as being brown
				g.setColor(brown);
				//Sets colour as brown for fruit3
				Board.colourSelected[2] = 1;
				break;
			}
			break;
		//if fruit1 is brown
		case 1:
			//Based on the colour of fruit2
			switch (Board.colourSelected[1]) {
			//If fruit2 is red
			case 0:
				//Sets the colour as being purple
				g.setColor(purple);
				//Sets colour as purple for fruit3
				Board.colourSelected[2] = 2;
				break;
			//if fruit2 is purple
			case 2:
				//Sets the colour as being red
				g.setColor(red);
				//Sets colour as red for fruit3
				Board.colourSelected[2] = 0;
				break;
			}
			break;
		//if fruit1 is purple
		case 2:
			//Based on the colour of fruit2
			switch (Board.colourSelected[1]) {
			//If fruit2 is red
			case 0:
				//Sets the colour as being brown
				g.setColor(brown);
				//Sets colour as brown for fruit3
				Board.colourSelected[2] = 1;
				break;
			//if fruit2 is brown
			case 1:
				//Sets the colour as being red
				g.setColor(red);
				//Sets colour as red for fruit3
				Board.colourSelected[2] = 0;
				break;
			}
			break;
		}

		//Draws the fruit at the point of the fruit
		g.fillRect(Board.fruit3.x * Board.SCALE, Board.fruit3.y * Board.SCALE,
				Board.SCALE, Board.SCALE);

		//Calls the method strings and gives it the parameter Graphics g
		strings(g);

		//If the timer is running for the specified time
		if (Movement.timerFinish) {
			//set the string countdown to the relevant message based on the difficulty which is selected
			String countdown = getTime();
			g.setColor(Color.BLACK);
			//Sets the font to be of large style
			g.setFont(QuestionBox.large);
			//Finding the width of the string
			int stringLenQuestion = (int) g.getFontMetrics()
					.getStringBounds(countdown, g).getWidth();
			//Draw the string of 'countdown' in position (x, y)    
			g.drawString(countdown,
			//(int) is casted because drawString takes int values but not doubles.
					(int) (dim4.width / 2 - stringLenQuestion / 2), (int) 100);
		}

		//If the answer is correct, the timer is running and the board hasn't just been started
		if (Movement.collectCorrectAnswer && !Board.isJustStarted) {
			//Set string strCorrect to "Correct!"
			String strCorrect = "Correct!";
			//Setting colour to black
			g.setColor(Color.BLACK);
			//Sets the font to be of large style
			g.setFont(QuestionBox.large);
			//Finding the width of the string
			int stringLenQuestion2 = (int) g.getFontMetrics()
					.getStringBounds(strCorrect, g).getWidth();
			//Draw the string of 'strCorrect' in position (x, y), this appears below the countdown    
			g.drawString(strCorrect,
			//(int) is casted because drawString takes int values but not doubles.
					(int) (dim4.width / 2 - stringLenQuestion2 / 2), (int) 50);
		}

		//If the answer is incorrect, the timer is running and the board hasn't just been started
		if (Movement.collectIncorrectAnswer && !Board.isJustStarted) {
			//Set string strIncorrect to "Incorrect"
			String strIncorrect = "Incorrect";
			//Setting colour to black
			g.setColor(Color.BLACK);
			//Sets the font to be of large style
			g.setFont(QuestionBox.large);
			//Finding the width of the string
			int stringLenQuestion = (int) g.getFontMetrics()
					.getStringBounds(strIncorrect, g).getWidth();
			//Draw the string of 'strIncorrect' in position (x, y)    
			g.drawString(strIncorrect,
			//(int) is casted because drawString takes int values but not doubles.
					(int) (dim4.width / 2 - stringLenQuestion / 2), (int) 50);
		}
	}

	//This will get the time based on the difficulty selected for the timer
	public String getTime() {
		//Initialises and declares the string time.
		String time = null;
		//if difficulty is easy
		if (Questions.difficultyChosen == 0) {
			//String time is "3 Seconds Pause"
			time = "3 Seconds Pause";
		}
		//if difficulty is easy
		if (Questions.difficultyChosen == 1) {
			//String time is "5 Seconds Pause"
			time = "5 Seconds Pause";
		}
		//if difficulty is easy
		if (Questions.difficultyChosen == 2) {
			//String time is "6 Seconds Pause"
			time = "6 Seconds Pause";
		}
		//Returns the string time
		return time;
	}

	public void strings(Graphics g) {
		//Setting colour to black
		g.setColor(Color.BLACK);
		//Displayed when the game had been lost
		String lostString1 = "Game Over";
		//Sets the font to be title style
		g.setFont(QuestionBox.title);
		//This gets the width of the string.
		int stringLenLost1 = (int) g.getFontMetrics()
				.getStringBounds(lostString1, g).getWidth();
		if (Board.lost)
			//Draw the string of 'lostString1' in position (x, y)    
			g.drawString(lostString1,
					//(int) is casted because drawString takes int values but not doubles.
					(int) (dim4.width / 2 - stringLenLost1 / 2),
					(int) dim4.height / 4);

		//This is shown when the player wins or loses the game
		String lostString2 = "Press space to restart";
		//Sets the font to be large style
		g.setFont(QuestionBox.large);
		//This gets the width of the string.
		int stringLenLost2 = (int) g.getFontMetrics()
				.getStringBounds(lostString2, g).getWidth();
		if (Board.lost || Board.won)
			//Draw the string of 'lostString2' in position (x, y)    
			g.drawString(lostString2,
					//(int) is casted because drawString takes int values but not doubles.
					(int) (dim4.width / 2 - stringLenLost2 / 2),
					(int) dim4.height / 4 + 50);

		//This is shown when the player wins or loses the game
		String lostString3 = "Score: " + Board.score;
		//Sets the font to be medium style
		g.setFont(QuestionBox.medium);
		//This gets the width of the string.
		int stringLenLost3 = (int) g.getFontMetrics()
				.getStringBounds(lostString3, g).getWidth();
		if (Board.lost || Board.won)
			//Draw the string of 'lostString3' in position (x, y)    
			g.drawString(lostString3,
					//(int) is casted because drawString takes int values but not doubles.
					(int) (dim4.width / 2 - stringLenLost3 / 2),
					(int) dim4.height / 4 + 150);

		//Shows the difficulty selected when the player wins or loses
		String strDiff = "Difficulty: " + QuestionBox.diffText;
		;
		//This gets the width of the string.
		int lenDiff = (int) g.getFontMetrics().getStringBounds(strDiff, g)
				.getWidth();
		if (Board.lost || Board.won)
			g.drawString(strDiff,
					//(int) is casted because drawString takes int values but not doubles.
					(int) (dim4.width / 2 - lenDiff / 2),
					(int) (dim4.height / 4) + 200);

		//This shows the name entered, this is shown when the player wins or loses the game
		String strName = "Name: " + NameScreen.inName;
		//This gets the width of the string.
		int lenName = (int) g.getFontMetrics().getStringBounds(strName, g)
				.getWidth();
		if (Board.lost || Board.won)
			g.drawString(strName,
					//(int) is casted because drawString takes int values but not doubles.
					(int) (dim4.width / 2 - lenName / 2),
					(int) dim4.height / 4 + 100);

		//Sets the string to be pausedString
		String pausedString = "Game paused. Press ‘P’ to continue.";
		//Sets the font to be large style
		g.setFont(QuestionBox.large);
		//This gets the width of the string.
		int stringLenPause = (int) g.getFontMetrics()
				.getStringBounds(pausedString, g).getWidth();
		//If the game is paused and not lost/won
		if (Board.paused && !Board.lost && !Board.won)
			//Draw the string of 'pausedString' in position (x, y)        
			g.drawString(pausedString,
					//(int) is casted because drawString takes int values but not doubles.
					(int) (dim4.width / 2 - stringLenPause / 2),
					(int) dim4.height / 4);

		//This is displayed if the game is won
		String winString1 = "You Won!";
		//Sets the font to be title style
		g.setFont(QuestionBox.title);
		//This gets the width of the string.
		int stringLenWon1 = (int) g.getFontMetrics()
				.getStringBounds(winString1, g).getWidth();
		//If the game is paused and not lost
		if (Board.won == true)
			//Draw the string of 'winString1' in position (x, y)        
			g.drawString(winString1,
					//(int) is casted because drawString takes int values but not doubles.
					(int) (dim4.width / 2 - stringLenWon1 / 2),
					(int) dim4.height / 4);
	}
}