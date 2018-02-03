package snake;

import java.io.Serializable;

//This class allows us to get the name and the score, this can then be used to make an object.
@SuppressWarnings("serial")
//We implement Serializable in order to ensure that the we can create the object once the data has been added, it is required to add the object to the file. This is due to the way in which Serializable works, it converts the data into byestreams, these can then be deserialized in order to convert back into a object.
public class Score implements Serializable {
	//This stores the score attained
	private int score;
	//This stores the name entered
	private String name;

	//Method in order to get the score which was attained
	public int getScore() {
		return score;
	}

	//Method to get the name which was entered
	public String getName() {
		return name;
	}

	//Constructor which has the parameters name and score, assigns the name and score to this instance.
	public Score(String name, int score) {
		this.score = score;
		this.name = name;
	}
}