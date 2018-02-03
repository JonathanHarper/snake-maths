package snake;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
//What this class will do is hold the JFrame of the instructions page which can be accessed by clicking on the Instructions button on the main menu page.
public class Instructions extends JPanel implements ActionListener {

	//the JFrame which will contain the contents of the instructions page
	public JFrame instructionsFrame;
	//This is the button used to redirect the user back to the main menu
	public JButton backToMain;
	//This is used for the component of the Instructions JFrame
	InstructionsComponent inComp;

	public Instructions() {
		//New instance of InstructionsComponent called inComp.
		inComp = new InstructionsComponent();

		//Makes a new instance of JFrame called 'instructionsFrame', this window has the title "Snake - Instructions Screen"
		instructionsFrame = new JFrame("Snake - Instructions Screen");
		//Makes a new button with text "Return to Main Menu"
		backToMain = new JButton("Return to Main Menu");

		//Sets the size of the button to be (300, 75)
		backToMain.setPreferredSize(new Dimension(300, 75));
		//Sets the position of the button to be (150, 640)
		backToMain.setBounds(150, 640, 300, 75);
		//Adds a action listener to the button, this will be able to use any actions which relate to it, in this case clicking the button.
		backToMain.addActionListener(this);
		//Sets the font style as being button
		backToMain.setFont(TitleScreen.button);
		//If the window is closed, the frame closes.
		instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Sets layout as BorderLayout
		instructionsFrame.setLayout(new BorderLayout());
		//This stops the frame from being able to be resized.
		instructionsFrame.setResizable(false);

		//Adds the button
		instructionsFrame.add(backToMain);
		//Adds the background component
		instructionsFrame.add(inComp);

		//Packs the frame to fit the components, in this case the background (600, 720)
		instructionsFrame.pack();
		//Positions the frame in the middle of the screen
		instructionsFrame.setLocationRelativeTo(null);
		//Sets the frame to be visible
		instructionsFrame.setVisible(true);

		//Second pack is called to fix border issues
		instructionsFrame.pack();
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent arg1) {
		// TODO Auto-generated method stub
		//Creating a new object 'c' and assigning it to the value of the ActionEvent (the action)
		Object c = arg1.getSource();
		//Compares the object to the button, if they are the same then do this
		//if button pressed is backToMain
		if (c == backToMain) {
			//Closes the instructionsFrame
			this.instructionsFrame.dispose();
			//Starts a new instance of the MainMenu class constructor.
			MainMenu mainMenu = new MainMenu();
		}
	}
}