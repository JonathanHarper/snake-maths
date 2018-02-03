package snake;

import java.util.*;
import java.io.*;

//This is where the bulk of the processing of the scores occurs for the easy difficulty.
public class HighscoreManagerEasy {
	// An ArrayList of the type Score, we will use this to store the scores
	private ArrayList<Score> scores;

	//This is where all of the stores shall be saved, a .dat file has been used for encryption, meaning that the user will be unable to directly edit the scores. This file is used for the easy difficulty.
	private static final String HIGHSCORE_FILE = "scores.dat";

	//Initialising an in and outputStream for working with the file
	//This is used to input and output data to the file. Input stream is used for reading, output stream is used for writing
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	//Constructor, this will create the arraylist.
	public HighscoreManagerEasy() {
		//initialising the scores-arraylist
		scores = new ArrayList<Score>();
	}

	//This method is used to return the scores arraylist
	public ArrayList<Score> getScores() {
		//This will load the file
		loadScoreFile();
		//This will sort the scores in the file
		sort();
		return scores;
	}

	//This method is used to sort the scores from 1-10
	private void sort() {
		//Creates a new instance of the ScoreComparator class
		ScoreComparator comparator = new ScoreComparator();
		//This is used to sort the ArrayList scores by use of the comparator.
		Collections.sort(scores, comparator);
	}

	//This method is used to add the scores to the scores.dat file
	public void addScore(String name, int score) {
		//Loads the file
		loadScoreFile();
		//The scores are being added
		scores.add(new Score(name, score));
		//Updates the scores.dat file
		updateScoreFile();
	}

	//This is used to load the arraylist that is in the scores.dat file into the 'scores' arraylist
	@SuppressWarnings("unchecked")
	public void loadScoreFile() {
		//The try/catch structures are used in order to prevent crashes, if for example the file doesn't exist (the file is created upon restarting the application if no file exists) an error shall be output instead of a crash.
		try {
			//The input stream allows for the reading of objects from the file
			//reading the highscore file
			inputStream = new ObjectInputStream(new FileInputStream(
					HIGHSCORE_FILE));
			//Adding objects from the file to the scores arraylist
			scores = (ArrayList<Score>) inputStream.readObject();
			//If file is not found
		} catch (FileNotFoundException e) {
			//output message
			System.out.println("[Load] FNF Error: " + e.getMessage());
			//failure of interrupted operations of I/O.
		} catch (IOException e) {
			//If the size of scores arraylist is not zero
			if (scores.size() != 0) {
				//Output message
				System.out.println("[Load] IO Error: " + e.getMessage());
			}
			//thrown if problems are had trying to access class, for example the classpath is broken
		} catch (ClassNotFoundException e) {
			//output message
			System.out.println("[Load] CNF Error: " + e.getMessage());
			//The finally block always executes when the try block exits, this will ensure that the finally block is entered even if an error occurs
		} finally {
			try {
				//Checks if outputstream is true (file exists)
				if (outputStream != null) {
					//This will ensure that the computers buffer doesn't get too full by executing the streams of data completely
					outputStream.flush();
					//Closes the stream
					outputStream.close();
				}
				//failure of interrupted operations of I/O.
			} catch (IOException e) {
				//output message
				System.out.println("[Laad] IO Error: " + e.getMessage());
			}
		}
	}

