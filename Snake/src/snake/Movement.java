package snake;

import java.awt.Point;

//This class controls the movement of the snake. It allows for the question timer to be implemented.
public class Movement implements Runnable {

	//This is used to ensure that the snake cannot change direction when the snake is paused in the question timer. It is also used to ensure that the messages, such as "Correct!" are displayed for the duration of the pause.
	public static boolean timerFinish;
	//This is set to true if the correct answer is collected, this allows for the message "Correct!" to be displayed for the correct duration of the sleep
	public static boolean collectCorrectAnswer;
	//This is set to true if the incorrect answer is collected, this allows for the message "Incorrect" to be displayed for the correct duration of the sleep
	public static boolean collectIncorrectAnswer;

	@Override
	public void run() {
		// TODO Auto-generated method stub

		//If the game has just started (this includes when the game is restarted)
		if (Board.isJustStarted) {
			//This calls the method diffChosen, giving the difficulty which has been chosen and making the thread sleep for the designated time
			diffChosen();
			//Sets the isJustStarted to false once the thread has slept
			Board.isJustStarted = false;
		}

		//While the game is running
		while (Board.isStarted) {
			//Call movement method
			movement();

			try {
				//Make the thread sleep every 125 ms (milli seconds), the reason I have done this is because it will loop around the while loop while the game is running to ensure that the snake moves every 125ms.
				Thread.sleep(125);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//If the game is lost or won
			if (Board.lost || Board.won) {
				//Set the isStarted to false, so the snake will not move any longer
				Board.isStarted = false;
			}
			//If the fruit is eaten
			if (Board.fruitEaten) {
				//If the fruit that was eaten contains the correct answer (fruit1)
				if (Board.collectedTrue) {
					//Sets collectCorrectAnswer to true, this will allow the "Correct!" message to appear
					collectCorrectAnswer = true;
					//Sets the collectedTrue to false, this will mean that it wont repeat this loop
					Board.collectedTrue = false;
				}
				//If the snake collects fruit2 or fruit3, containing the incorrect answers
				if (Board.collectedFalse) {
					////Sets collectIncorrectAnswer to true, this will allow the "Incorrect" message to appear
					collectIncorrectAnswer = true;
					//Sets the collectedFalse to false, this will mean that it wont repeat this loop
					Board.collectedFalse = false;
				}
				//This checks if the board has been won
				if (!Board.won) {
					//This will stop the thread for the specified time if one of the fruit is collected. This allows for the game to pause and display the words "Correct!" or "Incorrect" to be displayed when a fruit is collected.
					diffChosen();
					//Sets the variables to false when finished as to not repeat the loop.
					collectCorrectAnswer = false;
					collectIncorrectAnswer = false;
					Board.fruitEaten = false;
				}
			}
		}
	}

	//This is used to make the thread sleep depending on the difficulty level selected
	public void diffChosen() {
		//if easy
		if (Questions.difficultyChosen == 0) {
			//timerFinish is equal to true. This variable allows for the time to be displayed on the screen for the correct amount of time before it is taken away, this variable is also used to ensure that the snake cannot change direction while the timer is up
			timerFinish = true;
			try {
				//Makes the thread sleep for 3000ms or 3 seconds
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Sets timerFinish to false
			timerFinish = false;
		}
		//If medium
		if (Questions.difficultyChosen == 1) {
			timerFinish = true;
			try {
				//Makes the thread sleep for 5000ms or 5 seconds
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timerFinish = false;
		}
		//If hard
		if (Questions.difficultyChosen == 2) {
			timerFinish = true;
			try {
				//Makes the thread sleep for 6000ms or 6 seconds
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timerFinish = false;
		}
	}

	public static void movement() {
		//Perform this if statement if head has a value (in other words if head exists and therefore the game hasn't been lost) and the game isn't lost or paused, also checks to see if the game has been won, if it has then no movement will occur.
		if (Board.head != null && !Board.lost && !Board.paused
				&& !Board.isWon()) {
			//Adds a snakeBody point where the previous position of the head was.
			Board.snakeBody.add(new Point(Board.head.x, Board.head.y));
			//If the snake is facing upwards
			if (Board.direction == Board.UP)
				//If the head is on the board (i.e. not off the boundaries), the head will not move off the board in the next tick and the head will not collide with the snakeBody bits.
				if (Board.head.y - 1 >= 0
						&& Board.noTailAt(Board.head.x, Board.head.y - 1))
					//move the head to a new point below the current position of the head
					Board.head = new Point(Board.head.x, Board.head.y - 1);
				else {
					//If the snakes head is off the board, end the game.
					Board.lost = true;
					HighscoresScreen.isBoardLostOrWon();
				}

			//If the snake is facing downwards
			if (Board.direction == Board.DOWN)
				//If the head is on the board (i.e. not off the boundaries), the head will not move off the board in the next tick and the head will not collide with the snakeBody bits.
				if (Board.head.y + 1 < (Board.dim.height / Board.SCALE)
						&& Board.noTailAt(Board.head.x, Board.head.y + 1))
					//move the head to a new point above the current position of the head
					Board.head = new Point(Board.head.x, Board.head.y + 1);
				else {
					//If the snakes head is off the board, end the game.
					Board.lost = true;
					HighscoresScreen.isBoardLostOrWon();
				}

			//If the snake is facing leftwards
			if (Board.direction == Board.LEFT)
				//If the head is on the board (i.e. not off the boundaries), the head will not move off the board in the next tick and the head will not collide with the snakeBody bits.
				if (Board.head.x - 1 >= 0
						&& Board.noTailAt(Board.head.x - 1, Board.head.y))
					//move the head to a new point to the left of the current position of the head
					Board.head = new Point(Board.head.x - 1, Board.head.y);
				else {
					//If the snakes head is off the board, end the game.
					Board.lost = true;
					HighscoresScreen.isBoardLostOrWon();
				}

			//If the snake is facing rightwards
			if (Board.direction == Board.RIGHT)
				//If the head is on the board (i.e. not off the boundaries), the head will not move off the board in the next tick and the head will not collide with the snakeBody bits.
				if (Board.head.x + 1 < (Board.dim.width / Board.SCALE)
						&& Board.noTailAt(Board.head.x + 1, Board.head.y))
					////move the head to a new point to the right of the current position of the head
					Board.head = new Point(Board.head.x + 1, Board.head.y);
				else {
					//If the snakes head is off the board, end the game.
					Board.lost = true;
					HighscoresScreen.isBoardLostOrWon();
				}

			//Checks if the size of the snakeBody linked list is larger than the tailLength. In other words, it checks to see if the last element needs to be removed due to the length of the snake
			if (Board.snakeBody.size() > Board.tailLength)
				//This removes the last point (we are constantly adding points so by removing the last point we show movement).
				Board.snakeBody.remove(0);
		}
	}
}
