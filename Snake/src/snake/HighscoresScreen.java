package snake;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
//This is the class in which the high scores shall appear
public class HighscoresScreen extends JPanel implements ActionListener {

	//the JFrame which will contain the contents of the high-scores screen
	public JFrame HSFrame;

	//This is the button used to redirect the user back to the main menu
	public JButton backToMain;
	//These buttons are used to select the score achieved for the difficulty selected, effectively this means we have 3 tables representing the 3 different difficulties, pressing one of these buttons makes the relevant one appear.
	public JButton diffEasy;
	public JButton diffMedium;
	public JButton diffHard;

	//This is used for the component of the HighscoresScreen JFrame
	HighscoresScreenComponent HSComp;

	//This creates a JLabel which will hold the name
	public static JLabel HSlbl1;
	//This creates a JLabel which will hold the score
	public JLabel HSlbl2;
	//This creates a JLabel which will hold the positions
	public JLabel HSlbl3;
	//This creates a JLabel which will display a message reading "Select a button to view the scores achieved at that difficulty"
	public JLabel HSlbl;

	//These are instances of the classes, this can be used to call methods within the class related to that specific instance.
	static HighscoreManagerEasy hm = new HighscoreManagerEasy();
	static HighscoreManagerMedium hm1 = new HighscoreManagerMedium();
	static HighscoreManagerHard hm2 = new HighscoreManagerHard();

