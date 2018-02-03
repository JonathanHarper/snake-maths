package snake;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
//This class is for the main menu screen
public class MainMenu extends JPanel implements ActionListener {

	//the JFrame which will contain the contents of the main menu screen
	public static JFrame mainMenuFrame;
	//This is the button used to view the instructions/help of the game
	public JButton help;
	//This is used to select the easy difficulty
	public JButton difficultyEasy;
	//This is used to select the medium difficulty
	public JButton difficultyMedium;
	//This is used to select the hard difficulty
	public JButton difficultyHard;
	//This button is used to open up the high scores page
	public JButton highScores;
	//This button is used to redirect the user to a page in order to enter their name
	public JButton play;
	//This is the background and text for the main menu screen
	public MainMenuComponent mainMenuComponent;

	//Constructor for the main menu
	public MainMenu() {
		// TODO Auto-generated method stub
		//New instance of MainMenuComponent called titleBackground.
		mainMenuComponent = new MainMenuComponent();

		//Makes a new instance of JFrame called 'mainMenuFrame', this window has the title "Snake - Main Menu"
		mainMenuFrame = new JFrame("Snake - Main Menu");
		//Makes a new button with text "Play"
		play = new JButton("Play");
		//Makes a new button with text "Easy"
		difficultyEasy = new JButton("Easy");
		//Makes a new button with text "Medium"
		difficultyMedium = new JButton("Medium");
		//Makes a new button with text "Hard"
		difficultyHard = new JButton("Hard");
		//Makes a new button with text "Instructions"
		help = new JButton("Instructions");
		//Makes a new button with text "High Scores"
		highScores = new JButton("High Scores");

		//Calls the buttons method
		buttons();

		//If the window is closed, the frame closes.
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Sets layout as BorderLayout
		mainMenuFrame.setLayout(new BorderLayout());
		//This stops the frame from being able to be resized.
		mainMenuFrame.setResizable(false);

		//Sets the font style as being button
		help.setFont(TitleScreen.button);
		highScores.setFont(TitleScreen.button);
		difficultyHard.setFont(TitleScreen.button);
		difficultyMedium.setFont(TitleScreen.button);
		difficultyEasy.setFont(TitleScreen.button);
		play.setFont(TitleScreen.button);

		//Adds the help button
		mainMenuFrame.add(help);
		//Adds the play button
		mainMenuFrame.add(play);
		//Adds the highScores button
		mainMenuFrame.add(highScores);
		//Adds the difficulty selection buttons
		mainMenuFrame.add(difficultyEasy);
		mainMenuFrame.add(difficultyMedium);
		mainMenuFrame.add(difficultyHard);
		//Adds the background component
		mainMenuFrame.add(mainMenuComponent);

		//Packs the frame to fit the components, in this case the background (600, 720)
		mainMenuFrame.pack();
		//Positions the frame in the middle of the screen
		mainMenuFrame.setLocationRelativeTo(null);
		//Sets the frame to be visible
		mainMenuFrame.setVisible(true);
		//The second pack method is called to ensure that border issues do not occur.
		mainMenuFrame.pack();
	}

	@SuppressWarnings({ "unused", "static-access" })
	@Override
	public void actionPerformed(ActionEvent e) {
		//Creating a new object 'b' and assigning it to the value of the ActionEvent (the action)
		Object b = e.getSource();
		//Compares the object to the button, if they are the same then do this
		//if button pressed is play
		if (b == play) {
			//Closes the mainMenuFrame
			this.mainMenuFrame.dispose();
			//Starts a new instance of the Board class constructor.
			NameScreen nameScreen = new NameScreen();
		}
		//if button pressed is difficultyEasy
		if (b == difficultyEasy) {
			//Sets difficulty to easy
			Questions.difficultyChosen = 0;
		}
		//if button pressed is difficultyMedium
		if (b == difficultyMedium) {
			//Sets difficulty to medium
			Questions.difficultyChosen = 1;
		}
		//if button pressed is difficultyHard
		if (b == difficultyHard) {
			//Sets difficulty to hard
			Questions.difficultyChosen = 2;
		}
		//if button pressed is help
		if (b == help) {
			//Closes the mainMenuFrame
			this.mainMenuFrame.dispose();
			//Starts a new instance of the Instructions class constructor.
			Instructions instruction = new Instructions();
		}
		//if button pressed is highScores
		if (b == highScores) {
			//Closes the mainMenuFrame
			this.mainMenuFrame.dispose();
			//The high scores screen is opened.
			HighscoresScreen HSScreen = new HighscoresScreen();
		}
	}

	//This is used to create the buttons on the main menu
	public void buttons() {
		//Sets the size of the button to be (300, 75)
		play.setPreferredSize(new Dimension(300, 75));
		//Sets the position of the button to be (150, 150)
		play.setBounds(150, 150, 300, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		play.addActionListener(this);

		//Sets the size of the button to be (90, 75)
		difficultyEasy.setPreferredSize(new Dimension(90, 75));
		//Sets the position of the button to be (150, 340)
		difficultyEasy.setBounds(150, 340, 90, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		difficultyEasy.addActionListener(this);

		//Sets the size of the button to be (90, 75)
		difficultyMedium.setPreferredSize(new Dimension(90, 75));
		//Sets the position of the button to be (265, 340)
		difficultyMedium.setBounds(265, 340, 80, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		difficultyMedium.addActionListener(this);

		//Sets the size of the button to be (90, 75)
		difficultyHard.setPreferredSize(new Dimension(90, 75));
		//Sets the position of the button to be (370, 340)
		difficultyHard.setBounds(370, 340, 80, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		difficultyHard.addActionListener(this);

		//Sets the size of the button to be (300, 75)
		help.setPreferredSize(new Dimension(300, 75));
		//Sets the position of the button to be (150, 465)
		help.setBounds(150, 465, 300, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		help.addActionListener(this);

		//Sets the size of the button to be (300, 75)
		highScores.setPreferredSize(new Dimension(300, 75));
		//Sets the position of the button to be (150, 590)
		highScores.setBounds(150, 590, 300, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		highScores.addActionListener(this);
	}
}