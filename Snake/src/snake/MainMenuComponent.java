package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
//This class is used to paint the component of the MainMenu, this will draw onto the JFrame
public class MainMenuComponent extends JPanel {

	//Creates a new dimension of (600, 720)
	public static Dimension dimMainMenu = new Dimension(600, 720);

	@Override
	public Dimension getPreferredSize() {
		//Returns the preferred size as the dimensions of this.
		return new Dimension(dimMainMenu.width, dimMainMenu.height);
	}

	@Override
	//Paints the components
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Sets the colour to 'greenBack'
		g.setColor(TitleScreenComponent.greenBack);
		//Fills a rectangle starting from position (0, 0) of size (600, 720)
		g.fillRect(0, 0, dimMainMenu.width, dimMainMenu.height);

		//Sets the string strMainMenu
		String strMainMenu = "Snake - Main Menu";
		//Setting colour to black
		g.setColor(Color.BLACK);
		//Sets the font to be of title style
		g.setFont(QuestionBox.title);
		//Finding the width of the string
		int stringLenMainMenu = (int) g.getFontMetrics()
				.getStringBounds(strMainMenu, g).getWidth();
		//Draw the string of 'strMainMenu' in position (x, y)    
		g.drawString(strMainMenu,
				//(int) is casted because drawString takes int values but not doubles.
				(int) (dimMainMenu.width / 2 - stringLenMainMenu / 2),
				(int) 100);

		//Calls the diffString method and uses the parameter Graphics g
		diffString(g);
	}

	public void diffString(Graphics g) {
		//Sets the string strDiff
		String strDiff = "Difficulty: (the default difficulty is easy)";
		//Setting colour to black
		g.setColor(Color.BLACK);
		//Sets the font to be of small style
		g.setFont(QuestionBox.small);
		//Finding the width of the string
		int stringLenDiff = (int) g.getFontMetrics()
				.getStringBounds(strDiff, g).getWidth();
		//Draw the string of 'strDiff' in position (x, y)    
		g.drawString(strDiff,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dimMainMenu.width / 2 - stringLenDiff / 2), (int) 260);

		//Sets the string strDiff2
		String strDiff2 = "Easy – Addition and Subtraction";
		//Finding the width of the string
		int stringLenDiff2 = (int) g.getFontMetrics()
				.getStringBounds(strDiff2, g).getWidth();
		//Draw the string of 'strDiff2' in position (x, y)    
		g.drawString(strDiff2,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dimMainMenu.width / 2 - stringLenDiff2 / 2), (int) 280);

		//Sets the string strDiff3
		String strDiff3 = "Medium – Multiplication and Division";
		//Finding the width of the string
		int stringLenDiff3 = (int) g.getFontMetrics()
				.getStringBounds(strDiff3, g).getWidth();
		//Draw the string of 'strDiff3' in position (x, y)    
		g.drawString(strDiff3,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dimMainMenu.width / 2 - stringLenDiff3 / 2), (int) 300);

		//Sets the string strDiff4
		String strDiff4 = "Hard – Addition, Subtraction, Multiplication and Division";
		//Finding the width of the string
		int stringLenDiff4 = (int) g.getFontMetrics()
				.getStringBounds(strDiff4, g).getWidth();
		//Draw the string of 'strDiff4' in position (x, y)    
		g.drawString(strDiff4,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dimMainMenu.width / 2 - stringLenDiff4 / 2), (int) 320);
	}
}