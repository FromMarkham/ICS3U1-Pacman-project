
//Name: Bob Wang

//Date of submission: December 14 2024

//Course Code: ICS3U1-04, Mr. Fernandes 

//Title: Pac man

/*Description: 
 * This is a remix of the original Pac Man game. It features the actual Pac man game, 
 * and also features music, a score count, a count of the number of lives left, a high score,
 *  and a title screen. It also has a second level which runs at a faster speed along with slightly 
 *  different background music, and a third level that runs at an even faster speed and also has different 
 *  background music then the 2nd level, and is pink and birthday-party themed. The title screen
 *   has 3 buttons that are used to access the main level, 2nd level, and 3rd level, and also features 
 *   the Pac-man logo. The count of the number of lives left, the high score, 
 *   and current score are all on a separate JPanel. 

 */

/* Major skills:
 *GUIs, reading from files, playing audio, for loops, 
 *if statements, classes, methods,  reading and writing 
 *from files, scanners, while loops, variables, ASCII
 
 */

/*Added Features:
- Music
- 1 Theme
- 3 levels
- High score (doesn' t always work)
- lives left
- Current score 
 * 
 */

/* Areas of concern: 
 * 
- The ghosts are moving in weird ways for all 3 of the levels 
- Error messages show up when the user presses a key thats not the arrow key 
- The different icons that are supposed to be used in the 3rd level aren’t showing and that level also has a lot of bugs in general
- The high score isn’t showing up for all 3 of the levels
- The number of lives left count doesn’t work for all 3 of the levels 

 */

//Imports
import javax.swing.JFrame;

//This is the test class for the main level of my Pac man game 


public class PacManGUI extends JFrame {
	
	private Board board = new Board();
	
	//Constructor 
	public PacManGUI() {
		
		setSize(600,600);
		
		setTitle("Pacman");
		
		add(board);
		
		addKeyListener(board);
		
		setVisible(true);
		
	}
}
