
//Imports 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import java.io.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//Main class 
public class Board extends JPanel implements KeyListener, ActionListener, LineListener {

	//Timer 
	private Timer gametimer = new Timer(250, this);

	//An array of 
	private Cell[][] mazearray = new Cell[25][27]; //The mazearray, which 
	int pelletsEaten = 0;
	
	//pacMan mover 
	private Mover pacMan;

	//Array of type mover for all the ghosts in the game 
	private Mover[] ghostArray = new Mover[3];

	//Counts the number of pellets 
	private int pellets = 0;

	//Keeps track of the score 
	private int score = 0;

	//Keeps track of the number of lives left, initialised to 3 
	private int livesLeft = 3;

	//The score panel which displays the score but also the high score and number of lives left 
	pacManScore theScorePanel = new pacManScore();

	//Constructor 
	public Board() {

		//Set the layout to the grid layout 
		setLayout(new GridLayout(25, 27));
		
		//Set the background to black 
		setBackground(Color.BLACK);

		//Calls the method which plays the song, and cataches the IOException errors and LineUnavailableException errors 
		try {
			playSong();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Calls the loadBoard method 
		loadBoard();
	}

	// Song is called Electroman Adventures,
	// by Iron man and that spaceship pilot
	// https://www.youtube.com/watch?v=7w8k94DWlrI

	
	//Method that plays the music 
	private void playSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Clip musicClip;
		AudioInputStream musicStream;
		musicStream = AudioSystem.getAudioInputStream(new File("Electroman.wav").getAbsoluteFile());
		musicClip = AudioSystem.getClip();
		musicClip.open(musicStream);
		musicClip.loop(Clip.LOOP_CONTINUOUSLY);

		// Code source: https://www.geeksforgeeks.org/play-audio-file-using-java/

	}

	//the method which makes the board look like the pac-man maze 
	private void loadBoard() {
		
		//Row count=0
		int row = 0;

		//Scanner input that'll be used to store the maze.txt file 
		Scanner input;

		//Put the maze.txt file into the Scanner input 
		try {
			
			//Put the maze.txt file into that scanner object 
			input = new Scanner(new File("maze.txt"));

			//
			while (input.hasNext()) {
				
				//Store the maze input into an array of type char 
				char[] lineArray = input.nextLine().toCharArray();

				//Loop through the lineArray
				for (int column = 0; column < lineArray.length; column++) {
					mazearray[row][column] = new Cell(lineArray[column]);

					//If the 
					if (lineArray[column] == 'F')
						pellets++;

					//
					else if (lineArray[column] == 'P') {
						pacMan = new Mover(row, column);
						pacMan.setIcon(Icons.pacman[0]);

					}

					//
					else if (lineArray[column] == '0' || lineArray[column] == '1' || lineArray[column] == '2') {
						int gNum = Character.getNumericValue(mazearray[row][column].getItem());
						ghostArray[gNum] = new Mover(row, column);
						ghostArray[gNum].setIcon(Icons.ghost[gNum]);

					}

					//Add each row-column pair of the maze array to the screen
					add(mazearray[row][column]);
				}
				//Increment row 
				row++;
			}
			input.close();

			//Print file not found if a FileNotFoundException is caught 
		} catch (FileNotFoundException error) {
			System.out.println("file not found");

		}

	

	}

	//Method that decides how the pacman should move based on which of the arrow keys have been pressed
	public void keyPressed(KeyEvent key) {
		
		//Start the timer if the game isn't running and the pacman isn't dead 
		if (gametimer.isRunning() == false && pacMan.isDead() == false)
			gametimer.start();

		//If the pacman isn't dead and the score isn't equal to the pellet count 
		else if (pacMan.isDead() == false && score != pellets) {
			
			//calculates the direction the user wants to move in 
			int direction = key.getKeyCode() - 37; 
													
			//Initialise the change in row and change in column to 0 and 0 respectively 
			int dRow = 0;
			int dCol = 0;

			///after the direction has been calculated, decide what the dCol and dRow should be depending on the direction 
			if (direction == 0)
				dCol = -1;
			else if (direction == 1)
				dRow = -1;
			else if (direction == 2)
				dCol = 1;
			else if (direction == 3)
				dRow = 1;

			//If the current icon does not match Icons.wall, set the icon of the pacman and the direction 
			if (mazearray[pacMan.getRow() + dRow][pacMan.getColumn()].getIcon() != Icons.wall) {
				pacMan.setIcon(Icons.pacman[direction]);
				pacMan.setDirection(direction);

			}

		}

	}