	//This does the same as the loadScoreFile method but instead of reading from the file and assigning it to the scores arraylist, this instead reads from the source arraylist and writes this to the file.
	public void updateScoreFile() {
		//The try/catch structures are used in order to prevent crashes, if for example the file doesn't exist (the file is created upon restarting the application if no file exists) an error shall be output instead of a crash.
		try {
			//This outputStream is used to write the scores object to the file.
			outputStream = new ObjectOutputStream(new FileOutputStream(
					HIGHSCORE_FILE));
			//This writes the scores object to the outputStream, in other words to the file 
			outputStream.writeObject(scores);
			//If file is not found
		} catch (FileNotFoundException e) {
			//Output message, if the file is not found, the program will make a new file
			System.out.println("[Update] FNF Error: " + e.getMessage()
					+ ",the program will try and make a new file");
			//failure of interrupted operations of I/O.
		} catch (IOException e) {
			//Output message
			System.out.println("[Update] IO Error: " + e.getMessage());
			//The finally block always executes when the try block exits, this will ensure that the finally block is entered even if an error occurs
		} finally {
			try {
				//Checks if outputstream is true (file exists)
				if (outputStream != null) {
					//This will ensure that the computers buffer doesn't get too full by executing the streams of data completely
					outputStream.flush();
					//Closes the stream
					outputStream.close();
				}
				//failure of interrupted operations of I/O.
			} catch (IOException e) {
				//output message
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	//This method is used to write the default values into the scores.dat file
	public void getDefault() {
		ArrayList<Score> scores;
		//the score which is attained is put into the arraylist
		scores = getScores();

		//If the arraylist has a size of 0
		if (scores.size() == 0) {
			//Add the default scores as shown below
			HighscoresScreen.hm.addScore("Jessica", 30);
			HighscoresScreen.hm.addScore("Alex", 23);
			HighscoresScreen.hm.addScore("Georgina", 20);
			HighscoresScreen.hm.addScore("George", 14);
			HighscoresScreen.hm.addScore("Sophie", 10);
			HighscoresScreen.hm.addScore("Kim", 7);
			HighscoresScreen.hm.addScore("Tom", 2);
			HighscoresScreen.hm.addScore("Lucy", 0);
			HighscoresScreen.hm.addScore("Matthew", -5);
			HighscoresScreen.hm.addScore("Ben", -10);
		}
	}

	//This method is used in order to get the name of the player in the high score table, this will allow for the JLabel to be updated accordingly.
	public String getName() {
		ArrayList<Score> scores;
		//the score which is attained is put into the arraylist
		scores = getScores();

		//Declaring and initialising variables of type String which will contain the names of the players in positions 1-10
		String pos1 = null;
		String pos2 = null;
		String pos3 = null;
		String pos4 = null;
		String pos5 = null;
		String pos6 = null;
		String pos7 = null;
		String pos8 = null;
		String pos9 = null;
		String pos10 = null;

		//Assigning the name of the player in a position within the arraylist to the variables
		pos1 = scores.get(0).getName();
		pos2 = scores.get(1).getName();
		pos3 = scores.get(2).getName();
		pos4 = scores.get(3).getName();
		pos5 = scores.get(4).getName();
		pos6 = scores.get(5).getName();
		pos7 = scores.get(6).getName();
		pos8 = scores.get(7).getName();
		pos9 = scores.get(8).getName();
		pos10 = scores.get(9).getName();

		//Returns the string of Name and the names from 1-10, with 1 being above 2, which is above 3 etc. In order to format the returned string I needed to use html, "<br>" allows for the insertion of a new line in a JLabel. This formatting also means that the names, position and scores shall all be aligned.
		return ("<html> Name" + "<br>" + "<br>" + pos1 + "<br>" + "<br>" + pos2
				+ "<br>" + "<br>" + pos3 + "<br>" + "<br>" + pos4 + "<br>"
				+ "<br>" + pos5 + "<br>" + "<br>" + pos6 + "<br>" + "<br>"
				+ pos7 + "<br>" + "<br>" + pos8 + "<br>" + "<br>" + pos9
				+ "<br>" + "<br>" + pos10);
	}

	//This method is used to get the score of the player in a position from 1-10, this is then output to a JLabel.
	public String getScore() {
		ArrayList<Score> scores;
		//the score which is attained is put into the arraylist
		scores = getScores();

		//Declaring and initialising variables of type int, these are used to store the scores of the players between and including position 1-10
		int pos1;
		int pos2;
		int pos3;
		int pos4;
		int pos5;
		int pos6;
		int pos7;
		int pos8;
		int pos9;
		int pos10;

		//Assigns the value of the variables to the score achieved by the players from positions 1-10
		pos1 = scores.get(0).getScore();
		pos2 = scores.get(1).getScore();
		pos3 = scores.get(2).getScore();
		pos4 = scores.get(3).getScore();
		pos5 = scores.get(4).getScore();
		pos6 = scores.get(5).getScore();
		pos7 = scores.get(6).getScore();
		pos8 = scores.get(7).getScore();
		pos9 = scores.get(8).getScore();
		pos10 = scores.get(9).getScore();

		//Returns formatted text similar to that of the name, this also makes use of html code which is necessary in order to format the text to be placed in a JLabel.
		return ("<html> Score" + "<br>" + "<br>" + pos1 + "<br>" + "<br>"
				+ pos2 + "<br>" + "<br>" + pos3 + "<br>" + "<br>" + pos4
				+ "<br>" + "<br>" + pos5 + "<br>" + "<br>" + pos6 + "<br>"
				+ "<br>" + pos7 + "<br>" + "<br>" + pos8 + "<br>" + "<br>"
				+ pos9 + "<br>" + "<br>" + pos10);
	}
}