	//This is the constructor, the will create the JFrame and add the components onto it.
	HighscoresScreen() {
		//New instance of HighscoresScreenComponent called HSComp.
		HSComp = new HighscoresScreenComponent();

		//Makes a new instance of JFrame called 'HSFrame', this window has the title "Snake - High Scores"
		HSFrame = new JFrame("Snake - High Scores");
		//Makes a new button with text "Return to Main Menu"
		backToMain = new JButton("Return to Main Menu");

		//Assigns the JLabel HSlbl to have "Select a button to view the scores achieved at that difficulty", the text is centred horizontally).
		HSlbl = new JLabel(
				"Select a button to view the scores achieved at that difficulty",
				SwingConstants.CENTER);
		//Makes HSlbl1 and HSlbl2 equal to JLabels, HSlbl1 shall store the name, while HSlbl2 stores the score.
		HSlbl1 = new JLabel();
		HSlbl2 = new JLabel();
		//This is used to store the position, as this will never chance, we can simply use no variables and a set JLabel.
		HSlbl3 = new JLabel(
				"<html><div style=\"text-align: center;\"> Position" + "<br>"
						+ "<br>" + "1" + "<br>" + "<br>" + "2" + "<br>"
						+ "<br>" + "3" + "<br>" + "<br>" + "4" + "<br>"
						+ "<br>" + "5" + "<br>" + "<br>" + "6" + "<br>"
						+ "<br>" + "7" + "<br>" + "<br>" + "8" + "<br>"
						+ "<br>" + "9" + "<br>" + "<br>" + "10" + "<html>");
		//Buttons to select in order to view the top 10 scores achieved for that difficulty.
		//Easy
		diffEasy = new JButton("Easy");
		//Medium
		diffMedium = new JButton("Medium");
		//Hard
		diffHard = new JButton("Hard");

		//Sets the size of the button to be (300, 75)
		backToMain.setPreferredSize(new Dimension(300, 75));
		//Sets the position of the button to be (150, 640)
		backToMain.setBounds(150, 640, 300, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		backToMain.addActionListener(this);
		//Sets the font style as being button
		backToMain.setFont(TitleScreen.button);

		//Sets the size of the button to be (180, 75)
		diffEasy.setPreferredSize(new Dimension(180, 75));
		//Sets the position of the button to be (15, 550)
		diffEasy.setBounds(15, 550, 180, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		diffEasy.addActionListener(this);
		//Sets the font style as being button
		diffEasy.setFont(TitleScreen.button);

		//Sets the size of the button to be (180, 75)
		diffMedium.setPreferredSize(new Dimension(180, 75));
		//Sets the position of the button to be (210, 550)
		diffMedium.setBounds(210, 550, 180, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		diffMedium.addActionListener(this);
		//Sets the font style as being button
		diffMedium.setFont(TitleScreen.button);

		//Sets the size of the button to be (180, 75)
		diffHard.setPreferredSize(new Dimension(180, 75));
		//Sets the position of the button to be (405, 550)
		diffHard.setBounds(405, 550, 180, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		diffHard.addActionListener(this);
		//Sets the font style as being button
		diffHard.setFont(TitleScreen.button);

		//If the window is closed, the frame closes.
		HSFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Sets the size of the button to be (600, 500)
		HSlbl.setPreferredSize(new Dimension(600, 500));
		//Sets the position of the button to be (0, 75)
		HSlbl.setBounds(0, 75, 600, 500);
		//Sets the font style as being button
		HSlbl.setFont(QuestionBox.medium);
		//The label is set to visible upon startup, this means that the message "Select a button to view the scores achieved at that difficulty" shall initially appear when the high scores screen is opened.
		HSlbl.setVisible(true);

		//Sets the size of the button to be (600, 500)
		HSlbl1.setPreferredSize(new Dimension(600, 500));
		//Sets the position of the button to be (250, 60)
		HSlbl1.setBounds(250, 60, 600, 500);
		//Sets the font style as being medium
		HSlbl1.setFont(QuestionBox.medium);
		//These labels are initially set to false in order to show the message HSlbl
		HSlbl1.setVisible(false);

		//Sets the size of the button to be (200, 500)
		HSlbl2.setPreferredSize(new Dimension(200, 500));
		//Sets the position of the button to be (450, 60)
		HSlbl2.setBounds(450, 60, 200, 500);
		//Sets the font style as being medium
		HSlbl2.setFont(QuestionBox.medium);
		//These labels are initially set to false in order to show the message HSlbl
		HSlbl2.setVisible(false);

		//Sets the size of the button to be (200, 500)
		HSlbl3.setPreferredSize(new Dimension(200, 500));
		//Sets the position of the button to be (80, 60)
		HSlbl3.setBounds(80, 60, 200, 500);
		//Sets the font style as being medium
		HSlbl3.setFont(QuestionBox.medium);
		//These labels are initially set to false in order to show the message HSlbl
		HSlbl3.setVisible(false);

		//Sets layout as a new BorderLayout
		HSFrame.setLayout(new BorderLayout());
		//This stops the frame from being able to be resized.
		HSFrame.setResizable(false);

		//Adds the buttons
		HSFrame.add(backToMain);
		HSFrame.add(diffEasy);
		HSFrame.add(diffMedium);
		HSFrame.add(diffHard);

		//Adds the JLabels
		HSFrame.add(HSlbl);
		HSFrame.add(HSlbl1);
		HSFrame.add(HSlbl2);
		HSFrame.add(HSlbl3);

		//Adds the background component
		HSFrame.add(HSComp);

		//Packs the frame to fit the components, in this case the background (600, 720)
		HSFrame.pack();
		//Positions the frame in the middle of the screen
		HSFrame.setLocationRelativeTo(null);
		//Sets the frame to be visible
		HSFrame.setVisible(true);

		//Second pack is called to fix border issues
		HSFrame.pack();
	}

	//This method is used to add scores if the game is either won or lost.
	public static void isBoardLostOrWon() {
		//If the game is either won or lost
		if (Board.lost || Board.won) {
			//If the difficulty chosen is easy and the setPoints is true. setPoints is used in order to ensure that the game has not just started (i.e. the initial timer is still not running), this variable was used to ensure that the game will not record the score despite the user not having actually moved the snake.
			if (Questions.difficultyChosen == 0 && Board.setPoints == true) {
				//The instance hm uses the method addScore, this is then passed the name and score. Therefore, the score shall be added to the scores.dat file
				hm.addScore(NameScreen.inName, Board.score);
			}
			//If the difficulty chosen is medium and the setPoints is true. setPoints is used in order to ensure that the game has not just started (i.e. the initial timer is still not running), this variable was used to ensure that the game will not record the score despite the user not having actually moved the snake.
			if (Questions.difficultyChosen == 1 && Board.setPoints == true) {
				//The instance hm1 uses the method addScore, this is then passed the name and score. Therefore, the score shall be added to the scores1.dat file
				hm1.addScore(NameScreen.inName, Board.score);
			}
			//If the difficulty chosen is hard and the setPoints is true. setPoints is used in order to ensure that the game has not just started (i.e. the initial timer is still not running), this variable was used to ensure that the game will not record the score despite the user not having actually moved the snake.
			if (Questions.difficultyChosen == 2 && Board.setPoints == true) {
				//The instance hm2 uses the method addScore, this is then passed the name and score. Therefore, the score shall be added to the scores2.dat file
				hm2.addScore(NameScreen.inName, Board.score);
			}
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent arg3) {
		// TODO Auto-generated method stub
		Object o = arg3.getSource();
		//Compares the object to the button, if they are the same then do this
		//if button pressed is backToMain
		if (o == backToMain) {
			//Closes the JFrame
			HSFrame.dispose();
			//Opens the main menu
			MainMenu mainMenu = new MainMenu();
		}
		//If the diffEasy button is pressed
		if (o == diffEasy) {
			//Set the text of HSlbl1 to display the names of the top 10 scores for the easy difficulty
			HSlbl1.setText(hm.getName());
			//Set the text of HSlbl2 to display the scores of the top 10 scores for the easy difficulty
			HSlbl2.setText(hm.getScore());
			//Sets the label to being false, as this is no longer needed, this is set to false in order to view the table
			HSlbl.setVisible(false);
			//These are set to true, this will show the table
			HSlbl1.setVisible(true);
			HSlbl2.setVisible(true);
			HSlbl3.setVisible(true);
		}
		//If button pressed is diffMedium
		if (o == diffMedium) {
			//Set the text of HSlbl1 to display the names of the top 10 scores for the medium difficulty
			HSlbl1.setText(hm1.getName());
			//Set the text of HSlbl2 to display the scores of the top 10 scores for the medium difficulty
			HSlbl2.setText(hm1.getScore());
			//Sets the label to being false, as this is no longer needed, this is set to false in order to view the table
			HSlbl.setVisible(false);
			//These are set to true, this will show the table
			HSlbl1.setVisible(true);
			HSlbl2.setVisible(true);
			HSlbl3.setVisible(true);
		}
		//If button pressed is diffHard
		if (o == diffHard) {
			//Set the text of HSlbl1 to display the names of the top 10 scores for the hard difficulty
			HSlbl1.setText(hm2.getName());
			//Set the text of HSlbl2 to display the scores of the top 10 scores for the hard difficulty
			HSlbl2.setText(hm2.getScore());
			//Sets the label to being false, as this is no longer needed, this is set to false in order to view the table
			HSlbl.setVisible(false);
			//These are set to true, this will show the table
			HSlbl1.setVisible(true);
			HSlbl2.setVisible(true);
			HSlbl3.setVisible(true);
		}
	}
}
