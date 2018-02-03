package snake;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//This class is used to create the game board.
public class Board implements ActionListener, KeyListener {

	public static JFrame frame;
	public Graphics g;
	public DrawBoard drawBoard;
	public QuestionBox questionBox;
	//This is a point in which the first fruit will exist.
	public static Point fruit1;
	//This is a point in which the second fruit will exist.
	public static Point fruit2;
	//This is a point in which the third fruit will exist.
	public static Point fruit3;
	public Fruit fruit;
	public Filler filler;
	
	//Fruit
	private ImageIcon melon;
	private JLabel lblMelon;
	private ImageIcon strawberry;
	private JLabel lblStrawberry;
	private ImageIcon peach;
	private JLabel lblPeach;
	
	//Avoids death by pressing directional buttons simultaneously
	public boolean isDirectionSelected;
	
	public JPanel c2;
	
	//This is used to get the question
	public static String question;
	//This is used to get the correct answer
	public static int corAns;
	//This is used to generate the first incorrect answer.
	public static int incorAns1;
	//This is used to generate the second incorrect answer.
	public static int incorAns2;
	//This is used to check how many correct answers have been scored. When this value is 2, we increase the number limit by 5.
	public static int numCorrect;

	//This is the head of the snake. It is defined as a single point.
	public static Point head;
	public static Dimension dim = DrawBoard.dim1;
	//Creates a random number generator
	public static Random rand = new Random();
	//This is where the score is held and updated
	public static int score;
	//This is the tailLength of the snake [in other words the length of the snake - 1 (we negate one because we do not include the head)]
	public static int tailLength = 3;
	//Creates a boolean (true/false) for if the player has lost or not
	public static boolean lost = false;
	//Creates a boolean value for the pause function. This will allow the user to pause the game.
	public static boolean paused = false;
	//Checks to see if user has won
	public static boolean won = false;

	//Creating a new thread t1, this is used for the timer and inside the thread, movement of the snake is controlled
	Thread t1;
	//Whether or not the game has been started
	public static boolean isStarted;
	//Whether or not a piece of fruit has been eaten by the snake
	public static boolean fruitEaten;
	//Keeps track if the game has just been started (allows for game over scenarios without issues)
	public static boolean isJustStarted;
	//Has snake collected the fruit (fruit2 and fruit3) containing the wrong answers
	public static boolean collectedFalse;
	//Has snake collected the fruit (fruit1) containing the correct answer
	public static boolean collectedTrue;
	//This is used to ensure that if the game has just started, the score will not be recorded in the high score table.
	public static boolean setPoints;

	//This checks whether the fruit has been collected
	public static boolean collected;
	//This will assign the colour chosen to the elements within the array, this will allow us to do checks to ensure that no 2 fruit are the same colour.
	//The structure of the array is as follows
	// [0] - fruit1 colour
	// [1] - fruit2 colour
	// [2] - fruit3 colour
	public static int[] colourSelected = new int[3];
	//This allows us to select a random colour for the fruit to be assigned.
	//Where selectColour = 0 refers to the colour red
	//Where selectColour = 1 refers to the colour brown
	//Where selectColour = 2 refers to the colour purple
	public static int selectColour;
	//Selects a random colour for the second fruit -- fruit2
	public static int selectColour2;

	//This is to create the snake object. This is a list of points which will hold the snake. The linked list holds the elements of the snake and point represents an x,y position.
	public static LinkedList<Point> snakeBody = new LinkedList<Point>();
	//Defining a new Timer called ticker. This is using the form  new Timer(int delay in milliseconds, ActionListener listener).  What the timer does is it allows threads to schedule the execution of instructions. In this case to constantly refresh the drawBoard component at regular intervals, which in this case is 125ms meaning it runs at 8 frames per second (which was the specified speed of the snake in the design stage). This will give the appearance of motion. What "this" does is it is in reference to the current instance. 
	public Timer ticker = new Timer(125, this);
	//These are constants defined for the direction in which the snake is facing.
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	//This allows us to set the direction of the snake. The initial direction is set to down as to not kill the snake instantly.
	public static int direction = DOWN;
	//This creates a constant which represents the size of one of the individual bits (i.e. the scale in which everything will be subjected to so everything will work like a grid).
	public static final int SCALE = 20;

