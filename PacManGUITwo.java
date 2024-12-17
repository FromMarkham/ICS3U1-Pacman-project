

//Import
import javax.swing.JFrame;

//This is the application class for the second level of my pac man game 

@SuppressWarnings("serial")
public class PacManGUITwo extends JFrame{
	
	private BoardLevelTwo board = new BoardLevelTwo();
	
	public PacManGUITwo() {
		
		setSize(600,600);
		
		setTitle("Pacman");
		
		add(board);
		
		addKeyListener(board);
		
		setVisible(true);
		
	}
}