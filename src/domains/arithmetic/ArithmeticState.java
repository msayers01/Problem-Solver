/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.arithmetic;

import framework.problem.State;

/**
 *
 * @author Michael Sayers
 */
public class ArithmeticState extends State {
    
        public ArithmeticState(Integer content){
            this.contents = content;
        }
        /*
        @return true, if contents and contents of other are equal, false 
        if otherwise
        */
        @Override
        public boolean equals(Object other){
            ArithmeticState otherArithmetic = (ArithmeticState) other;
            return this.contents.equals(otherArithmetic.contents);
        }
        /*
        Converts contents to a string and adds it to the STATE string to 
        form one String. "The value is: <contents>"
        @return STATE + str
        */
        @Override
        public String toString() {
            String str = String.valueOf(this.contents);
            return STATE + str;
        }
        /*
        A getter method to help implement ArithmeticMover
        @return contents
        */
        public Integer getContents(){
            return contents;
        }
        
        @Override
        public int hashCode() {
            int hash = 43;
            hash += 7 * this.contents;
            return hash;
        }
        
        private final Integer contents;
        private static final String STATE = "The value is: ";
}
