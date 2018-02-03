package snake;

import java.util.Comparator;

//This class is used to compare/test the scores
public class ScoreComparator implements Comparator<Score> {

	//Compare method, with parameters score1 and score2 which are instances of the Score class.
	public int compare(Score score1, Score score2) {

		//sc1 is equal to the value of score1
		int sc1 = score1.getScore();
		//sc2 is equal to the value of score2
		int sc2 = score2.getScore();

		//If score1 is larger than score2
		if (sc1 > sc2) {
			//Return -1, what this represents is that the first score is larger than the second score.
			return -1;
			//If score1 is less than score 2
		} else if (sc1 < sc2) {
			//Return 1, this indicates that score1 is smaller than score2
			return +1;
		} else {
			//Return 0, this represents that they are both of equal value
			return 0;
		}
	}
}