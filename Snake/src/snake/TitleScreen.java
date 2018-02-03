package snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
//This class is used to deal with the title screen.
public class TitleScreen extends JPanel implements ActionListener {

	//the JFrame which will contain the contents of the title screen
	public JFrame titleFrame;
	//This is the button used to progress the game onto the main menu
	public JButton titleProgress;
	//This is the background and text for the title screen.
	public TitleScreenComponent titleBackground;

	//This is the font style button, this is used for the buttons text
	public static Font button = new Font("Arial", Font.BOLD, 12);

	//This is instance hm of HighscoreManagerEasy. What this shall do is allow us to access the methods within the class.
	static HighscoreManagerEasy hm = new HighscoreManagerEasy();
	//This is instance hm1 of HighscoreManagerMedium. What this shall do is allow us to access the methods within the class.
	static HighscoreManagerMedium hm1 = new HighscoreManagerMedium();
	//This is instance hm2 of HighscoreManagerHard. What this shall do is allow us to access the methods within the class.
	static HighscoreManagerHard hm2 = new HighscoreManagerHard();

	//Constructor for TitleScreen.
	public TitleScreen() {
		//New instance of TitleScreenComponent called titleBackground.
		titleBackground = new TitleScreenComponent();

		//Makes a new instance of JFrame called 'titleFrame', this window has the title "Snake - Title Screen"
		titleFrame = new JFrame("Snake - Title Screen");
		//Makes a new button with text "Main Menu"
		titleProgress = new JButton("Main Menu");

		//The method getDefault() writes the default scores into the relevant high scores file, depending on if there are no scores contained within the file. This shall test whether or not the scores.dat, scores1.dat or scores2.dat files are empty, if they are it shall add the scores to the relevant file, depending on what file is empty.
		//The reason as to why this is ran at the start is because I need to ensure that, for example, the user can play the game immediately upon startup. If this method was called in the HighscoresScreen, if the user where to gain a score, the size of the file would no longer be zero and because of this there would be an error; therefore, it is best to place it in the TitleScreen because it will ensure no errors occur.
		//hm refers to easy, therefore scores.dat
		hm.getDefault();
		//hm1 refers to medium, therefore scores1.dat
		hm1.getDefault();
		//hm2 refers to hard, therefore scores2.dat
		hm2.getDefault();

		//Sets the size of the button to be (300, 75)
		titleProgress.setPreferredSize(new Dimension(300, 75));
		//Sets the position of the button to be (150, 550)
		titleProgress.setBounds(150, 550, 300, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		titleProgress.addActionListener(this);

		//If the window is closed, the frame closes.
		titleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Sets layout as BorderLayout
		titleFrame.setLayout(new BorderLayout());
		//This stops the frame from being able to be resized.
		titleFrame.setResizable(false);

		//Sets the font style as being button.
		titleProgress.setFont(button);

		//Adds the button
		titleFrame.add(titleProgress);
		//Adds the background component
		titleFrame.add(titleBackground);

		//Packs the frame to fit the components, in this case the background (600, 720)
		titleFrame.pack();
		//Positions the frame in the middle of the screen
		titleFrame.setLocationRelativeTo(null);
		//Sets the frame to be visible
		titleFrame.setVisible(true);

		//Second pack is called to fix border issues
		titleFrame.pack();
	}

	@SuppressWarnings("unused")
	@Override
	//This is invoked when an action is performed
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//Creating a new object 'a' and assigning it to the value of the ActionEvent (the action)
		Object a = arg0.getSource();
		//Compares the object to the button, if they are the same then do this
		if (a == titleProgress) {
			//Closes the titleFrame
			this.titleFrame.dispose();
			//Starts a new instance of the MainMenu class constructor.
			MainMenu mainMenu = new MainMenu();
		}
	}
}