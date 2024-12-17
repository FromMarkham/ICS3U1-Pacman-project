
import javax.swing.JFrame;

//This is the test class for the 3rd level of my Pac man game 

@SuppressWarnings("serial")
public class PacManGUIThree extends JFrame{
	
	private BoardLevelThree board = new BoardLevelThree();
	
	//Class constructor 
	public PacManGUIThree() {
		
		setSize(600,600);
		
		setTitle("Pacman");
		
		add(board);
		
		addKeyListener(board);
		
		setVisible(true);
		
	}
}