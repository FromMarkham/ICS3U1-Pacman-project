
//Import the ImageIcon class
import javax.swing.ImageIcon;

//Main class
public class Icons {
	
	//Wall icon
	public static final ImageIcon wall = new ImageIcon("images/Wall.bmp");
	
	//Pellet icon, used to represent food
	public static final ImageIcon food = new ImageIcon("images/Food.bmp");
	
	// Black/blank icon
	public static final ImageIcon blank = new ImageIcon("images/Black.bmp");
	
	//Door icon
	public static final ImageIcon door = new ImageIcon("images/Black.bmp");
	
	//Skull icon
	public static final ImageIcon skull  = new ImageIcon("images/Skull.bmp");
	
	//Cake icon, used in place of pacman, for the birthday-themed 3rd level
	public static final ImageIcon cake= new ImageIcon("images/Cake.bmp");
	//Image source for the cake: https://www.vectorstock.com/royalty-free-vector/birthday-cake-smiling-red-and-pink-color-vector-35166073
	
	//Candle icon used instead of pellets for the birthday themed 3rd level
	public static final ImageIcon candle= new ImageIcon("images/Candle.bmp");
	//Image source for the candle: https://www.vecteezy.com/vector-art/15062041-burning-birthday-candle-icon-flat-isolated-vector
	
//Array of type ImageIcon that stores Pac-man gifs 
	public static ImageIcon[] pacman= {new ImageIcon("images/PacMan0.gif"),
			new ImageIcon("images/PacMan1.gif"),
			new ImageIcon("images/PacMan2.gif"),
			new ImageIcon("images/PacMan3.gif"),
};

	
	//Array of type ImageIcon that stores ghost pictures 
	public static ImageIcon[] ghost= {
			new ImageIcon("images/Ghost0.bmp"),
			new ImageIcon("images/Ghost1.bmp"),
			new ImageIcon("images/Ghost2.bmp")
	};
	
	//Array of type image icon that stores kid icons, which are used instead of ghosts in the birthday-themed 3rd level
	public static ImageIcon[] kids= {
			new ImageIcon("images/Kid.bmp"),
			new ImageIcon("images/Kid2.bmp"),
			new ImageIcon("images/Kid3.bmp")
	};
	//Image source for Kid.bmp:
	//https://www.flaticon.com/free-icon/children_3884885
	
	//Image source for Kid2.bmp:
	//https://www.bigstockphoto.com/image-473428563/stock-vector-kid-boy-sitting-happily-celebrating-birthday%2C-vector-illustration-art-doodle-icon-image-kawaii
	
	//Image source for Kid3.bmp:
	//https://ca.pinterest.com/pin/940548703402291207/
}
