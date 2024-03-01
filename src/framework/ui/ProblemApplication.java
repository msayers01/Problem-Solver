package framework.ui;

import domains.arithmetic.ArithmeticProblem;
import domains.dummy.DummyProblem;
import domains.farmer.FarmerProblem;
import domains.puzzle.PuzzleProblem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * This class presents problem solving domains in a tabbed pane.
 * @author Michael Sayers
 */
public class ProblemApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        
	/* Add tabs using the following */

	Tab tab = new Tab();
        tab.setText("Farmer, Wolf, Goat, Cabbage");
        tab.setContent(new ProblemGUI(new FarmerProblem(), 1000, 950));
        tabPane.getTabs().add(tab);

	Tab tab1 = new Tab();
        tab1.setText("Arithmetic");
        tab1.setContent(new ProblemGUI(new ArithmeticProblem(), 1000, 950));
        tabPane.getTabs().add(tab1);
        
        Tab tab2 = new Tab();
        tab2.setText("Dummy");
        tab2.setContent(new ProblemGUI(new DummyProblem(), 1000, 950));
        tabPane.getTabs().add(tab2);
        
        Tab tab3 = new Tab();
        tab3.setText("8-Puzzle");
        tab3.setContent(new ProblemGUI(new PuzzleProblem(), 1000, 950));
        tabPane.getTabs().add(tab3);
        
        Scene scene = new Scene(tabPane);
        primaryStage.setTitle("Problem Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}