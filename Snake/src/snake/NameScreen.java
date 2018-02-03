package snake;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
//This class is used for the constructor to create a JFrame which is called when the 'Play' button on the main menu is selected. This will allow the user to input their name.
public class NameScreen extends JPanel implements ActionListener {

	//the JFrame which will contain the contents of the name page
	public JFrame nameFrame;
	//This is the button used to redirect the user back to the main menu
	public JButton backToMain;
	//This is the button is used to start the game
	public JButton finished;
	//This is used for the component of the NameScreen JFrame, this will add in the background and text.
	NameScreenComponent nameComp;
	//This is a text field which is used to input the users name
	public JTextField enterName;
	//This stores the characters input into the text box
	public static String inName;

	//Constructor to create the JFrame, this can be called as an instance.
	public NameScreen() {
		//New instance of NameScreenComponent called nameComp
		nameComp = new NameScreenComponent();

		//Makes a new instance of JFrame called 'nameFrame', this window has the title "Snake - Name Screen"
		nameFrame = new JFrame("Snake - Name Screen");
		//Makes a new button with text "Return to Main Menu"
		backToMain = new JButton("Return to Main Menu");
		//Makes a new instance of the button finished
		finished = new JButton("Finished");
		//This is the text field
		enterName = new JTextField();

		//Sets the size of the button to be (300, 75)
		backToMain.setPreferredSize(new Dimension(300, 75));
		//Sets the position of the button to be (150, 600)
		backToMain.setBounds(150, 600, 300, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		backToMain.addActionListener(this);

		//Sets the size of the button to be (300, 75)
		finished.setPreferredSize(new Dimension(300, 75));
		//Sets the position of the button to be (150, 500)
		finished.setBounds(150, 500, 300, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		finished.addActionListener(this);

		//Sets the fonts as being of font style button.
		backToMain.setFont(TitleScreen.button);
		finished.setFont(TitleScreen.button);

		//If the window is closed, the frame closes.
		nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Sets layout as BorderLayout
		nameFrame.setLayout(new BorderLayout());
		//This stops the frame from being able to be resized.
		nameFrame.setResizable(false);

		//Sets the size of the enterName JTextField as being (450, 50) and the position it is placed at as being (75, 350)
		enterName.setBounds(75, 350, 450, 50);
		//Sets it to be large font style
		enterName.setFont(QuestionBox.large);

		//Adds the text field to enter name
		nameFrame.add(enterName);
		//Adds the buttons
		nameFrame.add(backToMain);
		nameFrame.add(finished);
		//Adds the background component
		nameFrame.add(nameComp);

		//This associates the enterName JTextField with the text document of the instance of the class JTextFieldLimit and uses the limit of 12. This effectively means that the enterName JTextField has a limit of 12 characters.
		enterName.setDocument(new JTextFieldLimit(12));

		//Packs the frame to fit the components, in this case the background (600, 720)
		nameFrame.pack();
		//Positions the frame in the middle of the screen
		nameFrame.setLocationRelativeTo(null);
		//Sets the frame to be visible
		nameFrame.setVisible(true);

		//Second pack is called to fix border issues
		nameFrame.pack();
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent arg2) {
		// TODO Auto-generated method stub
		//Creating a new object 'd' and assigning it to the value of the ActionEvent (the action)
		Object d = arg2.getSource();
		//Compares the object to the button, if they are the same then do this
		//if button pressed is backToMain
		if (d == backToMain) {
			//Closes the JFrame
			nameFrame.dispose();
			//Opens the main menu
			MainMenu mainMenu = new MainMenu();
		}
		//If finished button is pressed
		if (d == finished) {
			//If the text field has only blank spaces inserted (or nothing at all)
			if (enterName.getText().trim().isEmpty()) {
				//The input name equals "Anonymous"
				inName = "Anonymous";
			} else {
				//Set the name to the characters which were input
				inName = enterName.getText();
			}
			//Closes the JFrame
			nameFrame.dispose();
			//Starts a new game
			Board board = new Board();
		}
	}
}