	//This is used to create a button which when pressed will return the user back to the main menu
	public JButton backToMain;

	public int strawFruit;
	public int melonFruit;
	public int peachFruit;
	
	//This is a constructor for the class Board. This will allow us to create an object. Without a constructor, a default constructor is made with no parameters or code inside when an object of that class is created. Constructors are simply executed first when an object is made, and therefore are usually used for initialising.
	public Board() {
		//Calls the boardBack method. This creates the board.
		boardBack(g);
		//Calls the startGame method. This starts a new game.
		startGame();
	}

	//Method to start the game
	public void startGame() {
		//Declares the over variable as being false. This is used in case the game has been lost and the user wishes to restart the game.
		lost = false;
		//Sets collected to false
		collected = false;
		//Declares the variable won as false.
		won = false;
		//Making the game un-paused if paused.
		paused = false;
		//Makes a new random number
		rand = new Random();
		//Starts the score at 0
		score = 0;
		//Starts the tailLength at 3, this makes the total length of the snake equal to 4
		tailLength = 3;
		//Initialising the number limit to start at 5.
		Questions.numLimit = 5;
		//Sets the numCorrect to zero.
		numCorrect = 0;
		//Sets the start direction as being down
		direction = DOWN;
		//Clears the existing snake body
		snakeBody.clear();
		//Sets the collectCorrectAnswer to being false
		Movement.collectCorrectAnswer = false;
		//Sets the collectIncorrectAnswer to being false
		Movement.collectIncorrectAnswer = false;
		//Sets the fruitEaten to being false
		fruitEaten = false;
		//This starts the head at point (0, 0) - keep in mind that for java point (0, 0) is in the top left hand corner.
		head = new Point(0, 0);
		// Draws the 3 fruit onto the board.
		drawFruit();
		// This checks to make sure that the position of the fruit being placed is not the same as the position of the head or any of the snakeBody positions.
		fruitEqualPoint();
		//This makes a new question
		newQuestion();
		//This shows the new question by repainting the questionBox component
		questionBox.repaint();
		//This sets a random colour. This will be used for fruit1
		selectColour = rand.nextInt(3);
		//Selects a random colour for fruit2
		selectColour2 = rand.nextInt(2);

		//Randomises the fruit
		fruitColour();
		
		isDirectionSelected = false;
		
		//Sets isStarted to true, this tells the thread that the game is still running and to continue doing its tasks
		isStarted = true;
		//This is used to tell the thread whether the game has just been started (almost immediately) 
		isJustStarted = true;
		//Sets setPoints to be true.
		setPoints = true;

		//New thread instance which runs the runnable code in the Movement class
		t1 = new Thread(new Movement());
		//Starts the t1 thread
		t1.start();

		//Sets both the collected to false
		collectedTrue = false;
		collectedFalse = false;

		//Starts the timer, this starts is sending action events to its listeners.
		ticker.start();
	}

