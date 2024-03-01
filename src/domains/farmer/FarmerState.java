/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.farmer;

import framework.problem.State;
import java.util.Objects;

/**
 *
 * @author Michael Sayers
 */
public class FarmerState extends State {
    
        public FarmerState(String farmer, String wolf, String goat, String cabbage){
            this.Farmer = farmer;
            this.Wolf = wolf;
            this.Goat = goat;
            this.Cabbage = cabbage;
        }
        /*
        @return true, if contents and contents of other are equal, false 
        if otherwise
        */
        @Override
        public boolean equals(Object other){
            if(other == null) return false;
            if (!other.getClass().equals(this.getClass()) ) return false;
            FarmerState otherFarmer = (FarmerState)other;
            return (this.Farmer.equals(otherFarmer.Farmer) &&
                    this.Wolf.equals(otherFarmer.Wolf) &&
                    this.Goat.equals(otherFarmer.Goat) &&
                    this.Cabbage.equals(otherFarmer.Cabbage));
        }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.Farmer);
        hash = 59 * hash + Objects.hashCode(this.Wolf);
        hash = 59 * hash + Objects.hashCode(this.Goat);
        hash = 59 * hash + Objects.hashCode(this.Cabbage);
        return hash;
    }
        /*
        Converts contents to a string and adds it to the STATE string to 
        form one String. "The value is: <contents>"
        @return STATE + str
        */
        @Override
        public String toString() {
            if(this.Farmer.equals("West")){
                string = "   |  |   \n" + 
                         FarmerWest;
            }
            else if(this.Farmer.equals("East")){
                string = "   |  |   \n" + 
                         FarmerEast;
            }
            if(this.Wolf.equals("West")){
                string = string + WolfWest;
            }
            else {
                string = string + WolfEast;
            }
            if(this.Goat.equals("West")){
                string = string + GoatWest;
            }
            else {
                string = string + GoatEast;
            }
            if(this.Cabbage.equals("West")){
                string = string + CabbageWest;
            }
            else{
                string = string + CabbageEast;
            }
            string = string + "   |  |   ";
            return string;
        }
        
        public String getFarmer() {
            return Farmer;
        }
        
        public String getWolf() {
            return Wolf;
        }
        
        public String getGoat() {
            return Goat;
        }
        
        public String getCabbage() {
            return Cabbage;
        }
        
        
        private final String Farmer;
        private final String Wolf;
        private final String Goat;
        private final String Cabbage;
        private String string;
        
        private final String FarmerWest =  " F |  |   \n";
        private final String FarmerEast =  "   |  | F \n";
        private final String WolfWest =    " W |  |   \n";
        private final String WolfEast =    "   |  | W \n";
        private final String GoatWest =    " G |  |   \n";
        private final String GoatEast =    "   |  | G \n";
        private final String CabbageWest = " C |  |   \n";
        private final String CabbageEast = "   |  | C \n";
}