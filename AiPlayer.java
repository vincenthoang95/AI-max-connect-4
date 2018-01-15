import java.util.*;

/**
 * This is the AiPlayer class.  It simulates a minimax player for the max
 * connect four game.
 * The constructor essentially does nothing. 
 * 
 * @author james spargo
 *
 */

public class AiPlayer 
{
    /**
     * The constructor essentially does nothing except instantiate an
     * AiPlayer object.
     *
     */
	int MaxDepth = 0;
	int AINextMove = 0;
	int human = 0;
	int computer = 0;
	
    public AiPlayer() 
    {
	// nothing to do here
    }
    
    public void sethuman(int num){
    	human = num;
    }
    public void setcomputer(int num){
    	computer = num;
    }
    
	public int eval(GameBoard currentGame) {
		//currentGame.printGameBoard();
		int first = currentGame.getScore(computer);
		int second = currentGame.getScore(human);
		int diff = first - second;
		
		//System.out.println(diff);
		
		return diff;
		
	}
    
	public int MinValue(GameBoard currentGame, int alpha, int beta, int depth) {
		if (depth == MaxDepth){
			return eval(currentGame);
		}
		
		if( currentGame.getPieceCount() >= 42 ) 
	    {
			return eval(currentGame);
	    }
	    
		int v = Integer.MAX_VALUE;
		int x;
		for (x = 0; x <=  6; x++){
			if(currentGame.isValidPlay(x) == false){
				continue;
			}
			currentGame.tempplayPiece(x);
			//	currentGame.printGameBoard();
			v = Math.min(v, MaxValue(currentGame, alpha, beta, depth+1));
			
			if (v <= alpha){
				currentGame.removePiece(x);
				return v;
			}
			
			beta = Math.min(beta, v);
			currentGame.removePiece(x);
		}
		
		return v;
	}
    
    private int MaxValue(GameBoard currentGame, int alpha, int beta, int depth) {
		if (depth == MaxDepth){
			return eval(currentGame);
		}
		
		if( currentGame.getPieceCount() >= 42 ) 
	    {
			return eval(currentGame);
	    }
	    
		
		int v = Integer.MIN_VALUE;
		int x;
		for (x = 0; x <= 6; x++){
			if(currentGame.isValidPlay(x) == false){
				continue;
			}
			currentGame.tempplayPiece(x);
			//	currentGame.printGameBoard();
			int oldv = v;
			v = Math.max(v, MinValue(currentGame, alpha,beta,depth+1));
			if (oldv < v && depth == 0){
				AINextMove = x;
			}
			
			if (v >= beta){
				currentGame.removePiece(x);
				return v;
			}
			
			alpha = Math.max(alpha, v);
			currentGame.removePiece(x);
			
		}
		return v;
	}


	public void AlphaBetaDecision(GameBoard currentGame) {
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int depth = 0;
		//int v = MaxValue(currentGame, alpha, beta, depth);
		MaxValue(currentGame, alpha, beta, depth);
		//return 0;
	}


	/**
     * This method plays a piece randomly on the board
     * @param currentGame The GameBoard object that is currently being used to
     * play the game.
     * @param depthLevel
     * @return an integer indicating which column the AiPlayer would like
     * to play in.
     */
    public int findBestPlay( GameBoard currentGame, int depthLevel ) 
    {
    	/*
	// start random play code
	Random randy = new Random();
	int playChoice = 99;
	
	playChoice = randy.nextInt( 7 );
	
	while( !currentGame.isValidPlay( playChoice ) )
	    playChoice = randy.nextInt( 7 );
	
	// end random play code
	*/
    MaxDepth = depthLevel;
    
    AlphaBetaDecision(currentGame);
    //System.out.println("THIS IS AI NEXT MOVE " + AINextMove);
	return AINextMove;
    
    }

    
}
