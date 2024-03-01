package domains.puzzle;

import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author Michael Sayers
 */

public class PuzzleMover extends Mover {

        public static final String SLIDE1 = "Slide Tile 1";
        public static final String SLIDE2 = "Slide Tile 2";
        public static final String SLIDE3 = "Slide Tile 3";
        public static final String SLIDE4 = "Slide Tile 4";
        public static final String SLIDE5 = "Slide Tile 5";
        public static final String SLIDE6 = "Slide Tile 6";
        public static final String SLIDE7 = "Slide Tile 7";
        public static final String SLIDE8 = "Slide Tile 8";
        
        public PuzzleMover() {
            super.addMove(SLIDE1, s -> slideTile1(s));
            super.addMove(SLIDE2, s -> slideTile2(s));
            super.addMove(SLIDE3, s -> slideTile3(s));
            super.addMove(SLIDE4, s -> slideTile4(s));
            super.addMove(SLIDE5, s -> slideTile5(s));
            super.addMove(SLIDE6, s -> slideTile6(s));
            super.addMove(SLIDE7, s -> slideTile7(s));
            super.addMove(SLIDE8, s -> slideTile8(s));
            
        }
      
        private Boolean isAdjacent(PuzzleState state, int Tile)
        {
            int tileRow = state.getLocation(Tile).getRow();
            int tileCol = state.getLocation(Tile).getColumn();
            int blankRow = state.getLocation(0).getRow();
            int blankCol = state.getLocation(0).getColumn();
            
            int rowDifference = tileRow - blankRow;
            int colDifference = tileCol - blankCol;
            if( ((Math.abs(rowDifference) == 1) && (colDifference) == 0) ^ 
                    ((Math.abs(colDifference) == 1) && rowDifference == 0) ) return true; // The ^ operand is XOR, so if the difference of row and column are both 1, then it is not adjacent
            else return false;
        }   
            
        
        
        private State slideTile1(State state) {
            PuzzleState s = PuzzleState.Copy((PuzzleState)state);
            if(!isAdjacent(s, 1)) {
                return illegalMove();
            }
            
            return new PuzzleState(s, s.getLocation(0), s.getLocation(1));
        }
        
        private State slideTile2(State state) {
            PuzzleState s = PuzzleState.Copy((PuzzleState)state);
            if(!isAdjacent(s, 2)) {
                return illegalMove();
            }
            
            return new PuzzleState(s, s.getLocation(0), s.getLocation(2));
        }
        
        private State slideTile3(State state) {
            PuzzleState s = PuzzleState.Copy((PuzzleState)state);
            if(!isAdjacent(s, 3)) {
               return illegalMove();
            }
            
            return new PuzzleState(s, s.getLocation(0), s.getLocation(3));
        }
        
        private State slideTile4(State state) {
            PuzzleState s = PuzzleState.Copy((PuzzleState)state);
            if(!isAdjacent(s, 4)) {
                return illegalMove();
            }
            
            return new PuzzleState(s, s.getLocation(0), s.getLocation(4));
        }
        
        private State slideTile5(State state) {
            PuzzleState s = PuzzleState.Copy((PuzzleState)state);
            if(!isAdjacent(s, 5)) {
                return illegalMove();
            }
            
            return new PuzzleState(s, s.getLocation(0), s.getLocation(5));
        }
        
        private State slideTile6(State state) {
            PuzzleState s = PuzzleState.Copy((PuzzleState)state);
            if(!isAdjacent(s, 6)) {
                return illegalMove();
            }
            
            return new PuzzleState(s, s.getLocation(0), s.getLocation(6));
        }
        
        private State slideTile7(State state) {
            PuzzleState s = PuzzleState.Copy((PuzzleState)state);
            if(!isAdjacent(s, 7)) {
                return illegalMove();
            }
            
            return new PuzzleState(s, s.getLocation(0), s.getLocation(7));
        }
        
        private State slideTile8(State state) {
            PuzzleState s = PuzzleState.Copy((PuzzleState)state);
            if(!isAdjacent(s, 8)) {
                return illegalMove();
            }
            
            return new PuzzleState(s, s.getLocation(0), s.getLocation(8));
        }

        private State illegalMove() {
            return null;
        }
       
    }