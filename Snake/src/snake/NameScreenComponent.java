package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


@SuppressWarnings("serial")
//This is used to add the components to the NameScreen JFrame.
public class NameScreenComponent extends JPanel {
	//Creates a new dimension of (600, 720)
	public static Dimension dimName = new Dimension(600, 720);

	@Override
	public Dimension getPreferredSize() {
		//Returns the preferred size as the dimensions of this.
		return new Dimension(dimName.width, dimName.height);
	}

	@Override
	//Paints the components
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Sets the colour to 'greenBack'
		g.setColor(TitleScreenComponent.greenBack);
		//Fills a rectangle starting from position (0, 0) of size (600, 720)
		g.fillRect(0, 0, dimName.width, dimName.height);
		
		//Sets the string strName
		String strName = "Snake - Name Screen";
		//Setting colour to black
		g.setColor(Color.BLACK);
		//Sets the font to be of title style
		g.setFont(QuestionBox.title);
		//Finding the width of the string
		int stringLenName = (int) g.getFontMetrics()
				.getStringBounds(strName, g).getWidth();
		//Draw the string of 'strName' in position (x, y)    
		g.drawString(strName,
				//(int) is casted because drawString takes int values but not doubles.
				(int) (dimName.width / 2 - stringLenName / 2),
				(int) 100);

		//Sets the string strName1
		String strName1 = "Please enter your name, press ‘Finished’ when done to start the game.";
		//Sets the font to be of medium style
		g.setFont(QuestionBox.medium);
		//Finding the width of the string
		int stringLenName1 = (int) g.getFontMetrics()
				.getStringBounds(strName1, g).getWidth();
		//Draw the string of 'strName1' in position (x, y)    
		g.drawString(strName1,
		//(int) is casted because drawString takes int values but not doubles.
				(int) (dimName.width / 2 - stringLenName1 / 2), (int) 300);
	}
}
