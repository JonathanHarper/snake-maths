package snake;

//Allows use of default sRGB colours.
import java.awt.Color;
import java.awt.Dimension;
//Graphics is an abstract class that allows us to draw onto components.
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

//Warnings will not be thrown (are suppressed).
@SuppressWarnings("serial")
//This class is used to create the board component in which the snake can move on.
//What extending does is it allows us to inherit the methods and attributes (properties) of another class. In this case, the DrawBoard class (subclass - inherits state and behaviour from all of its ancestors) inherits properties from the JPanel class (superclass - gives properties to its subclasses).
public class DrawBoard extends JPanel {

	//Declaring the colour 'yellow' as the hex colour code (turned to decimal using a hex calculator so Java can use it) which was chosen in the design stage. This is used for the background
	public static Color yellow = new Color(13816442);
	//Declaring the colour 'lGreen' as the hex colour code (turned to decimal using a hex calculator so Java can use it) which was chosen in the design stage. This is used for the snake bits (other than the head)
	public static Color lGreen = new Color(35328);
	//Declaring the colour 'dGreen' as the hex colour code (turned to decimal using a hex calculator so Java can use it) which was chosen in the design stage. This is used for the snake head.
	public static Color dGreen = new Color(23552);

	//Sets the dimension to a new dimension of (600, 600)
	public static Dimension dim1 = new Dimension(600, 600);

	//This overrides the getPrefferedSize method, this sets the PrefferedSize as returning a dimension of (600, 600). 
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(dim1.height, dim1.width);
	}

	//We are overriding the protected method in order to define our own body (and properties) for the paintComponent method. Overriding this allows us to define how we will paint the component DrawBoard. Protected means that it can only be accessed by things within the same package.
	@Override
	//A component is an object which has a graphical representation that can interact with the user (e.g. buttons).
	//What this does is it paints the component using the graphics class, defined as instance g.
	protected void paintComponent(Graphics g) {
		//'Super.' refers to the method calling its super class, which in this case is JPanel. Doing this allows me to use in built 'drawings' such as rectangle and oval, which can be drawn by calling their methods.
		super.paintComponent(g);
		//Setting the colour in which graphics objects are made to the colour defined in the colour 'yellow'
		g.setColor(yellow);
		//Filling in a rectangle which has a width and height of (600, 600), in other words provides a background of colour 'yellow' defined.
		g.fillRect(0, 0, dim1.width, dim1.height);

		//For every element in snakeBody
		for (Point p : Board.snakeBody) {
			//Sets the colour
			g.setColor(lGreen);
			//Fills in a rectangle of 20x20 pixels
			g.fillRect(p.x * Board.SCALE, p.y * Board.SCALE, Board.SCALE,
					Board.SCALE);
		}
		//Sets the colour to dGreen
		g.setColor(dGreen);
		//Fills in the rectangle for the point of the snake head
		g.fillRect(Board.head.x * Board.SCALE, Board.head.y * Board.SCALE,
				Board.SCALE, Board.SCALE);
	}
}