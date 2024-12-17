
//Imports 
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.*;

//Main class 
public class pacManScore extends JFrame implements ActionListener {
	
	
	
	
    //The JLabel which keeps track of the current score of the user, initialised to 0 
	private JLabel scoreLabel=new JLabel("0");
	
	//Keeps track of the current high score 
	private int highScore;
	
	//Keeps track of the number of times that we have reset the score 
	private int scoreSetCount=0;
	
	//It labels the score label, letting us know where it is 
	private JLabel scoreLabelDesc=new JLabel("Your current score:");
	
	//JLabel to keeep track of the number of lives the user has left, initialised to 3
	private JLabel livesLeftLabel=new JLabel("3");
	
	//It labels the lives left label, letting us know where it is
	private JLabel livesLeftDesc=new JLabel("Lives left:");

	//High score label
	static JLabel highScoreLabel=new JLabel("0");
	
	//It labels the high score label, letting us know where the high score label is 
	static JLabel highScoreDescription=new JLabel("High score:");
	
	
	
    
	
	
	//Method to set the current score and the high score of the user 
	//Has scoreInt as a parameter (which is the score of the user in integer form)
	public void setScore(int scoreInt) throws IOException {
		
		    //Filewriter explanation: https://www.w3schools.com/java/java_files_create.asp
			FileWriter highScoreFile=new FileWriter("highScores.txt");
			//Creates an object of type filewriter so the file can be writed 
		
			//Define a scanner that takes highScores.txt, which is the file which stores all the high scores, as an argument 
			Scanner highScoreFileInput = new Scanner(new File("highScores.txt"));
		    
			//String that stores the current score which goes onto the score panel 
			String scoreString;
			
			//String that stores the current high score of the user which goes onto the score panel 
			String highScoreString;
			
			//Sets the scoreString to be equal to the scoreInt
			scoreString=Integer.toString(scoreInt);
			
			//Use delimiter in the file which stores the high scores 
			highScoreFileInput.useDelimiter(",|\r\n");
			
			//Initialise the string which stores the high scores that got taken out of the high score file
			//To null
			String highScoreFileString=null;
			
			//While the high score file in scanner form has a next line, make the highScoreFileString equal to the next line 
			while (highScoreFileInput.hasNextLine()) {
				highScoreFileString=(highScoreFileInput.next());
			}
			
			
				scoreLabel.setText(scoreString);
				
				//If the number of times we reset the score is equal to 0 (as kept track of by the scoresetcount)
				if (scoreSetCount==0) {
					//Make the high score equal to the current score 
					highScore=scoreInt;
					
					
					//Integer.toString explanation: //https://www.geeksforgeeks.org/different-ways-for-integer-to-string-conversions-in-java/
					//It converts the score integer to a string so it can be displayed on the screen 
					highScoreString=Integer.toString(highScore);
					
					//Pass the score into a file which stores all the high scores of the user 
					highScoreFile.write(highScoreString);
					
					
					//Sets the JLabel for the high score to whatever the highScoreFileString is equal to
					highScoreLabel.setText(highScoreFileString);				
					// setText method explanation https://www.tutorialspoint.com/java-program-to-change-jlabel-text-after-creation
				}
				
				//If the number of times we reset the score is not equal to 0
				else {
					
					//And if the current score is now greater then the high score 
					if (scoreInt>highScore) {
						
						//Make the high score equal to the current score 
						highScore=scoreInt;
						
						//Convert the high score to a string and make the string thats supposed to store the high score
						//Equal to that string I mentioned above
						highScoreString=Integer.toString(highScore);
						
						//Put the string which holds the high score integer that got converted into a string
						//Into the file which stores all the high scores 
						highScoreFile.write(highScoreString);
						
						//Set the high score JLabel to the highScoreString
						highScoreLabel.setText(highScoreFileString);
						
					}
					
					//Otherwise just keep the highscore the same 
					else {
						highScore=highScore;
					}
						
				}
		
	
	    
		//Increment the variable which holds the number of times we've reset the score 
		scoreSetCount+=1;
		

	}
	
	//Method to set the JLabel which stores a count of the number of lives left 
	public void setLivesLeft(int livesLeft) {
		
		//the string which holds the number of lives left 
		String livesLeftString;
		
		//Set the string which holds the number of lives left to the number of lives left integer (converted into string form)
		livesLeftString=Integer.toString(livesLeft);
		
		//Set the label which stores the number of lives left to the string which holds the number of lives left 
		livesLeftLabel.setText(livesLeftString);
		
	}
//Constructor 
	public pacManScore() {
		
		//Set the size of the pac man score window to 
		setSize(600,600);
		
		//Adds the label for the current score, and also sets the font, location and  size 
		scoreLabel.setBounds(400,0,500,250);
		scoreLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		add(scoreLabel);
		
		//Adds the label that lets us know where the score label is, and also sets the font, location and  size 
		scoreLabelDesc.setBounds(50,0,500,250);
		scoreLabelDesc.setFont(new Font("Calibri", Font.PLAIN, 30));
		add(scoreLabelDesc);
		
		//Adds the label for the no of lives left, and also sets the font, location and  size 
		livesLeftLabel.setBounds(400,0,500,450);
		livesLeftLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		add(livesLeftLabel);
		
		//Adds the label that tells us where the no of lives left label is, and also sets the font, location and  size 
		livesLeftDesc.setBounds(50,0,500,450);
		livesLeftDesc.setFont(new Font("Calibri", Font.PLAIN, 30));
		add(livesLeftDesc);
		
		//Adds the label which stores the current high score, and also sets the font, location and  size 
		highScoreLabel.setBounds(400,0,500,650);
		highScoreLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		add(highScoreLabel);
		
		//Adds the label which tells where where the high score label is, and also sets the font, location and  size 
		highScoreDescription.setBounds(50,0,500,650);;
		highScoreDescription.setFont(new Font("Calibri", Font.PLAIN, 30));
		add(highScoreDescription);
		
		//Set the title
		setTitle("Score, highscore & Number of lives left");
		
		//Make layout=null
		setLayout(null);
		
		//Make it visible 
		setVisible(true);
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
