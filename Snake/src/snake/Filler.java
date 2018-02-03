package snake;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
//This class is used to take up the space of the QuestionBox, this will allow for the same layout of the JPanel in which the Fruit and Filler components will be contained, meaning that it will be able to share the same layout as the container JPanel meaning it will be aligned correctly.
public class Filler extends JPanel {

	//Sets the dimension to a new dimension of (600, 120) -- this is the size of the QuestionBox and the Points together (i.e. the total size of the QuestionBox - blue area).
	public static Dimension dim5 = new Dimension(600, 120);

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(dim5.width, dim5.height);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}