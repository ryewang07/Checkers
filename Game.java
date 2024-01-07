import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
public class Game extends JPanel implements ActionListener{
	private static ImageIcon imageIcon;
	private Timer myTimer;
	private static Board board, board1, board2, board3;
	private JToolBar toolbar;
	private static JLabel label, label1;
	public static JFrame MyJFrame;
	public static Container cont;
	private static JButton play, play1, play2, play3, menu, rules, quit;
	private static int change=0;
	public Game() {
		myTimer = new Timer(10, this);
		MyJFrame = new JFrame();
		toolbar= new JToolBar("Toolbar");
		play=new JButton("8x8");
		play1=new JButton("10x10");
		play2=new JButton("12x12");
		play3=new JButton("20x20, fun");
		menu=new JButton("Menu");
		rules=new JButton("Rules");
		quit=new JButton("Quit");
		play.addActionListener(this);
		play1.addActionListener(this);
		play2.addActionListener(this);
		play3.addActionListener(this);
		menu.addActionListener(this);
		rules.addActionListener(this);
		quit.addActionListener(this);
		toolbar.add(play);
		toolbar.add(play1);
		toolbar.add(play2);
		toolbar.add(play3);
		toolbar.add(menu);
		toolbar.add(rules);
		toolbar.add(quit);
		cont = MyJFrame.getContentPane();
		cont.setLayout(new BorderLayout());
		cont.add(toolbar,BorderLayout.SOUTH);
		imageIcon = new ImageIcon(new ImageIcon("checkers.jpg").getImage().getScaledInstance(Board.DISPLAY_WIDTH-15, Board.DISPLAY_WIDTH, Image.SCALE_DEFAULT));
		label=new JLabel();
		label.setIcon(imageIcon);
		label1= new JLabel("Checkers");
		label1.setBounds(Board.DISPLAY_WIDTH/2-110,Board.DISPLAY_WIDTH/2-50,300,50);
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Verdana", Font.PLAIN, 40));
		cont.add(label1);
		cont.add(label, BorderLayout.CENTER);
	
		MyJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // make frame closed when x button is pressed
		MyJFrame.setVisible(true);     // make the frame visible
		MyJFrame.setSize(Board.DISPLAY_WIDTH,Board.DISPLAY_WIDTH);
		MyJFrame.setResizable(false);// set the size of the frame
  
		//You made stuff, now make a proper menu

//end of main
//end of class
  }	
  public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menu) {
			cont.removeAll();
			cont.add(label1);
			cont.add(label, BorderLayout.CENTER);
			cont.add(toolbar,BorderLayout.SOUTH);
			MyJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // make frame closed when x button is pressed
			MyJFrame.setVisible(true);     // make the frame visible
			MyJFrame.setSize(Board.DISPLAY_WIDTH,Board.DISPLAY_WIDTH);
			MyJFrame.setResizable(false);// set the size of the frame
		}
		else if(e.getSource()==play) {
			cont.removeAll();
			board = new Board(8);
			cont.add(board, BorderLayout.CENTER);
			cont.add(toolbar,BorderLayout.SOUTH);
			MyJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // make frame closed when x button is pressed
			MyJFrame.setVisible(true);     // make the frame visible
			MyJFrame.setSize(Board.DISPLAY_WIDTH,Board.DISPLAY_WIDTH);
			MyJFrame.setResizable(false);// set the size of the frame
		}
		else if(e.getSource()==play1) {
			cont.removeAll();
			board1 = new Board(10);
			cont.add(board1, BorderLayout.CENTER);
			cont.add(toolbar,BorderLayout.SOUTH);
			MyJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // make frame closed when x button is pressed
			MyJFrame.setVisible(true);     // make the frame visible
			MyJFrame.setSize(Board.DISPLAY_WIDTH,Board.DISPLAY_WIDTH);
			MyJFrame.setResizable(false);// set the size of the frame
		}
		else if(e.getSource()==play2) {
			cont.removeAll();
			board2 = new Board(12);
			cont.add(board2, BorderLayout.CENTER);
			cont.add(toolbar,BorderLayout.SOUTH);
			MyJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // make frame closed when x button is pressed
			MyJFrame.setVisible(true);     // make the frame visible
			MyJFrame.setSize(Board.DISPLAY_WIDTH,Board.DISPLAY_WIDTH);
			MyJFrame.setResizable(false);// set the size of the frame
		}
		else if(e.getSource()==play3) {
			cont.removeAll();
			board3 = new Board(20);
			cont.add(board3, BorderLayout.CENTER);
			cont.add(toolbar,BorderLayout.SOUTH);
			MyJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // make frame closed when x button is pressed
			MyJFrame.setVisible(true);     // make the frame visible
			MyJFrame.setSize(Board.DISPLAY_WIDTH,Board.DISPLAY_WIDTH);
			MyJFrame.setResizable(false);// set the size of the frame
		}
		else if(e.getSource()==quit) {
			System.exit(0);
		}
		
		else{
			String newLine = System.getProperty("line.separator");
			JOptionPane.showMessageDialog(null, 
			"Rules of checkers are: "+
			newLine+
            newLine + "Each piece can move diagonally one tile but during this one tile transition if there is already a piece on the desired destination, user may not move there" +
            newLine + "In order for a piece to kill, there must be a opposite colour piece 1 tile diagonal to them and an empty space 2 tiles diagonal to them, this move can be repeated multiple times in one turn" +
            newLine + "If a piece can be killed, you are forced to take the piece"+
			newLine + "If a piece reaches the other side, it is promoted to a King which can move backwards" + 
			newLine + "Eliminate each others' pieces and have fun"+
			newLine + "Menu bar is at the bottom");

		}
			
			
	}
	
	public void paintComponent(Graphics g) {
	
	}
	 //public void actionPerformed(ActionEvent e) {
				//System.out.println("Hi");
			//}
  public static void main(String[] args) {
	Game game= new Game();
  }
 
}