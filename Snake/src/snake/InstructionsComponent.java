package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
//This class is used to paint the component of the Instructions, this will draw onto the JFrame
public class InstructionsComponent extends JPanel {

	//Creates a new dimension of (600, 720)
	public static Dimension dimInstructions = new Dimension(600, 720);

	@Override
	public Dimension getPreferredSize() {
		//Returns the preferred size as the dimensions of this.
		return new Dimension(dimInstructions.width, dimInstructions.height);
	}

	@Override
	//Paints the components
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Sets the colour to 'greenBack'
		g.setColor(TitleScreenComponent.greenBack);
		//Fills a rectangle starting from position (0, 0) of size (600, 720)
		g.fillRect(0, 0, dimInstructions.width, dimInstructions.height);

		//Calls the strings method with the parameter Graphics g
		strings(g);
	}

	public void strings(Graphics g) {
		//Sets the string strInstr
		String strInstr = "Snake";
		//Setting colour to black
		g.setColor(Color.BLACK);
		//Sets the font to be of title style
		g.setFont(QuestionBox.title);
		//Finding the width of the string
		int stringLenInstr = (int) g.getFontMetrics()
				.getStringBounds(strInstr, g).getWidth();
		//Draw the string of 'strInstr' in position (x, y)    
		g.drawString(strInstr,
				//(int) is casted because drawString takes int values but not doubles.
				(int) (dimInstructions.width / 2 - stringLenInstr / 2),
				(int) 40);

		String strInstr2 = "Instructions Screen";
		//Finding the width of the string
		int stringLenInstr2 = (int) g.getFontMetrics()
				.getStringBounds(strInstr2, g).getWidth();
		g.drawString(strInstr2,
				(int) (dimInstructions.width / 2 - stringLenInstr2 / 2),
				(int) 80);

		String strMovement = "Movement";
		g.setFont(QuestionBox.large);
		//Finding the width of the string
		int lenMove = (int) g.getFontMetrics().getStringBounds(strMovement, g)
				.getWidth();
		g.drawString(strMovement,
				(int) (dimInstructions.width / 2 - lenMove / 2), (int) 120);

		String strMovement1 = "Pressing the left arrow key makes the snake face towards the left.";
		g.setFont(QuestionBox.small);
		//Finding the width of the string
		int lenMove1 = (int) g.getFontMetrics()
				.getStringBounds(strMovement1, g).getWidth();
		g.drawString(strMovement1,
				(int) (dimInstructions.width / 2 - lenMove1 / 2), (int) 145);

		String strMovement2 = "Pressing the right arrow key makes the snake face towards the right.";
		//Finding the width of the string
		int lenMove2 = (int) g.getFontMetrics()
				.getStringBounds(strMovement2, g).getWidth();
		g.drawString(strMovement2,
				(int) (dimInstructions.width / 2 - lenMove2 / 2), (int) 160);

		String strMovement3 = "Pressing the up arrow key makes the snake face upwards.";
		//Finding the width of the string
		int lenMove3 = (int) g.getFontMetrics()
				.getStringBounds(strMovement3, g).getWidth();
		g.drawString(strMovement3,
				(int) (dimInstructions.width / 2 - lenMove3 / 2), (int) 175);

		String strMovement4 = "Pressing the down arrow key makes the snake face downwards.";
		//Finding the width of the string
		int lenMove4 = (int) g.getFontMetrics()
				.getStringBounds(strMovement4, g).getWidth();
		g.drawString(strMovement4,
				(int) (dimInstructions.width / 2 - lenMove4 / 2), (int) 190);

		String strAnswers = "Answers";
		g.setFont(QuestionBox.large);
		//Finding the width of the string
		int lenAnswers = (int) g.getFontMetrics()
				.getStringBounds(strAnswers, g).getWidth();
		g.drawString(strAnswers,
				(int) (dimInstructions.width / 2 - lenAnswers / 2), (int) 220);

		String strAnswers1 = "Collect answers by making the snake eat one of the pieces of fruit, the answer which relates";
		g.setFont(QuestionBox.small);
		//Finding the width of the string
		int lenAnswers1 = (int) g.getFontMetrics()
				.getStringBounds(strAnswers1, g).getWidth();
		g.drawString(strAnswers1,
				(int) (dimInstructions.width / 2 - lenAnswers1 / 2), (int) 245);

		String strAnswers2 = "to the fruit is shown to the left hand side of it, for example [RED] 55 means that the red";
		//Finding the width of the string
		int lenAnswers2 = (int) g.getFontMetrics()
				.getStringBounds(strAnswers2, g).getWidth();
		g.drawString(strAnswers2,
				(int) (dimInstructions.width / 2 - lenAnswers2 / 2), (int) 260);

		String strAnswers3 = "fruit on the board relates to the answer of 55.";
		//Finding the width of the string
		int lenAnswers3 = (int) g.getFontMetrics()
				.getStringBounds(strAnswers3, g).getWidth();
		g.drawString(strAnswers3,
				(int) (dimInstructions.width / 2 - lenAnswers3 / 2), (int) 275);

		String strTimer = "Question Timer";
		g.setFont(QuestionBox.large);
		//Finding the width of the string
		int lenTimer = (int) g.getFontMetrics().getStringBounds(strTimer, g)
				.getWidth();
		g.drawString(strTimer,
				(int) (dimInstructions.width / 2 - lenTimer / 2), (int) 305);

		String strTimer1 = "The question timer is the time that you have when a new question appears before the snake";
		g.setFont(QuestionBox.small);
		//Finding the width of the string
		int lenTimer1 = (int) g.getFontMetrics().getStringBounds(strTimer1, g)
				.getWidth();
		g.drawString(strTimer1,
				(int) (dimInstructions.width / 2 - lenTimer1 / 2), (int) 330);

		String strTimer2 = "starts to move again, this will give you some time to work out the question beforehand. Don’t";
		//Finding the width of the string
		int lenTimer2 = (int) g.getFontMetrics().getStringBounds(strTimer2, g)
				.getWidth();
		g.drawString(strTimer2,
				(int) (dimInstructions.width / 2 - lenTimer2 / 2), (int) 345);

		String strTimer3 = "forget, if you are struggling you can pause the game and work it out!";
		//Finding the width of the string
		int lenTimer3 = (int) g.getFontMetrics().getStringBounds(strTimer3, g)
				.getWidth();
		g.drawString(strTimer3,
				(int) (dimInstructions.width / 2 - lenTimer3 / 2), (int) 360);

		String strOver = "Game Over";
		g.setFont(QuestionBox.large);
		//Finding the width of the string
		int lenOver = (int) g.getFontMetrics().getStringBounds(strOver, g)
				.getWidth();
		g.drawString(strOver, (int) (dimInstructions.width / 2 - lenOver / 2),
				(int) 390);

		String strOver1 = "The game will end if you hit the snake’s head against its body or if you hit any of the";
		g.setFont(QuestionBox.small);
		//Finding the width of the string
		int lenOver1 = (int) g.getFontMetrics().getStringBounds(strOver1, g)
				.getWidth();
		g.drawString(strOver1,
				(int) (dimInstructions.width / 2 - lenOver1 / 2), (int) 415);

		String strOver2 = "borders, so watch out for these!";
		//Finding the width of the string
		int lenOver2 = (int) g.getFontMetrics().getStringBounds(strOver2, g)
				.getWidth();
		g.drawString(strOver2,
				(int) (dimInstructions.width / 2 - lenOver2 / 2), (int) 430);

		String strWin = "Winning";
		g.setFont(QuestionBox.large);
		//Finding the width of the string
		int lenWin = (int) g.getFontMetrics().getStringBounds(strWin, g)
				.getWidth();
		g.drawString(strWin, (int) (dimInstructions.width / 2 - lenWin / 2),
				(int) 460);

		String strWin1 = "In order to win the game, you must fill 3/4 of the board with the snake.";
		g.setFont(QuestionBox.small);
		//Finding the width of the string
		int lenWin1 = (int) g.getFontMetrics().getStringBounds(strWin1, g)
				.getWidth();
		g.drawString(strWin1, (int) (dimInstructions.width / 2 - lenWin1 / 2),
				(int) 485);

		String strPoints = "Points";
		g.setFont(QuestionBox.large);
		//Finding the width of the string
		int lenPoints = (int) g.getFontMetrics().getStringBounds(strPoints, g)
				.getWidth();
		g.drawString(strPoints,
				(int) (dimInstructions.width / 2 - lenPoints / 2), (int) 515);

		String strPoints1 = "It’s simple, if you get the right answer you get 1 point, if you get the wrong answer";
		g.setFont(QuestionBox.small);
		//Finding the width of the string
		int lenPoints1 = (int) g.getFontMetrics()
				.getStringBounds(strPoints1, g).getWidth();
		g.drawString(strPoints1,
				(int) (dimInstructions.width / 2 - lenPoints1 / 2), (int) 540);

		String strPoints2 = "you get -2 points, so to get the highest score collect as many correct answers as possible!";
		//Finding the width of the string
		int lenPoints2 = (int) g.getFontMetrics()
				.getStringBounds(strPoints2, g).getWidth();
		g.drawString(strPoints2,
				(int) (dimInstructions.width / 2 - lenPoints2 / 2), (int) 555);

		String strCont = "Controls";
		g.setFont(QuestionBox.large);
		//Finding the width of the string
		int lenCont = (int) g.getFontMetrics().getStringBounds(strCont, g)
				.getWidth();
		g.drawString(strCont, (int) (dimInstructions.width / 2 - lenCont / 2),
				(int) 585);

		String strCont1 = "When in game press the ‘P’ button to pause the game. ";
		g.setFont(QuestionBox.small);
		//Finding the width of the string
		int lenCont1 = (int) g.getFontMetrics().getStringBounds(strCont1, g)
				.getWidth();
		g.drawString(strCont1,
				(int) (dimInstructions.width / 2 - lenCont1 / 2), (int) 610);

		String strCont2 = "When the game is lost or won, press the space bar to start a new game.";
		//Finding the width of the string
		int lenCont2 = (int) g.getFontMetrics().getStringBounds(strCont2, g)
				.getWidth();
		g.drawString(strCont2,
				(int) (dimInstructions.width / 2 - lenCont2 / 2), (int) 625);
	}
}