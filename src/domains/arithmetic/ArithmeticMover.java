/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.arithmetic;

import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author Michael Sayers
 */
public class ArithmeticMover extends Mover {
    
        public static final String ADD = "Add 3";
        public static final String SUB = "Subtract 5";
        public static final String DIV = "Divide by 2";
        public static final String MUL = "Multiply by 2";
        
        public ArithmeticMover(){
            super.addMove(ADD, s -> tryAdd(s));
            super.addMove(SUB, s -> trySub(s));
            super.addMove(DIV, s -> tryDiv(s));
            super.addMove(MUL, s -> tryMul(s));
        }
        /*
        @return a new ArithmeticState that contains the value of 
        the previous ArithmeticState value + 3
        */
        private State tryAdd(State state){
            ArithmeticState s = (ArithmeticState)state;
            String move = s.getMove();
            return new ArithmeticState(s.getContents() + 3);
        }
        /*
        @return a new ArithmeticState that is 5 less than the ArithmeticState that was passed
        into this method.
        */
        private State trySub(State state){
            ArithmeticState s = (ArithmeticState) state;
            return new ArithmeticState(s.getContents() - 5);
            
        }
        /*
        @return a new ArithmeticState that contains a value equal to 
        the previous state value divided by 2
        */
        private State tryDiv(State state){
            ArithmeticState s = (ArithmeticState) state;
            return new ArithmeticState(s.getContents() / 2);
        }
        /*
        @return a new ArithmeticState that contains a value equal to the previous ArithmeticState
        Integer value multplied by 2
        */
        private State tryMul(State state){
            ArithmeticState s = (ArithmeticState) state;
            return new ArithmeticState(s.getContents() * 2);
        }
}
