package domains.farmer;

import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author Michael Sayers
 */

public class FarmerMover extends Mover {

        public static final String FARMER_ALONE = "Farmer Goes Alone";
        public static final String FARMER_WOLF = "Farmer Takes Wolf";
        public static final String FARMER_GOAT = "Farmer Takes Goat";
        public static final String FARMER_CABBAGE = "Farmer Takes Cabbage";
        
        public FarmerMover() {
            super.addMove(FARMER_ALONE, s -> tryFarmerAlone(s));
            super.addMove(FARMER_WOLF, s -> tryFarmerWolf(s));
            super.addMove(FARMER_GOAT, s -> tryFarmerGoat(s));
            super.addMove(FARMER_CABBAGE, s -> tryFarmerCabbage(s));
        }
        
        private State tryFarmerAlone(State state) {
            FarmerState s = (FarmerState)state;
            String farmer = s.getFarmer(); 
            String wolf = s.getWolf();     
            String goat = s.getGoat();     
            String cabb = s.getCabbage();  
            
            
            if( (goat.equals(cabb) && goat.equals(farmer)) ||
                   (goat.equals(wolf) && goat.equals(farmer)) )
            {
                return null;
            }
            else if (farmer.equals("West"))
            {
                return new FarmerState("East", wolf, goat, cabb);
            }
            else
            {
            return new FarmerState("West", wolf, goat, cabb);
            }
        }

        private State tryFarmerWolf(State state) {
            FarmerState s = (FarmerState)state;
            String farmer = s.getFarmer();
            String wolf = s.getWolf();
            String goat = s.getGoat();
            String cabb = s.getCabbage();
            
            if ( (goat.equals(cabb) && goat.equals(farmer)) || 
                    (!wolf.equals(farmer)) ) {
                return null;
            }
            else if (wolf.equals("West")){
                return new FarmerState("East", "East", goat, cabb);
            }
            else {
                return new FarmerState("West", "West", goat, cabb);
            }
        }

        private State tryFarmerGoat(State state) {
            FarmerState s = (FarmerState)state;
            String farmer = s.getFarmer();
            String wolf = s.getWolf();
            String goat = s.getGoat();
            String cabb = s.getCabbage();
            
            if ( !goat.equals(farmer)) {
                return null;
            }
            else if(farmer.equals("West")){
                return new FarmerState("East", wolf, "East", cabb);
            }
            else {
                return new FarmerState("West", wolf, "West", cabb);
            }
        }

        private State tryFarmerCabbage(State state) {
            FarmerState s = (FarmerState)state;
            String farmer = s.getFarmer();
            String wolf = s.getWolf();
            String goat = s.getGoat();
            String cabb = s.getCabbage();
            
            if((goat.equals(wolf) && goat.equals(farmer)) ||
                    (!cabb.equals(farmer)) ){
                return null;
            }
            else if(farmer.equals("West")){
                return new FarmerState("East", wolf, goat, "East");
            }
            else {
                return new FarmerState("West", wolf, goat, "West");
            }
        }

       
    }