
//Imports 
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

//Main class 
public class pacManTitleScreen extends JFrame implements ActionListener {
	
	//The pac man logo  in the form of an ImageIcon
	//Image source: https://logos-world.net/pacman-logo/
	private ImageIcon pacManLogo=new ImageIcon("images/PacManLogo.png");
	
	//This button takes the user to the main level of the game 
	public JButton goToTheGame=new JButton("Play pacman🎮🕹️");
	
	//The button which takes the user to the 2nd level 
	public JButton LevelTwo=new JButton("Pacman Level 2🤯");
	
	//The button which takes the user to the 3rd level that is also birthday-themed 
	public JButton LevelThree=new JButton("Pacman Level 3🤯🤯");
	
	//The pac man logo in the form of a JLabel, so it can be placed onto the screen 
	public JLabel pacManLogoLabel=new JLabel(pacManLogo);
	
	//Constructor 
	public pacManTitleScreen() {
		//Set the size, background color, title, and layout 
		setSize(1000,700);
		setBackground(Color.BLACK);
		setTitle("Pacman");
		setLayout(null);
		
		//Adds the button which takes the user to the main game, and also sets the location, size and actionlistener
		goToTheGame.setBounds(610,270,150,100);
		goToTheGame.addActionListener(this);
		add(goToTheGame);

		//Adds the button which takes the user to the 2nd level of the game, and also sets the location, size and actionlistener
		LevelTwo.setBounds(160,470,150,100);
		LevelTwo.addActionListener(this);
		add(LevelTwo);
		
		//Adds the button which takes the user to the 3rd level of the game, and also sets the location, size and actionlistener
		LevelThree.setBounds(610,470,150,100);
		LevelThree.addActionListener(this);
		add(LevelThree);
		
		//Adds the Pac-man logo which takes the user to the main game, and also sets the location and size
		pacManLogoLabel.setBounds(50,-50,880,400);
		add(pacManLogoLabel);
		
		//Make it visible 
		setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//If the goToTheGame, button is clicked, create a new PacManGUI for the main level, and close this window 
		if (e.getSource()==goToTheGame) {
			new PacManGUI();
			setVisible(false);
		}
		
		//If the LevelTwo button is clicked, create a new PacManGUI for the 2nd level, and close this window 
		if (e.getSource()==LevelTwo) {
			new PacManGUITwo();
			setVisible(false);
		}
		
		//If the LevelThree button is clicked, create a new PacManGUI for the 3rd level, and close this window 
		if (e.getSource()==LevelThree) {
			new PacManGUIThree();
			setVisible(false);
		}
		
	}
	
	
}
