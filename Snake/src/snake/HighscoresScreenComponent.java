package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
//This class will draw the components onto the JFrame in the HighscoresScreen class.
public class HighscoresScreenComponent extends JPanel {

	//Creates a new dimension of (600, 720)
	public static Dimension dimHS = new Dimension(600, 720);

	@Override
	public Dimension getPreferredSize() {
		//Returns the preferred size as the dimensions of this.
		return new Dimension(dimHS.width, dimHS.height);
	}

	@Override
	//Paints the components
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Sets the colour to 'greenBack'
		g.setColor(TitleScreenComponent.greenBack);
		//Fills a rectangle starting from position (0, 0) of size (600, 720)
		g.fillRect(0, 0, dimHS.width, dimHS.height);

		//Sets the string strHS
		String strHS = "Snake - High Scores";
		//Setting colour to black
		g.setColor(Color.BLACK);
		//Sets the font to be of title style
		g.setFont(QuestionBox.title);
		//Finding the width of the string
		int stringLenHS = (int) g.getFontMetrics().getStringBounds(strHS, g)
				.getWidth();
		//Draw the string of 'strHS' in position (x, y)    
		g.drawString(strHS,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dimHS.width / 2 - stringLenHS / 2), (int) 40);

		//Sets the string strHS1
		String strHS1 = "Please note: your score will not be added to the high scores table if the game is closed";
		//Sets the font to be of small style
		g.setFont(QuestionBox.small);
		//Finding the width of the string
		int stringLenHS1 = (int) g.getFontMetrics().getStringBounds(strHS1, g)
				.getWidth();
		//Draw the string of 'strHS1' in position (x, y)    
		g.drawString(strHS1,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dimHS.width / 2 - stringLenHS1 / 2), (int) 80);
	}
}