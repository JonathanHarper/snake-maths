package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
//This class is used to paint the component of the TitleScreen, this will draw onto the JFrame.
public class TitleScreenComponent extends JPanel {

	//Makes a new colour with hex colour code #669900, this is to be used as the background
	static Color greenBack = new Color(6723840);

	//Creates a new dimension of (600, 720)
	public static Dimension dimTitle = new Dimension(600, 720);

	@Override
	public Dimension getPreferredSize() {
		//Returns the preferred size as the dimensions of this.
		return new Dimension(dimTitle.width, dimTitle.height);
	}

	@Override
	//Paints the components
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Sets the colour to 'greenBack'
		g.setColor(greenBack);
		//Fills a rectangle starting from position (0, 0) of size (600, 720)
		g.fillRect(0, 0, dimTitle.width, dimTitle.height);

		//Sets the string strSnakeTitle.
		String strSnakeTitle = "Snake";
		//Setting colour to black
		g.setColor(Color.BLACK);
		//Sets the font to be of title style
		g.setFont(QuestionBox.title);
		//Finding the width of the string
		int stringLenSnake = (int) g.getFontMetrics()
				.getStringBounds(strSnakeTitle, g).getWidth();
		//Draw the string of 'strSnakeTitle' in position (x, y)    
		g.drawString(strSnakeTitle,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dimTitle.width / 2 - stringLenSnake / 2), (int) 100);

		//Sets the string strTitleEd.
		String strTitleEd = "An Educational Game";
		//Sets the font to be of large style
		g.setFont(QuestionBox.large);
		//Finding the width of the string
		int stringLenTitleEd = (int) g.getFontMetrics()
				.getStringBounds(strTitleEd, g).getWidth();
		//Draw the string of 'strTitleEd' in position (x, y)    
		g.drawString(strTitleEd,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dimTitle.width / 2 - stringLenTitleEd / 2), (int) 175);

		//Sets the string strTitleDev. This is of large font style.
		String strTitleDev = "Developed By Jonathan Harper";
		//Finding the width of the string
		int stringLenTitleDev = (int) g.getFontMetrics()
				.getStringBounds(strTitleDev, g).getWidth();
		//Draw the string of 'strTitleDev' in position (x, y)    
		g.drawString(strTitleDev,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dimTitle.width / 2 - stringLenTitleDev / 2), (int) 250);
	}
}