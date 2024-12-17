

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

//This is the template class for the second level of my pac man game. 
//It uses almost exactly the same code, except for the parts where I have labeled 


public class BoardLevelTwo extends JPanel implements KeyListener, ActionListener, LineListener {

	//The timer is faster here 
	private Timer gametimer = new Timer(100, this); 

	private Cell[][] mazearray = new Cell[25][27];
	int pelletsEaten = 0;
	private Mover pacMan;

	private Mover[] ghostArray = new Mover[3];

	private int pellets = 0;

	private int score = 0;

	private int livesLeft = 3;

	pacManScore theScorePanel = new pacManScore();

	public BoardLevelTwo() {

		setLayout(new GridLayout(25, 27));
		setBackground(Color.BLACK);

		// scorePanel.setBounds(0,0,0,0);
		// scorePanel.setBackground(Color.BLUE);
		// scorePanel.setLayout(null);
		// add(scorePanel);

		try {
			playSong();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadBoard();

	}

	// Song is called Electroman Adventures V2,
	// by Iron man and that spaceship pilot
	//https://www.youtube.com/watch?v=UdGzAwhCHTE

	private void playSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Clip musicClip;
		AudioInputStream musicStream;
		musicStream = AudioSystem.getAudioInputStream(new File("ElectromanTwo.wav").getAbsoluteFile());
		musicClip = AudioSystem.getClip();
		musicClip.open(musicStream);
		musicClip.loop(Clip.LOOP_CONTINUOUSLY);

		// Code source: https://www.geeksforgeeks.org/play-audio-file-using-java/

	}

	private void loadBoard() {
		int row = 0;

		Scanner input;

		try {
			input = new Scanner(new File("maze.txt"));

			while (input.hasNext()) {
				char[] lineArray = input.nextLine().toCharArray();

				for (int column = 0; column < lineArray.length; column++) {
					mazearray[row][column] = new Cell(lineArray[column]);

					if (lineArray[column] == 'F')
						pellets++;

					else if (lineArray[column] == 'P') {
						pacMan = new Mover(row, column);
						pacMan.setIcon(Icons.pacman[0]);

					}

					else if (lineArray[column] == '0' || lineArray[column] == '1' || lineArray[column] == '2') {
						int gNum = Character.getNumericValue(mazearray[row][column].getItem());
						ghostArray[gNum] = new Mover(row, column);
						ghostArray[gNum].setIcon(Icons.ghost[gNum]);

					}

					add(mazearray[row][column]);
				}
				row++;
			}
			input.close();

		} catch (FileNotFoundException error) {
			System.out.println("file not found");

		}

	}

	public void keyPressed(KeyEvent key) {
		if (gametimer.isRunning() == false && pacMan.isDead() == false)
			gametimer.start();

		else if (pacMan.isDead() == false && score != pellets) {
			int direction = key.getKeyCode() - 37; 
			
			int dRow = 0;
			int dCol = 0;

			if (direction == 0)
				dCol = -1;
			else if (direction == 1)
				dRow = -1;
			else if (direction == 2)
				dCol = 1;
			else if (direction == 3)
				dRow = 1;

			if (mazearray[pacMan.getRow() + dRow][pacMan.getColumn()].getIcon() != Icons.wall) {
				pacMan.setIcon(Icons.pacman[direction]);
				pacMan.setDirection(direction);

			}

		}

	}

	private boolean collided() {
		for (Mover ghost : ghostArray) {
			if (ghost.getRow() == pacMan.getRow() && ghost.getColumn() == pacMan.getColumn())
				return true;
		}
		return false;
	}

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

		if (nextCell.getIcon() != Icons.wall) {

			if (mover != pacMan && currentCell.getItem() == 'F') {
				currentCell.setIcon(Icons.food);
			}

			else if (nextCell.getIcon() == Icons.food) {
				pelletsEaten += 1;
				try {
					theScorePanel.setScore(pelletsEaten);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				currentCell.setIcon(Icons.blank);
			}
			if (nextCell.getIcon() == Icons.blank) 
													
				currentCell.setIcon(Icons.blank);

			mover.move();
			currentCell = mazearray[mover.getRow()][mover.getColumn()];

	

			if (collided() && livesLeft <= 3 && livesLeft > 0) {

				livesLeft -= 1;

			}

			if (collided() && livesLeft == 0) { 
												
				death();
			}

			else {
				currentCell.setIcon(mover.getIcon());

			}

			if (mover == pacMan && nextCell.getItem() == 'F') {
				score++;
				currentCell.setItem('E');

				if (score == pellets) {

					gametimer.stop();

					JOptionPane.showMessageDialog(this, "You cleared the board!"); // ask fernandes about this tmrrw
				}

			}

		}

	}

	private void death() {

		pacMan.setDead(true);

		mazearray[pacMan.getRow()][pacMan.getColumn()].setIcon(Icons.skull);

		gametimer.stop();

		JOptionPane.showMessageDialog(this, "Game over");
	}

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
