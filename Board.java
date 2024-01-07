import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Board extends JPanel implements ActionListener{ 
	public static int DISPLAY_WIDTH=840;
	private Timer myTimer;
	private int counter,i,counter1=0,j=0,k=0;
	private int x,y,desX,desY;
	private static ArrayList <String> potentialMoves= new ArrayList <String>();
	private String firstclick="";
	private String secondclick="";
	private boolean help=true;
	public static JButton board[][];//Paint uses this to draw the board, cap is 34 (cap)^2 = 34^2
	public static int [][] playingBoard;//This board is needed to play the game do logic.
	public static Piece pieces[][];
	private static int whiteCounters;
	private static int blackCounters;

	public Board(int boardsize) {
		board=new JButton[boardsize][boardsize];
		playingBoard=new int[boardsize][boardsize];
		pieces=new Piece[2][board.length/2*(board.length-2)/2];
		whiteCounters=board.length/2*(board.length-2)/2;
		blackCounters=board.length/2*(board.length-2)/2;
		this.setLayout(new GridLayout(board.length,board[0].length));
		myTimer = new Timer(10, this);
		counter=0;
		i=0;

		for(int r=0; r<playingBoard.length;r++) {
			for(int c=0; c<playingBoard[r].length; c++) {

				if((r+c)%2!=0) {
					playingBoard[r][c]=3;
					if (counter>=0 && counter <board.length/2*(board.length-2)/2) {
						playingBoard[r][c]=1;//1 Is white
						pieces[0][counter]=new Piece(r,c,true,1);//Row 0 is all white pieces, 1 means white
					}		
					else if(counter>=(board.length/2*(board.length-2)/2)+board.length){
						playingBoard[r][c]=2;//2 is Black
						pieces[1][i]=new Piece(r,c,false,2);//Row 1 is all black pieces, 2 means black
						i++;
					}
					counter++;
				}
			}
		}
		for(int r=0; r<playingBoard.length; r++) {
				for(int c=0; c<playingBoard[r].length; c++) {
					board[r][c]=new JButton();
					board[r][c].setBackground(Color.LIGHT_GRAY);
					if((r+c)%2!=0) {
						board[r][c]=new JButton();
						board[r][c].setBackground(Color.GRAY);	
						
					}
				this.add(board[r][c]);
				board[r][c].setActionCommand(r+" "+c);
				board[r][c].addActionListener(this);
			}
		}		
	}
	private void kill(int x, int y, int desX, int desY) {//Kills whitepieces
		for(int i=0; i<playingBoard.length/2*(playingBoard.length-2)/2; i++) {
				if (pieces[0][i].x==(desX+x)/2 && pieces[0][i].y==(desY+y)/2) {
					pieces[0][i].setDead();
					
				}
			}
	}
	private void kill(int x, int y, int desX, int desY, boolean black) {//Kills blackpieces
		for(int i=0; i<playingBoard.length/2*(playingBoard.length-2)/2; i++) {
				if (pieces[1][i].x==(desX+x)/2 && Board.pieces[1][i].y==(desY+y)/2) {
					pieces[1][i].setDead();
					
				}
			}
	}
	public void actionPerformed(ActionEvent e) {
		boolean blackWin=true;
		boolean whiteWin=true;
					
		int temp=0;
		int temp1=0;
		j++;
		boolean run=false;
		JButton b1=(JButton)e.getSource();
		//Program better click
		if(j%2==1) {
			firstclick=b1.getActionCommand();
			boolean valid=false;
			if(firstclick.length()==3) {
				temp=Integer.parseInt(firstclick.substring(0,1));
				temp1=Integer.parseInt(firstclick.substring(2));
			}
			
			else if(firstclick.length()==5) {
				temp=Integer.parseInt(firstclick.substring(0,2));
				temp1=Integer.parseInt(firstclick.substring(3));
			}
			else{
				if(firstclick.substring(1,2).equals(" ")) {
					temp=Integer.parseInt(firstclick.substring(0,1));
					temp1=Integer.parseInt(firstclick.substring(2));
				}
				else if(firstclick.substring(2,3).equals(" ")) {
					temp=Integer.parseInt(firstclick.substring(0,2));
					temp1=Integer.parseInt(firstclick.substring(3));
				}
			}
			if(counter1%2==0) {
				for(int i=0; i<pieces[1].length; i++) {
					if(pieces[1][i].getX()==temp && pieces[1][i].getY()==temp1&& !pieces[1][i].isDead()) {
						valid=true;
					}
				}
				if(valid==false) {
					firstclick="";
					j=0;
				}
			}
			else {
				for(int i=0; i<pieces[1].length; i++) {
					if(pieces[0][i].getX()==temp && pieces[0][i].getY()==temp1 && !pieces[0][i].isDead()) {
						valid=true;
					}
				}
				if(valid==false) {
					firstclick="";
					j=0;
			}	
		}
		}
		else {//This is else if(j%2==0) 
			secondclick=b1.getActionCommand();
			boolean valid=false;
			if(secondclick.length()==3) {
				temp=Integer.parseInt(secondclick.substring(0,1));
				temp1=Integer.parseInt(secondclick.substring(2));
			}
			
			else if(secondclick.length()==5) {
				temp=Integer.parseInt(secondclick.substring(0,2));
				temp1=Integer.parseInt(secondclick.substring(3));
			}
			else {
				if(secondclick.substring(1,2).equals(" ")) {
					temp=Integer.parseInt(secondclick.substring(0,1));
					temp1=Integer.parseInt(secondclick.substring(2));
				}
				else if(secondclick.substring(2,3).equals(" ")) {
					temp=Integer.parseInt(secondclick.substring(0,2));
					temp1=Integer.parseInt(secondclick.substring(3));
				}
			}
			if(counter1%2==0) {
				for(int i=0; i<pieces[1].length; i++) {
					if(pieces[1][i].getX()==temp && pieces[1][i].getY()==temp1 && !pieces[1][i].isDead()) {
						firstclick=secondclick;
						j=0;
						}
					}
				}
	
			else {
				for(int i=0; i<pieces[1].length; i++) {
					if(pieces[0][i].getX()==temp && pieces[0][i].getY()==temp1 && !pieces[0][i].isDead()) {
						firstclick=secondclick;
						j=0;
					}
				}
	
		}
			if(playingBoard[temp][temp1] ==3) {
				valid=true;
				run=true;
			}
			
			if(!valid) {
				secondclick="";
				j--;
			}
		}
		
		//Game, error for turn, glitch if samepiece gets clicked and their turn
					
		if(run) {
			boolean condition=false;  //This is for getting coordinates on a double digit board 
										//e.g if a coordinate is 10 8 this checks it.
			if(firstclick.length()==3) {
				x=Integer.parseInt(firstclick.substring(0,1));
				y=Integer.parseInt(firstclick.substring(2));
			}
			
			else if(firstclick.length()==5) {
				x=Integer.parseInt(firstclick.substring(0,2));
				y=Integer.parseInt(firstclick.substring(3));
			}
			else {
				if(firstclick.substring(1,2).equals(" ")) {
					x=Integer.parseInt(firstclick.substring(0,1));
					y=Integer.parseInt(firstclick.substring(2));
				}
				else if(firstclick.substring(2,3).equals(" ")) {
					x=Integer.parseInt(firstclick.substring(0,2));
					y=Integer.parseInt(firstclick.substring(3));
				}
			}
			//Incase for boards with 2digit numbers
			if(secondclick.length()==3) {
				desX=Integer.parseInt(secondclick.substring(0,1));
				desY=Integer.parseInt(secondclick.substring(2));
			}
			
			else if(secondclick.length()==5) {
				desX=Integer.parseInt(secondclick.substring(0,2));
				desY=Integer.parseInt(secondclick.substring(3));
			}
			else {
				if(secondclick.substring(1,2).equals(" ")) {
					desX=Integer.parseInt(secondclick.substring(0,1));
					desY=Integer.parseInt(secondclick.substring(2));
				}
				else if(secondclick.substring(2,3).equals(" ")) {
					desX=Integer.parseInt(secondclick.substring(0,2));
					desY=Integer.parseInt(secondclick.substring(3));
				}
			}
			//potentialMoves.clear();
			if(counter1%2==0) {//Black Turn
				//This should be ur mustmove checker
				if(help) {//should be set from ur multiple jump checker
					for(int i=0; i<pieces[1].length; i++) {
						int tempX=pieces[1][i].getX();
						int tempY=pieces[1][i].getY();
						if(!pieces[1][i].isKing() && !pieces[1][i].isDead()) {
							if((tempY-2 >=0) && tempX-2>=0) {
								if((playingBoard[tempX-1][tempY-1]==1 || playingBoard[tempX-1][tempY-1]==11) && playingBoard[tempX-2][tempY-2]==3) {
									potentialMoves.add(tempX+" "+ tempY);
									potentialMoves.add(tempX-2+" "+ (tempY-2));
								}
							}
							if((tempY+2<=board.length-1)&&tempX-2>=0) {
								if((playingBoard[tempX-1][tempY+1]==1 || playingBoard[tempX-1][tempY+1]==11) && playingBoard[tempX-2][tempY+2]==3) {
									potentialMoves.add(tempX+ " "+ tempY);
									potentialMoves.add(tempX-2+" "+(tempY+2));
								}
							}
						}
						else if(pieces[1][i].isKing() && !pieces[1][i].isDead()) {
							if((tempY-2 >=0) && tempX-2>=0) {
								if((playingBoard[tempX-1][tempY-1]==1 || playingBoard[tempX-1][tempY-1]==11) && playingBoard[tempX-2][tempY-2]==3) {
									potentialMoves.add(tempX+" "+ tempY);
									potentialMoves.add(tempX-2+" "+ (tempY-2));
								}
							}
							if((tempY+2<=board.length-1)&&tempX-2>=0) {
								if((playingBoard[tempX-1][tempY+1]==1 || playingBoard[tempX-1][tempY+1]==11) && playingBoard[tempX-2][tempY+2]==3) {
									potentialMoves.add(tempX+ " "+ tempY);
									potentialMoves.add(tempX-2+" "+(tempY+2));
								}
							}
							if((tempY-2 >=0)&&tempX+2<=board.length-1) {
								if((playingBoard[tempX+1][tempY-1]==1 || playingBoard[tempX+1][tempY-1]==11) && playingBoard[tempX+2][tempY-2]==3) {
									potentialMoves.add(tempX+" "+ tempY);
									potentialMoves.add(tempX+2+" "+ (tempY-2));
								}
							}
							if((tempY+2<=board.length-1)&&tempX+2<=board.length-1) {
								if((playingBoard[tempX+1][tempY+1]==1|| playingBoard[tempX+1][tempY+1]==11) && playingBoard[tempX+2][tempY+2]==3) {
									potentialMoves.add(tempX+ " "+ tempY);
									potentialMoves.add(tempX+2+" "+(tempY+2));
								}
							}
						}
					}
				}
				if(potentialMoves.size()==0) 
					condition=true;
				else{
					for(int j=0; j<potentialMoves.size(); j+=2) {
						if(potentialMoves.get(j).equals(firstclick) && potentialMoves.get(j+1).equals(secondclick)) {
							for(int i=0; i< pieces[0].length; i++) {
								if(pieces[1][i].getX()==x && pieces[1][i].getY() ==y) {
									if(pieces[1][i].isValid(x,y,desX,desY)==2 && !pieces[1][i].isDead()) {
										pieces[1][i].setX(desX);
										pieces[1][i].setY(desY);
										playingBoard[x][y]=3;
										playingBoard[(desX+x)/2][(desY+y)/2]=3;
										kill(x,y,desX,desY);//Kills White
										playingBoard[desX][desY]=pieces[1][i].getID();
										potentialMoves.clear();
									}
									for(int k=0; k<playingBoard.length; k++) {
										for(int l=0; l<playingBoard[k].length; l++) {
											if(playingBoard[k][l]==1||playingBoard[k][l]==11) {
												blackWin=false;
											}
										}
									}
									if(blackWin) {
										repaint();
										JOptionPane.showMessageDialog(null,"Black wins!");
									}
									//---------------------------------------------
									int tempX=pieces[1][i].getX();
									int tempY=pieces[1][i].getY();
									if(!pieces[1][i].isKing() && !pieces[1][i].isDead()) {
										if((tempY-2 >=0) && tempX-2>=0) {
											if((playingBoard[tempX-1][tempY-1]==1 || playingBoard[tempX-1][tempY-1]==11) && playingBoard[tempX-2][tempY-2]==3) {
												potentialMoves.add(tempX+" "+ tempY);
												potentialMoves.add(tempX-2+" "+ (tempY-2));
												help=false;
											}
										}
											if((tempY+2<=board.length-1)&&tempX-2>=0) {
												if((playingBoard[tempX-1][tempY+1]==1 || playingBoard[tempX-1][tempY+1]==11) && playingBoard[tempX-2][tempY+2]==3) {
													potentialMoves.add(tempX+ " "+ tempY);
													potentialMoves.add(tempX-2+" "+(tempY+2));
													help=false;
												}
											}		
										}
										//This entire part is to check for multiple jumps.
									else if(pieces[1][i].isKing() && !pieces[1][i].isDead()) {
										if((tempY-2 >=0) && tempX-2>=0) {
											if((playingBoard[tempX-1][tempY-1]==1 || playingBoard[tempX-1][tempY-1]==11) && playingBoard[tempX-2][tempY-2]==3) {
												potentialMoves.add(tempX+" "+ tempY);
												potentialMoves.add(tempX-2+" "+ (tempY-2));
												help=false;
											}
										}
										if((tempY+2<=board.length-1)&&tempX-2>=0) {
											if((playingBoard[tempX-1][tempY+1]==1 || playingBoard[tempX-1][tempY+1]==11) && playingBoard[tempX-2][tempY+2]==3) {
												potentialMoves.add(tempX+ " "+ tempY);
												potentialMoves.add(tempX-2+" "+(tempY+2));
												help=false;
											}
										}
										if((tempY-2 >=0)&&tempX+2<=board.length-1) {
											if((playingBoard[tempX+1][tempY-1]==1 || playingBoard[tempX+1][tempY-1]==11) && playingBoard[tempX+2][tempY-2]==3) {
												potentialMoves.add(tempX+" "+ tempY);
												potentialMoves.add(tempX+2+" "+ (tempY-2));
												help=false;
											}
										}
										if((tempY+2<=board.length-1)&&tempX+2<=board.length-1) {
											if((playingBoard[tempX+1][tempY+1]==1|| playingBoard[tempX+1][tempY+1]==11) && playingBoard[tempX+2][tempY+2]==3) {
												potentialMoves.add(tempX+ " "+ tempY);
												potentialMoves.add(tempX+2+" "+(tempY+2));
												help=false;
											}
										}
									}
									//--------------------------------------------------------------- 
								}
							}
						}
					} 
					if(potentialMoves.size()==0) {// Basically if theres no double jump, its white's turn
						help=true;
						condition=false;
						counter1++;
					}
				} 

					
				
				if(condition) {//Run all code below	
					for(int i=0; i<pieces[1].length; i++) {
						if(pieces[1][i].getX()==x && pieces[1][i].getY()==y && !pieces[1][i].isDead()) {
							if (pieces[1][i].isValid(x,y,desX,desY)==1) {
								pieces[1][i].setX(desX);
								pieces[1][i].setY(desY);
								playingBoard[x][y]=3;
								playingBoard[desX][desY]=pieces[1][i].getID();
								counter1++;
							}
						}
					}
				}
			}
			//--- Black Turn 
			//--- Black turn
			//--- Black turn
			
			else{
				if(help) {
					for(int i=0; i<pieces[0].length; i++) {
						int tempX=pieces[0][i].getX();
						int tempY=pieces[0][i].getY();
						if(!pieces[0][i].isKing() && !pieces[0][i].isDead()) {
							if((tempY-2 >=0)&&tempX+2<=board.length-1) {
								if((playingBoard[tempX+1][tempY-1]==2 || playingBoard[tempX+1][tempY-1]==22) && playingBoard[tempX+2][tempY-2]==3) {
									potentialMoves.add(tempX+" "+ tempY);
									potentialMoves.add(tempX+2+" "+ (tempY-2));
									
								}
							}
							if((tempY+2<=board.length-1)&&tempX+2<=board.length-1) {
								if((playingBoard[tempX+1][tempY+1]==2|| playingBoard[tempX+1][tempY+1]==22) && playingBoard[tempX+2][tempY+2]==3) {
									potentialMoves.add(tempX+ " "+ tempY);
									potentialMoves.add(tempX+2+" "+(tempY+2));
								}
							}
						}
						else if(pieces[0][i].isKing() && !pieces[0][i].isDead()) {
							if((tempY-2 >=0) && tempX-2>=0) {
								if((playingBoard[tempX-1][tempY-1]==2 || playingBoard[tempX-1][tempY-1]==22) && playingBoard[tempX-2][tempY-2]==3) {
									potentialMoves.add(tempX+" "+ tempY);
									potentialMoves.add(tempX-2+" "+ (tempY-2));
								}
							}
							if((tempY+2<=board.length-1)&&tempX-2>=0) {
								if((playingBoard[tempX-1][tempY+1]==2 || playingBoard[tempX-1][tempY+1]==22) && playingBoard[tempX-2][tempY+2]==3) {
									potentialMoves.add(tempX+ " "+ tempY);
									potentialMoves.add(tempX-2+" "+(tempY+2));
								}
							}
							if((tempY-2 >=0)&&tempX+2<=board.length-1) {
								if((playingBoard[tempX+1][tempY-1]==2 || playingBoard[tempX+1][tempY-1]==22) && playingBoard[tempX+2][tempY-2]==3) {
									potentialMoves.add(tempX+" "+ tempY);
									potentialMoves.add(tempX+2+" "+ (tempY-2));
								}
							}
							if((tempY+2<=board.length-1)&&tempX+2<=board.length-1) {
								if((playingBoard[tempX+1][tempY+1]==2|| playingBoard[tempX+1][tempY+1]==22) && playingBoard[tempX+2][tempY+2]==3) {
									potentialMoves.add(tempX+ " "+ tempY);
									potentialMoves.add(tempX+2+" "+(tempY+2));
								}
							}
						}
					}
				}
				if(potentialMoves.size()==0) 
					condition=true;
				else{
					for(int j=0; j<potentialMoves.size(); j+=2) {
						if(potentialMoves.get(j).equals(firstclick) && potentialMoves.get(j+1).equals(secondclick)) {
							for(int i=0; i< pieces[0].length; i++) {
								if(pieces[0][i].getX()==x && pieces[0][i].getY() ==y) {
									if(pieces[0][i].isValid(x,y,desX,desY)==2 && !pieces[0][i].isDead()) {
										pieces[0][i].setX(desX);
										pieces[0][i].setY(desY);
										playingBoard[x][y]=3;
										playingBoard[(desX+x)/2][(desY+y)/2]=3;
										kill(x,y,desX,desY,true);//Kills black
										playingBoard[desX][desY]=pieces[0][i].getID();
										potentialMoves.clear();

									}
									for(int k=0; k<playingBoard.length; k++) {
										for(int l=0; l<playingBoard[k].length; l++) {
											if(playingBoard[k][l]==2||playingBoard[k][l]==22) {
												whiteWin=false;
											}
										}
									}
									
									if(whiteWin) {
										repaint();
										JOptionPane.showMessageDialog(null,"White wins!");
									}
									int tempX=pieces[0][i].getX();
									int tempY=pieces[0][i].getY();
									if(!pieces[0][i].isKing() && !pieces[0][i].isDead()) {
										if((tempY-2 >=0)&&tempX+2<=board.length-1) {
											if((playingBoard[tempX+1][tempY-1]==2 || playingBoard[tempX+1][tempY-1]==22) && playingBoard[tempX+2][tempY-2]==3) {
												potentialMoves.add(tempX+" "+ tempY);
												potentialMoves.add(tempX+2+" "+ (tempY-2));
												help=false;
											}
										}
										if((tempY+2<=board.length-1)&&tempX+2<=board.length-1) {
											if((playingBoard[tempX+1][tempY+1]==2|| playingBoard[tempX+1][tempY+1]==22) && playingBoard[tempX+2][tempY+2]==3) {
												potentialMoves.add(tempX+ " "+ tempY);
												potentialMoves.add(tempX+2+" "+(tempY+2));
												help=false;
											}
										}
									}
									else if(pieces[0][i].isKing() && !pieces[0][i].isDead()) {
										if((tempY-2 >=0) && tempX-2>=0) {
											if((playingBoard[tempX-1][tempY-1]==2 || playingBoard[tempX-1][tempY-1]==22) && playingBoard[tempX-2][tempY-2]==3) {
												potentialMoves.add(tempX+" "+ tempY);
												potentialMoves.add(tempX-2+" "+ (tempY-2));
												help=false;
											}
										}
										if((tempY+2<=board.length-1)&&tempX-2>=0) {
											if((playingBoard[tempX-1][tempY+1]==2 || playingBoard[tempX-1][tempY+1]==22) && playingBoard[tempX-2][tempY+2]==3) {
												potentialMoves.add(tempX+ " "+ tempY);
												potentialMoves.add(tempX-2+" "+(tempY+2));
												help=false;
											}
										}
										if((tempY-2 >=0)&&tempX+2<=board.length-1) {
											if((playingBoard[tempX+1][tempY-1]==2 || playingBoard[tempX+1][tempY-1]==22) && playingBoard[tempX+2][tempY-2]==3) {
												potentialMoves.add(tempX+" "+ tempY);
												potentialMoves.add(tempX+2+" "+ (tempY-2));
												help=false;
											}
										}
										if((tempY+2<=board.length-1)&&tempX+2<=board.length-1) {
											if((playingBoard[tempX+1][tempY+1]==2|| playingBoard[tempX+1][tempY+1]==22) && playingBoard[tempX+2][tempY+2]==3) {
												potentialMoves.add(tempX+ " "+ tempY);
												potentialMoves.add(tempX+2+" "+(tempY+2));
												help=false;
											}
										}
									}
								}
							}
						}
					}
					if(potentialMoves.size()==0) {// Basically if theres no double jump, it will be black's turn
						help=true;
						condition=false;
						counter1++;
					}
				}
				
				if(condition) {
					for(int i=0; i<pieces[0].length; i++) {
						if(pieces[0][i].getX()==x && pieces[0][i].getY()==y && !pieces[0][i].isDead()) {
							if (pieces[0][i].isValid(x,y,desX,desY)==1) {
								pieces[0][i].setX(desX);
								pieces[0][i].setY(desY);
								playingBoard[x][y]=3;
								playingBoard[desX][desY]=pieces[0][i].getID();
								counter1++;
								}
							}
						}
					}
				}
			}

		repaint();
	
	}	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

			for(int x=0; x<playingBoard.length; x++) {
				for(int y=0; y<playingBoard[x].length; y++) {
					if(playingBoard[x][y]==3) {//This line means if they move, a piece, I paint over the original spot because null doesnt work very well.
						BufferedImage sqrImg= new BufferedImage(DISPLAY_WIDTH/board.length,DISPLAY_WIDTH/board.length,BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2=sqrImg.createGraphics();
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2.setColor(Color.GRAY);
						g2.fillRect(0,0,DISPLAY_WIDTH/board.length,DISPLAY_WIDTH/board.length);
						g2.dispose();
						board[x][y].setIcon(new ImageIcon(sqrImg));

					}
					else if(playingBoard[x][y]==1) {
						BufferedImage cirImg= new BufferedImage(DISPLAY_WIDTH/board.length-25,DISPLAY_WIDTH/board.length-25,BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2=cirImg.createGraphics();
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2.setColor(Color.WHITE);
						g2.fillOval(0,0,DISPLAY_WIDTH/board.length-25,DISPLAY_WIDTH/board.length-25);
						g2.dispose();
						board[x][y].setIcon(new ImageIcon(cirImg));
					}
					else if(playingBoard[x][y]==2) {
						BufferedImage cirImg= new BufferedImage(DISPLAY_WIDTH/board.length-25,DISPLAY_WIDTH/board.length-25,BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2=cirImg.createGraphics();
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2.setColor(Color.BLACK);
						g2.fillOval(0,0,DISPLAY_WIDTH/board.length-25,DISPLAY_WIDTH/board.length-25);
						g2.dispose();
						board[x][y].setIcon(new ImageIcon(cirImg));
				}
				
					else if(playingBoard[x][y]==11) {
						BufferedImage cirImg= new BufferedImage(DISPLAY_WIDTH/board.length-25,DISPLAY_WIDTH/board.length-25,BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2=cirImg.createGraphics();
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2.setColor(Color.WHITE);
						g2.fillOval(0,0,DISPLAY_WIDTH/board.length-25,DISPLAY_WIDTH/board.length-25);
						g2.setColor(Color.RED);
						g2.fillOval((DISPLAY_WIDTH/board.length-25)/4,(DISPLAY_WIDTH/board.length-25)/4,(DISPLAY_WIDTH/board.length-25)/2,(DISPLAY_WIDTH/board.length-25)/2);
						g2.dispose();
						board[x][y].setIcon(new ImageIcon(cirImg));
					}
					else if(playingBoard[x][y]==22) {
						BufferedImage cirImg= new BufferedImage(DISPLAY_WIDTH/board.length-25,DISPLAY_WIDTH/board.length-25,BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2=cirImg.createGraphics();
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2.setColor(Color.BLACK);
						g2.fillOval(0,0,DISPLAY_WIDTH/board.length-25,DISPLAY_WIDTH/board.length-25);
						g2.setColor(Color.RED);
						g2.fillOval((DISPLAY_WIDTH/board.length-25)/4,(DISPLAY_WIDTH/board.length-25)/4,(DISPLAY_WIDTH/board.length-25)/2,(DISPLAY_WIDTH/board.length-25)/2);
						g2.dispose();
						board[x][y].setIcon(new ImageIcon(cirImg));
				}
			}
		}	
	}	    
}	   