	//Method to create the JFrame, define its attributes and add it's components
	public void boardBack(Graphics g) {
		//Creates instances questionBox , points, fruit, filler and drawBoard from the respective classes.
		questionBox = new QuestionBox();
		drawBoard = new DrawBoard();
		fruit = new Fruit();
		filler = new Filler();
		//Instance of JButton called backToMain, containing the text "Return to Main Menu". When pressed, it will return the user to the main menu
		backToMain = new JButton("Return to Main Menu");

		//Declaring instance of the JFrame 'frame'. This JFrame is called to declare a title for this frame - "Snake".
		final JFrame frame = new JFrame("Snake");
		//A JPanel which can be added into the JFrame. What this JPanel does is it adds the two individual components (drawBoard and questionBox) into the JFrame without overwriting one another (which would happen if we didn't group them together into a single JPanel). This is done by adding the components to the JPanel, adding this JPanel to the JLayeredPane and then adding the JLayeredPane to the JFrame
		JPanel container = new JPanel();
		//JPanel containing the fruit and filler components.
		JPanel c2 = new JPanel();
		//JLayeredPane which allows for JPanels to be layered, allowing for c2 to be on top of container. This will contain the JPanels c2 and container.
		JLayeredPane pane = new JLayeredPane();


		//Sets the operation which will happen when the user closes the JFrame 'frame', the EXIT_ON_CLOSE exits the application using the System exit method. This means that when the JFrame is closed, the application will be exited (closed).
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Adding this instance as a KeyListener
		frame.addKeyListener(this);
		//Sets the layout of the container to have a new layout from the BorderLayout class.
		container.setLayout(new BorderLayout());
		//Setting layout of c2 to null, this allows for absolute positioning of components
		c2.setLayout(null);
		//This stops the frame from being able to be resized.
		frame.setResizable(false);

		//Adds the questionBox component to the container JPanel.
		container.add(questionBox, BorderLayout.NORTH);
		//Adds the drawBoard component to the JPanel. It also sets the layout to make the drawBoard component sit at the bottom of the screen by calling the method SOUTH of the BorderLayout.
		container.add(drawBoard, BorderLayout.SOUTH);

		//Positions the fruit component to be positioned at (0, 120) and of size (600, 600)
		fruit.setBounds(0, 120, 600, 600);
		//Positions the filler component to be positioned at (0, 0) and of size (600, 120)
		filler.setBounds(0, 0, 600, 120);

		//Sets the size of the button to be (150, 35)
		backToMain.setPreferredSize(new Dimension(150, 35));
		//Sets the position of the button to be (445, 80)
		backToMain.setBounds(445, 80, 150, 35);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		backToMain.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			@Override
			//Calls the actionPerformed method and depending on the ActionEvent (such as a button press) - the ActionEvent is the parameter  
			public void actionPerformed(ActionEvent e) {
				//Object b is equal to the source of the ActionEvent
				Object b = e.getSource();
				//If the source was backToMain
				if (b == backToMain) {
					//When the button is pressed, it checks if isJustStarted is true, this is used to ensure that 
					if (isJustStarted) {
						//setPoints is false, this will ensure that if the game has just started, the score shall not be added to the high scores.
						setPoints = false;
					} else {
						//Checks if the game has already been won or lost, this stop repeat records from appearing.
						if (!lost && !won) {
							//Sets lost to true, this will mean that the score is updated into the high score table immediately.
							lost = true;
							//Calls the method isBoardLostOrWon(), this will add the score to the scores.dat, scores1.dat or scores2.dat file.
							HighscoresScreen.isBoardLostOrWon();
						}
					}
					//Closes the JFrame
					frame.dispose();
					//Opens the main menu
					MainMenu mainMenu = new MainMenu();
				}
			}
		});
		//Prevents the button from being focused on, this prevents issues with when keys are pressed, this isn't going to be used for the button.
		backToMain.setFocusable(false);

		//Sets the font style as being button
		backToMain.setFont(TitleScreen.button);

		melon = new ImageIcon(getClass().getResource("/melon.png"));
		lblMelon = new JLabel(melon);
		lblMelon.setVisible(true);
		lblMelon.setPreferredSize(new Dimension(20, 20));
		
		strawberry = new ImageIcon(getClass().getResource("/strawberry.png"));
		lblStrawberry = new JLabel(strawberry);
		lblStrawberry.setVisible(true);
		lblStrawberry.setPreferredSize(new Dimension(20, 20));
		
		peach = new ImageIcon(getClass().getResource("/peach.png"));
		lblPeach = new JLabel(peach);
		lblPeach.setVisible(true);
		lblPeach.setPreferredSize(new Dimension(20, 20));
		
		drawFruit();
		
		fruitColour();
		