	//Method that returns true or false depending on whether the pacman has collided with the ghost 
	//If collided, it returns true and false if it hasnt 
	private boolean collided() {
		for (Mover ghost : ghostArray) {
			if (ghost.getRow() == pacMan.getRow() && ghost.getColumn() == pacMan.getColumn())
				return true;
		}
		return false;
	}

	//Method that moves the pac-man across the maze 
	private void performMove(Mover mover) {
		Cell currentCell = mazearray[mover.getRow()][mover.getColumn()];
		Cell nextCell = mazearray[mover.getNextRow()][mover.getNextColumn()];

		if (mover.getColumn() == 1) {

			mover.setColumn(25);
			mazearray[12][1].setIcon(Icons.door);
		} else if (mover.getColumn() == 25) {
			mover.setColumn(1);
			mazearray[12][25].setIcon(Icons.door);
		}

		//If the next cell is a wall
		if (nextCell.getIcon() != Icons.wall) {

			//And if the mover is not the pac man and the current cell has the F character 
			if (mover != pacMan && currentCell.getItem() == 'F') {
				//Put a pellet icon there to represent the food 
				currentCell.setIcon(Icons.food);
			}

			//If the icon of the next cell the pac man is moving towards is a food icon
			else if (nextCell.getIcon() == Icons.food) {
				
				//Increment the variable which keeps track of the no of pellets eaten 
				pelletsEaten += 1;
				
				//Try to set the score of score panel to the total no of pellets eaten 
				//And try to catch an IOexception 
				try {
					theScorePanel.setScore(pelletsEaten);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//Set the current cell to blank 
				currentCell.setIcon(Icons.blank);
			}
			
			//If the icon of the next cell is a blank icon 
			if (nextCell.getIcon() == Icons.blank) 
				
				//Give the current cell a blank icon
				currentCell.setIcon(Icons.blank);

			//Move the mover and set the current cell to an element from the mazearray array
			mover.move();
			currentCell = mazearray[mover.getRow()][mover.getColumn()];

	
			//If the pac man collided with the ghost and the user still has some lives left 
			//Reduce the number of lives they have left by 1. 
			if (collided() && livesLeft <= 3 && livesLeft > 0) {
				livesLeft -= 1;

			}

			//If the pac man has collided with the ghost and the user has 0 lives left
			//The pac man is dead 
			if (collided() && livesLeft == 0) { 	
				death();
			}

			//Set the icon of the current cell to the icon of the mover 
			else {
				currentCell.setIcon(mover.getIcon());
			}

			//If the mover is pacman and the item of the next cell is F 
			if (mover == pacMan && nextCell.getItem() == 'F') {
				
				//Increment the score 
				score++;
				
				//Set the item of the current cell to E 
				currentCell.setItem('E');

				//If the score is equal to the number of pellets 
				if (score == pellets) {

					//Stop the game timer 
					gametimer.stop();

					//Display a popup telling the user that they cleared the board 
					JOptionPane.showMessageDialog(this, "You cleared the board!"); 
				}

			}

		}

	}

	//Method that runs when the pac man collides with a ghost and dies 
	private void death() {

		pacMan.setDead(true);

		mazearray[pacMan.getRow()][pacMan.getColumn()].setIcon(Icons.skull);

		gametimer.stop();

		JOptionPane.showMessageDialog(this, "Game over");
	}

	//Method which moves the ghosts 
	private void moveGhosts() {
		
		for (Mover ghost : ghostArray) {
			int dir=0;
			
			do {
				dir=(int)(Math.random()*4);
				
	    	}while(Math.abs(ghost.getDirection()-dir)==2); 
			
			ghost.setDirection(dir);
			
			if (!pacMan.isDead())
				performMove(ghost);
			
		}
		
	}

	//Move the pacMan through the screen if the event source is the game timer 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == gametimer) {
			performMove(pacMan);
			moveGhosts();

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(LineEvent event) {
		// TODO Auto-generated method stub

	}

}
