
//Import JLabel 
import javax.swing.JLabel;

//Main class
public class Cell extends JLabel{

	//Item variable 
	private char item;
	
	//Constructor 
	public Cell(char item) {
		super();
		// TODO Auto-generated constructor stub
		this.item=item;
		setCodeIcon();
		
	}
	
	//Method to the return the char variable item based on the maze.txt file 
	public char getItem() {
		return item;
	}
	
	//Method to the set the char variable item based on the maze.txt file 
	public void setItem(char item) {
		this.item=item;	
	}


	//Method to decide what icon should be passed into the setIcon method
	private void setCodeIcon() {

		//If character is P, pass a pac-man icon into setIcon
		if (item=='P') {
			setIcon(Icons.pacman[0]);
		}
		
		//If character is 0, pass a pac-man icon into setIcon
		else if(item=='0') {
			setIcon(Icons.ghost[0]);
		}
		
		//If character  is 1, pass a pac-man icon into setIcon
		else if(item=='1') {
			setIcon(Icons.ghost[1]);
		}
		
		//If character is 2, pass a pac-man icon into setIcon
		else if(item=='2') {
			setIcon(Icons.ghost[2]);
		}
		
		//If character  is W, pass a pac-man icon into setIcon
		else if(item=='W') {
			setIcon(Icons.wall);
		}
		
		//If character  is F, pass a pac-man icon into setIcon
		else if(item=='F') {
			setIcon(Icons.food);
		}
		
		//If character is D, pass a pac-man icon into setIcon
		else if(item=='D') {
			setIcon(Icons.door);
		}
		
	}

}
