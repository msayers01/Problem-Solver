/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.arithmetic;

import framework.problem.Problem;
/**
 *
 * @author Michael
 */
public class ArithmeticProblem extends Problem {
    
        public ArithmeticProblem() {
            super();
            super.setName("Arithmetic");
            super.setIntroduction(INTRO);
            super.setMover(new ArithmeticMover());
            super.setInitialState(new ArithmeticState(0));
            super.setCurrentState(super.getInitialState());
            super.setFinalState(new ArithmeticState(17));
        }

        private static final String INTRO = 
            "The starting value is zero. " +
            "Your options are to add 3, subtract 5, " +
            "divide by 2, or multiply by 2. " +
            "Find a sequence of operations that results in the value 17.";

    }