//		//Setting fruit position
//		lblMelon.setBounds(fruit1.x*SCALE, (fruit1.y*SCALE)+120, Board.SCALE, Board.SCALE);
//		lblStrawberry.setBounds(fruit2.x*SCALE, (fruit2.y*SCALE)+120, Board.SCALE, Board.SCALE);
//		lblPeach.setBounds(fruit3.x*SCALE, (fruit3.y*SCALE)+120, Board.SCALE, Board.SCALE);
		
		//Adding fruit component to the c2 JPanel. This is assigned as the south position.
		c2.add(fruit);
		//Adding the filler component to the c2 JPanel. This is assigned as the north position.
		c2.add(filler);
		//Adds the button backToMain
		c2.add(backToMain);

		//Adding the three fruit to the board.
		c2.add(lblMelon);
		c2.add(lblStrawberry);
		c2.add(lblPeach);
		
		//Making both of the components background as being transparent. This will allow us to see the container underneath.
		filler.setOpaque(false);
		fruit.setOpaque(false);
		//Sets the JPanel as being transparent. This also constantly repaints the JPanel regardless of the fact I do not call for the components to repaint.
		c2.setOpaque(false);

		//Sets the boundaries for the components, these boundaries must be defined so that the pane is able to add them with the correct dimensions.
		container.setBounds(0, 0, 600, 720);
		c2.setBounds(0, 0, 600, 720);

		//Adding the c2 JPanel in the foreground (level 2 is above level 1)
		pane.add(c2, new Integer(2));
		//Adds the JPanel container in the background
		pane.add(container, new Integer(1));

		//Setting the dimensions for pane as (600, 720)
		pane.setPreferredSize(new Dimension(600, 720));

		//This adds the pane JLayeredPane to the JFrame frame.
		frame.add(pane);

		//Fits the frame around the components which are inside of it. This means we do not have to set the size of the frame, the components which are contained instead determine the size.
		frame.pack();
		//This centres the frame into the middle of the screen.
		frame.setLocationRelativeTo(null);
		//JFrame is initially set to invisible, so we use the setVisible method (setting it to true) to make the JFrame 'frame' visible.
		frame.setVisible(true);

		//Second pack is called to fix border issues
		frame.pack();
	}

	public void fruitColour() {
		
			//Based upon the random selection of integer, the correct fruit is assigned
			switch (Board.selectColour) {
			case 0:
				Board.colourSelected[0] = Board.selectColour;
				lblMelon.setBounds(fruit1.x*SCALE, (fruit1.y*SCALE)+120, Board.SCALE, Board.SCALE);
				break;
			case 1:
				Board.colourSelected[0] = Board.selectColour;
				lblPeach.setBounds(fruit1.x*SCALE, (fruit1.y*SCALE)+120, Board.SCALE, Board.SCALE);
				break;
			case 2:
				Board.colourSelected[0] = Board.selectColour;
				lblStrawberry.setBounds(fruit1.x*SCALE, (fruit1.y*SCALE)+120, Board.SCALE, Board.SCALE);
				break;
			}


			//Checks what colour the fruit1 is, and based on that assigns a colour for fruit2
			switch (Board.colourSelected[0]) {
			//if red
			case 0:
				//Sets the value of the colour for fruit2 as being a random colour between brown and purple
				Board.colourSelected[1] = Board.selectColour2;
				//If the colourSelected[1] is equal to the value 0
				if (Board.colourSelected[1] == 0) {
					//Sets colour as brown for fruit2
					Board.colourSelected[1] = 1;
					lblPeach.setBounds(fruit2.x*SCALE, (fruit2.y*SCALE)+120, Board.SCALE, Board.SCALE);
				} else {
					//Sets colour as purple for fruit2
					Board.colourSelected[1] = 2;
					lblStrawberry.setBounds(fruit2.x*SCALE, (fruit2.y*SCALE)+120, Board.SCALE, Board.SCALE);
				}
				break;
			//if brown
			case 1:
				//Sets the value of the colour for fruit2 as being a random colour between red and purple
				Board.colourSelected[1] = Board.selectColour2;
				//If the colourSelected[1] is equal to the value 0
				if (Board.colourSelected[1] == 0) {
					//Sets colour as red for fruit2
					Board.colourSelected[1] = 0;
					lblMelon.setBounds(fruit2.x*SCALE, (fruit2.y*SCALE)+120, Board.SCALE, Board.SCALE);
				} else {
					//Sets colour as purple for fruit2
					Board.colourSelected[1] = 2;
					lblStrawberry.setBounds(fruit2.x*SCALE, (fruit2.y*SCALE)+120, Board.SCALE, Board.SCALE);
				}
				break;
			//if purple
			case 2:
				//Sets the value of the colour for fruit2 as being a random colour between brown and red
				Board.colourSelected[1] = Board.selectColour2;
				//If the colourSelected[1] is equal to the value 0
				if (Board.colourSelected[1] == 0) {
					//Sets colour as red for fruit2
					Board.colourSelected[1] = 0;
					lblMelon.setBounds(fruit2.x*SCALE, (fruit2.y*SCALE)+120, Board.SCALE, Board.SCALE);
				} else {
					//Sets colour as brown for fruit2
					Board.colourSelected[1] = 1;
					lblPeach.setBounds(fruit2.x*SCALE, (fruit2.y*SCALE)+120, Board.SCALE, Board.SCALE);
				}
				break;
			}

			//This changes the colour of fruit3 based on the colours in fruit1 and fruit2, this ensures that all 3 colours are the same.
			//Based on the colour of fruit1
			switch (Board.colourSelected[0]) {
			//if fruit1 is red
			case 0:
				//Based on the colour of fruit2
				switch (Board.colourSelected[1]) {
				//If fruit2 is brown
				case 1:
					//Sets colour as purple for fruit3
					Board.colourSelected[2] = 2;
					lblStrawberry.setBounds(fruit3.x*SCALE, (fruit3.y*SCALE)+120, Board.SCALE, Board.SCALE);
					break;
				//if fruit2 is purple
				case 2:
					//Sets colour as brown for fruit3
					lblPeach.setBounds(fruit3.x*SCALE, (fruit3.y*SCALE)+120, Board.SCALE, Board.SCALE);
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
					//Sets colour as purple for fruit3
					Board.colourSelected[2] = 2;
					lblStrawberry.setBounds(fruit3.x*SCALE, (fruit3.y*SCALE)+120, Board.SCALE, Board.SCALE);
					break;
				//if fruit2 is purple
				case 2:
					//Sets colour as red for fruit3
					Board.colourSelected[2] = 0;
					lblMelon.setBounds(fruit3.x*SCALE, (fruit3.y*SCALE)+120, Board.SCALE, Board.SCALE);
					break;
				}
				break;
			//if fruit1 is purple
			case 2:
				//Based on the colour of fruit2
				switch (Board.colourSelected[1]) {
				//If fruit2 is red
				case 0:
					//Sets colour as brown for fruit3
					Board.colourSelected[2] = 1;
					lblPeach.setBounds(fruit3.x*SCALE, (fruit3.y*SCALE)+120, Board.SCALE, Board.SCALE);
					break;
				//if fruit2 is brown
				case 1:
					//Sets colour as red for fruit3
					Board.colourSelected[2] = 0;
					lblMelon.setBounds(fruit3.x*SCALE, (fruit3.y*SCALE)+120, Board.SCALE, Board.SCALE);
					break;
				}
				break;
			}
	}
	
	//Overriding the actionPerformed method from the ActionListener class.
	@Override
	//This method is performed for every 'tick' which is specified in the timer.
	//This is the actionPerformed method. the parameter is the ActionEvent e, what this is is an object which gives information about the event and its source. This allows us to perform an action based upon a specific event (e.g. a keyboard key pressed).
	public void actionPerformed(ActionEvent e) {
		//This repaints this component for every tick (125 ms)
		drawBoard.repaint();
		//This repaints the questionBox component, allowing for the text (for example to score) to be constantly updated.
		questionBox.repaint();

		//Calls the ifCollected method.
		isCollected();

		//If the fruit has been collected
		if (collected == true) {
			//Checks if the number correct is 2.
			if (numCorrect == 2) {
				//Increase the number limit by 5
				Questions.numLimit += 5;
				//Set the numCorrect back to zero.
				numCorrect = 0;
			}
			//Change the colour of fruit1 to a random colour
			selectColour = rand.nextInt(3);
			//Selects a random colour for fruit2
			selectColour2 = rand.nextInt(2);
			
			
			
			//Generates a new question
			newQuestion();
			//Repaints the questionBox component
			questionBox.repaint();
			
			fruitColour();
			
			//Sets fruitEaten to true
			fruitEaten = true;
			//Set collected to false
			collected = false;
		}

	}

	//This checks to see whether the snake's head has collided with the snakeBody
	public static boolean noTailAt(int x, int y) {
		//For every point in snakeBody
		for (Point p : snakeBody) {
			//If the snakeBody point is equal to the head point (x, y) refer to the parameters where (x, y) refer to the position it is called from - i.e. the snake's new position
			if (p.equals(new Point(x, y))) {
				//If collision, return false
				return false;
			}
		}
		//If there is no collision, return true.
		return true;
	}

	//This tests to see whether or not the game has been won.
	public static boolean isWon() {
		//If the size of the snakeBody is equal to 3/4 (3 quarters) of the number of possible places on the board - 1 (we negate 1 because the head takes up a space)
		if (tailLength == ((((dim.height / SCALE) * (dim.width / SCALE)) * 0.75) - 1)) {
			//Sets won = true
			won = true;
			//Calls the method isBoardLostOrWon in order to add the score to the file which relates to the high scores
			HighscoresScreen.isBoardLostOrWon();
			//The game has been won.
			return true;
		}
		//If not, the game is not won.
		return false;
	}

	//This checks to make sure that the position of the fruit being placed is not the same as the position of the head or any of the snakeBody positions. If it is, it will create a new point for the fruit.
	public void fruitEqualPoint() {
		//For every point in snakeBody
		for (Point p : snakeBody) {
			//Runs a while loops which checks if the point of the fruit is the same as the point of the head or the position of one of the points in the snakeBody.
			while ((fruit1.x == head.x && fruit1.y == head.y)
					|| (p.x == fruit1.x && p.y == fruit1.y)) {
				//Creates a new fruit in a random position.
				fruit1 = new Point(rand.nextInt(dim.height / SCALE),
						rand.nextInt(dim.height / SCALE));
			}
			while ((fruit2.x == head.x && fruit2.y == head.y)
					|| (p.x == fruit2.x && p.y == fruit2.y)) {
				//Creates a new fruit in a random position.
				fruit2 = new Point(rand.nextInt(dim.height / SCALE),
						rand.nextInt(dim.height / SCALE));
			}
			while ((fruit3.x == head.x && fruit3.y == head.y)
					|| (p.x == fruit3.x && p.y == fruit3.y)) {
				//Creates a new fruit in a random position.
				fruit3 = new Point(rand.nextInt(dim.height / SCALE),
						rand.nextInt(dim.height / SCALE));
			}
		}
	}

	//Overrides the keyPressed method
	@Override
	//KeyEvent is the parameter to the keyPressed method. This method deals with what happens when a key is pressed.
	public void keyPressed(KeyEvent arg0) {
		//Assigning i to the getKeyCode method for arg0. This returns an int value for the key pressed.
		int i = arg0.getKeyCode();
		//If the key pressed is the left arrow key and the direction is not equal to right. This also ensures that the game isn't paused and the timer isn't on.
		if (!isDirectionSelected) {
		if (i == KeyEvent.VK_LEFT && direction != RIGHT && !paused
				&& !Movement.timerFinish)
			//Then direction is set to left
			direction = LEFT;
			isDirectionSelected = true;
		//If the key pressed is the right arrow key and the direction is not equal to left. This also ensures that the game isn't paused and the timer isn't on.
		if (i == KeyEvent.VK_RIGHT && direction != LEFT && !paused
				&& !Movement.timerFinish)
			//Then direction is set to right
			direction = RIGHT;
			isDirectionSelected = true;
		//If the key pressed is the up arrow key and the direction is not equal to down. This also ensures that the game isn't paused and the timer isn't on.
		if (i == KeyEvent.VK_UP && direction != DOWN && !paused
				&& !Movement.timerFinish)
			//Then direction is set to up
			direction = UP;
			isDirectionSelected = true;
		//If the key pressed is the down arrow key and the direction is not equal to up. This also ensures that the game isn't paused and the timer isn't on.
		if (i == KeyEvent.VK_DOWN && direction != UP && !paused
				&& !Movement.timerFinish)
			//Then direction is set to down
			direction = DOWN;
			isDirectionSelected = true;
		}
		//If the user presses space and the game has been lost or has been won
		if (i == KeyEvent.VK_SPACE && (lost == true || won == true))
			//Call the startGame method (i.e. restart the game)
			startGame();
		//If the user presses 'P' on their keyboards
		if (i == KeyEvent.VK_P)
			//pause the game if not paused or un-pause the game if paused
			paused = !paused;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		//Assigning i to the getKeyCode method for arg0. This returns an int value for the key pressed.
		int i = arg0.getKeyCode();
		//If the key pressed is the left arrow key and the direction is not equal to right. This also ensures that the game isn't paused and the timer isn't on.
		if (isDirectionSelected){
		if (i == KeyEvent.VK_LEFT && direction != RIGHT && !paused
				&& !Movement.timerFinish)
			isDirectionSelected = false;
		//If the key pressed is the right arrow key and the direction is not equal to left. This also ensures that the game isn't paused and the timer isn't on.
		if (i == KeyEvent.VK_RIGHT && direction != LEFT && !paused
				&& !Movement.timerFinish)
			isDirectionSelected = false;
		//If the key pressed is the up arrow key and the direction is not equal to down. This also ensures that the game isn't paused and the timer isn't on.
		if (i == KeyEvent.VK_UP && direction != DOWN && !paused
				&& !Movement.timerFinish)
			isDirectionSelected = false;
		//If the key pressed is the down arrow key and the direction is not equal to up. This also ensures that the game isn't paused and the timer isn't on.
		if (i == KeyEvent.VK_DOWN && direction != UP && !paused
				&& !Movement.timerFinish)
			isDirectionSelected = false;
		
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public static void drawFruit() {
		//Makes a new random number
		rand = new Random();
		//Creates new fruits
		fruit1 = new Point(rand.nextInt(dim.height / SCALE),
				rand.nextInt(dim.height / SCALE));
		fruit2 = new Point(rand.nextInt(dim.height / SCALE),
				rand.nextInt(dim.height / SCALE));
		fruit3 = new Point(rand.nextInt(dim.height / SCALE),
				rand.nextInt(dim.height / SCALE));

		//while the position of fruit1 is in any of the 4 corners
		while ((fruit1.x == 0 && fruit1.y == 0)
				|| (fruit1.x == 0 && fruit1.y == 29)
				|| (fruit1.x == 29 && fruit1.y == 0)
				|| (fruit1.x == 29 && fruit1.y == 29)) {
			//Set a random point for fruit1
			fruit1 = new Point(rand.nextInt(dim.height / SCALE),
					rand.nextInt(dim.height / SCALE));
		}

		//while the position of fruit2 is in any of the 4 corners
		while ((fruit2.x == 0 && fruit2.y == 0)
				|| (fruit2.x == 0 && fruit2.y == 29)
				|| (fruit2.x == 29 && fruit2.y == 0)
				|| (fruit2.x == 29 && fruit2.y == 29)) {
			//Set a random point for fruit2
			fruit2 = new Point(rand.nextInt(dim.height / SCALE),
					rand.nextInt(dim.height / SCALE));
		}

		//while the position of fruit3 is in any of the 4 corners
		while ((fruit3.x == 0 && fruit3.y == 0)
				|| (fruit3.x == 0 && fruit3.y == 29)
				|| (fruit3.x == 29 && fruit3.y == 0)
				|| (fruit3.x == 29 && fruit3.y == 29)) {
			//Set a random point for fruit3
			fruit3 = new Point(rand.nextInt(dim.height / SCALE),
					rand.nextInt(dim.height / SCALE));
		}

		//Repeats until the position of fruit1 is not equal to any of the positions of the other fruit.
		while ((fruit1.x == fruit2.x && fruit1.y == fruit2.y)
				|| (fruit1.x == fruit3.x && fruit1.y == fruit3.y)) {
			fruit1 = new Point(rand.nextInt(dim.height / SCALE),
					rand.nextInt(dim.height / SCALE));
		}
		//Repeats until the position of fruit2 is not equal to any of the positions of the other fruit.
		while ((fruit2.x == fruit1.x && fruit2.y == fruit1.y)
				|| (fruit2.x == fruit3.x && fruit2.y == fruit3.y)) {
			fruit2 = new Point(rand.nextInt(dim.height / SCALE),
					rand.nextInt(dim.height / SCALE));
		}
		//Repeats until the position of fruit3 is not equal to any of the positions of the other fruit.
		while ((fruit3.x == fruit1.x && fruit3.y == fruit1.y)
				|| (fruit3.x == fruit2.x && fruit3.y == fruit2.y)) {
			fruit3 = new Point(rand.nextInt(dim.height / SCALE),
					rand.nextInt(dim.height / SCALE));
		
		
		}
	}

	public void isCollected() {
		//Checks if fruit exists
		if (fruit1 != null || fruit2 != null || fruit3 != null) {

			//If head hits the fruit2 or fruit3 (i.e. the wrong answers)
			if (head.equals(fruit2) || head.equals(fruit3)) {
				//Decrement score by 2 points if they collect the wrong answer
				score -= 2;
				//Increments length of snake
				tailLength++;
				//Sets collected to be true
				collected = true;
				collectedFalse = true;
				fruitEaten = true;

				//If game is won
				if (isWon()) {
					//Move the point off the board (so not visible)
					fruit1.setLocation(100, 100);
					fruit2.setLocation(100, 100);
					fruit3.setLocation(100, 100);
				} else {
					//Draw new fruit
					drawFruit();
					//Places down the fruit
					lblMelon.setVisible(false);
					lblMelon.setBounds(fruit1.x*SCALE, (fruit1.y*SCALE)+120, Board.SCALE, Board.SCALE);
					lblMelon.setVisible(true);
					lblStrawberry.setVisible(false);
					lblStrawberry.setBounds(fruit2.x*SCALE, (fruit2.y*SCALE)+120, Board.SCALE, Board.SCALE);
					lblStrawberry.setVisible(true);
					lblPeach.setVisible(false);
					lblPeach.setBounds(fruit3.x*SCALE, (fruit3.y*SCALE)+120, Board.SCALE, Board.SCALE);
					lblPeach.setVisible(true);
					
					//Ensures that fruit does not spawn on snake
					fruitEqualPoint();
				}
			}
			//If the head is at the point of the fruit1 (i.e. if the head collides with the fruit) do this
			if (head.equals(fruit1)) {
				//Increment the score by 1
				score++;
				//Increments the length of the snake by 1
				tailLength++;
				//Sets collected to be true
				collected = true;
				collectedTrue = true;
				fruitEaten = true;

				//If game is won
				if (isWon()) {
					//Move the point off the board (so not visible)
					fruit1.setLocation(100, 100);
					fruit2.setLocation(100, 100);
					fruit3.setLocation(100, 100);
				} else {
					//Draw new fruit
					drawFruit();
					//Places down the fruit
					lblMelon.setVisible(false);
					lblMelon.setBounds(fruit1.x*SCALE, (fruit1.y*SCALE)+120, Board.SCALE, Board.SCALE);
					lblMelon.setVisible(true);
					lblStrawberry.setVisible(false);
					lblStrawberry.setBounds(fruit2.x*SCALE, (fruit2.y*SCALE)+120, Board.SCALE, Board.SCALE);
					lblStrawberry.setVisible(true);
					lblPeach.setVisible(false);
					lblPeach.setBounds(fruit3.x*SCALE, (fruit3.y*SCALE)+120, Board.SCALE, Board.SCALE);
					lblPeach.setVisible(true);
					
					//Ensures that fruit does not spawn on snake
					fruitEqualPoint();
					//Increments the numCorrect, when numCorrect is equal to 2, increment the numLimit by 5
					numCorrect++;
				}
			}
		}
	}

	//This method is used to create a new question
	public static void newQuestion() {
		//This declares an object q1 of Questions
		Questions q1 = new Questions();
		//Calls the method getQuestion for object q1 and assigns is to question
		question = q1.getQuestion();
		//This gets the correct answer to the question in q1
		corAns = q1.getCorrect();
		//This generates the first incorrect answer
		incorAns1 = q1.generateIncorrect();
		//This generates the second incorrect answer
		incorAns2 = q1.generateIncorrect();
	}
}