public class Piece {
	protected int x;
	protected int y;
	protected boolean isWhite;
	protected boolean isKing;
	protected boolean isDead;
	protected int id;
    
	public Piece(int x, int y, boolean isWhite, int id) {
		this.x=x;
		this.y=y;
		this.isWhite=isWhite;
		this.id=id;
		isKing=false;
		isDead=false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public void setX(int newX){
		x=newX;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead() {
		isDead=true;
	}
	public void setY(int newY) {
		y=newY;
	}

	public int getID() {
		return id;
	}
	
	public boolean isWhite() {
		return isWhite;
	}
	
	public boolean isKing() {
		return isKing;
	}
	

	public int isValid(int x, int y, int desX, int desY) {//Return ints instead of booleans
		//Black
		if(!isWhite && !isKing) {	
			if((x-desX==1) && Math.abs(y-desY)==1 && Board.playingBoard[desX][desY]==3) {
				if(desX==0) {//King promotion 
					id=22;
					isKing=true;
					return 1;
				}
				return 1;
			}
		else if((x-desX==2) && Math.abs(y-desY)==2 && (Board.playingBoard[(desX+x)/2][(desY+y)/2]==1 || Board.playingBoard[(desX+x)/2][(desY+y)/2]==11) &&Board.playingBoard[desX][desY]==3) {//Kill move
				if(desX==0) {//King promotion on kill
					id=22;
					isKing=true;
					return 2;
				}
				return 2;
			}
			
			return 0;
		}
		
		//Black king
		else if(!isWhite && isKing) {
			if((x-desX==1) && Math.abs(y-desY)==1 && Board.playingBoard[desX][desY]==3)
				return 1;
			else if((desX-x==1) && Math.abs(desY-y)==1 && Board.playingBoard[desX][desY]==3)
				return 1;
			else if((x-desX==2) && Math.abs(y-desY)==2 && (Board.playingBoard[(desX+x)/2][(desY+y)/2]==1|| Board.playingBoard[(desX+x)/2][(desY+y)/2]==11)&&Board.playingBoard[desX][desY]==3) {//Kill move
				return 2;
			}
			else if((desX-x==2) && Math.abs(desY-y)==2 && (Board.playingBoard[(desX+x)/2][(desY+y)/2]==1|| Board.playingBoard[(desX+x)/2][(desY+y)/2]==11)&&Board.playingBoard[desX][desY]==3) {//Kill move
				return 2;
			}
			return 0;
		}
		//White
		else if(isWhite && !isKing){
			if((desX-x==1) && Math.abs(desY-y)==1 && Board.playingBoard[desX][desY]==3) {
				if(desX==Board.board.length-1) {//King promotion
					id=11;
					isKing=true;
					return 1;
				}
				return 1;
			}
			else if((desX-x==2) && Math.abs(desY-y)==2 && (Board.playingBoard[(desX+x)/2][(desY+y)/2]==2 || Board.playingBoard[(desX+x)/2][(desY+y)/2]==22) &&Board.playingBoard[desX][desY]==3) {//Kill move
				if(desX==Board.board.length-1) {//King promotion on kill
					id=11;
					isKing=true;
					return 2;
				}
				return 2;
			}

			return 0;
		}
		//White King
		else{
			if((x-desX==1) && Math.abs(y-desY)==1 && Board.playingBoard[desX][desY]==3)
				return 1;
			else if((desX-x==1) && Math.abs(desY-y)==1 && Board.playingBoard[desX][desY]==3)
				return 1;
			else if((x-desX==2) && Math.abs(y-desY)==2 && (Board.playingBoard[(desX+x)/2][(desY+y)/2]==2 || Board.playingBoard[(desX+x)/2][(desY+y)/2]==22) && Board.playingBoard[desX][desY]==3) {//Kill move
				return 2;
			}
			else if((desX-x==2) && Math.abs(desY-y)==2 && (Board.playingBoard[(desX+x)/2][(desY+y)/2]==2|| Board.playingBoard[(desX+x)/2][(desY+y)/2]==22)&&Board.playingBoard[desX][desY]==3) {//Kill move 
				return 2;
			}
			
			return 0;
		}
		
	}
	
}