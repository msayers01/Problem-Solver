/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.ui;

import framework.graph.Vertex;
import framework.problem.Benchmark;
import framework.solution.SolvingAssistant;
import framework.solution.Solver;
import framework.solution.StateSpaceSolver;
import framework.solution.AStarSolver;
import framework.solution.BFSGraphSolver;
import framework.solution.BFSStateSpaceSolver;
import framework.solution.BestFirstSolver;
import framework.solution.DFSGraphSolver;
import framework.solution.DFSStateSpaceSolver;

import javafx.scene.layout.VBox;
import framework.problem.Problem;
import framework.problem.State;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Michael 
 */
public class ProblemGUI extends VBox {
    
    public ProblemGUI(Problem problem, double width, double height) {
        solveAssist = new SolvingAssistant(problem);
        BFStateSpace = new BFSStateSpaceSolver(problem);
        DFStateSpace = new DFSStateSpaceSolver(problem);
        BestFirst = new BestFirstSolver(problem);
        AStar = new AStarSolver(problem);
        
        super.setPrefSize(width, height);
        super.setBackground(new Background(new BackgroundFill(Color.rgb(51, 128, 242), CornerRadii.EMPTY, Insets.EMPTY)));
        makeIntro(problem);
        makeActionBox(problem);
        makeStatsBox(problem);
        super.getChildren().addAll(introBox, actionBox, statsBox);
        
    }
    
    /* Instance fields and helper methods here */
    
    private void makeIntro(Problem problem) {
        welcomeMsg = new Label("Welcome to the " + problem.getName() + " Problem");
        welcomeMsg.setFont(new Font("Verdana", 30));
        welcomeMsg.setTextFill(Color.DARKORANGE);
        welcomeMsg.setWrapText(true);
        
        StringBuilder builder = new StringBuilder();
        builder.append("\n\n");
        builder.append(problem.getIntroduction());
        builder.toString();
        
        intro = new Label(builder.toString());
        intro.setWrapText(true);
        intro.setFont(new Font("Sans", 16));
        intro.setTextFill(Color.LIGHTYELLOW);
        introBox = new VBox();
        introBox.setBackground(new Background(new BackgroundFill(Color.rgb(19, 7, 47), CornerRadii.EMPTY, Insets.EMPTY)));
        introBox.setPadding(new Insets(30));
        introBox.setAlignment(Pos.TOP_CENTER);
        introBox.getChildren().addAll(welcomeMsg, intro);
     
    }
    
    private void makeActionBox(Problem problem) {
        
        /* creating current state Box */
        currentStateLabel = new Label("Current State:");
        currentStateLabel.setWrapText(true);
        currentStateLabel.setFont(new Font("Sans", 18));
        currentState = new Label(problem.getCurrentState().toString());
        currentState.setContentDisplay(ContentDisplay.LEFT);
        currentState.setFont(new Font("Consolas", 24));
        currentState.setWrapText(true);
        currentState.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        currentState.setPadding(new Insets(10));
        currentState.setAlignment(Pos.CENTER);
        
        currentBox = new VBox();
        currentBox.setPadding(new Insets(20));
        currentBox.getChildren().addAll(currentStateLabel, currentState);
        
        /* creating moves Box */
        movesLabel = new Label("Moves (" + solveAssist.getMoveCount() + " so far):" );
        movesLabel.setFont(new Font("Sans", 18));
        movesLabel.setWrapText(true);
        
        moveResponse = new Text("");
        moveResponse.setFill(Color.RED);
        moveResponse.setFont(new Font("Sans", 14));
        
        movesLabel.setPadding(new Insets(10));
        movesBox = new VBox(10);
        movesBox.setAlignment(Pos.CENTER);
        movesBox.setPadding(new Insets(10));
        movesBox.getChildren().add(movesLabel);
        makeButtons(problem);
        reset = new Button("Reset");
        
        reset.setOnAction(e -> {
            solveAssist.reset(); 
            problem.setCurrentState(problem.getInitialState()); 
            currentState.setText(problem.getCurrentState().toString());
            moveResponse.setText("");
            movesLabel.setText("Moves (0 so far):");
            
            for(int i = 0; i < buttons.size(); i++){
                buttons.get(i).disableProperty().set(false);
                buttons.get(i).setStyle(defaultStyleEnabled);
                Solve.disableProperty().set(false);
                bench.disableProperty().set(false);
                Next.disableProperty().set(true);
            }
        });
        
        movesBox.getChildren().addAll(moveResponse, reset);
        
        
        /* Creating Goal State Box */
        goalLabel = new Label("Final State:");
        goalLabel.setWrapText(true);
        goalLabel.setFont(new Font("Sans", 18));
        goalState = new Label(problem.getFinalState().toString());
        goalState.setContentDisplay(ContentDisplay.RIGHT);
        goalState.setFont(new Font("Consolas", 24));
        goalState.setWrapText(true);
        goalState.setBorder(new Border(new BorderStroke(Color.BLACK, 
           BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        goalState.setPadding(new Insets(10));
        goalState.setAlignment(Pos.CENTER);
        
        goalBox = new VBox();
        goalBox.setPadding(new Insets(20));
        goalBox.getChildren().addAll(goalLabel, goalState);
        
        /* Making the 'Action Box' */
        actionBox = new HBox();
        actionBox.setBackground(new Background(new BackgroundFill(Color.rgb(51, 128, 242), CornerRadii.EMPTY, Insets.EMPTY)));
        actionBox.setPadding(new Insets(5));
        actionBox.setAlignment(Pos.BOTTOM_CENTER);
        actionBox.getChildren().addAll(currentBox, movesBox, goalBox);
        
        /* Creating the solve and statistics area */
        
        
    }
    
    private void makeStatsBox(Problem problem) {
        
        
        /* making first two buttons */ 
        Solve = new Button("Solve");
        Next = new Button("Next");
        solve = new VBox(50);
        solve.getChildren().addAll(Solve, Next);
        solve.setAlignment(Pos.CENTER);
        buttonsColor = new ArrayList<>();
        Solve.setOnAction( e -> {
           solver.solve();
           stats.setText(solver.getStatistics().toString());
           Solve.disableProperty().set(true);
           bench.disableProperty().set(true);
           for(Button move : buttons){
               move.disableProperty().set(true);
           }
           Next.disableProperty().set(false);
        });
        
        Next.disableProperty().set(true);
        
        Next.setOnAction(e-> { 
            
             
            if(solver.getSolution().hasNext()) {
                
                State next = (State)solver.getSolution().next().getData();
                
                if(problem.getCurrentState().equals(next)){
                   next = (State)solver.getSolution().next().getData();
                   defaultStyle = buttons.get(0).getStyle();
                }
                
                problem.setCurrentState(next);
                solveAssist.update(next);
                movesLabel.setText("Moves (" + solveAssist.getMoveCount() + " so far):");
                currentState.setText(next.toString());
                String move = problem.getCurrentState().getMove();
                int indexOfMove = problem.getMover().getMoveNames().indexOf(move);
                
                if(buttonsColor.size() > 0){
                   
                    buttonsColor.get(buttonsColor.size() - 1).setStyle(defaultStyle);
                    
                    if(buttonsColor.size() > 1){
                        buttonsColor.get(buttonsColor.size() - 2).setStyle(defaultStyle);
                    }
                }
                    
                buttons.get(indexOfMove).setStyle(
                "-fx-background-color: red;" + 
                            "-fx-text-fill: white"
                );
                buttonsColor.add(buttons.get(indexOfMove));
                
                
                
                if(solveAssist.isProblemSolved()) {
                      moveResponse.setText("Congratulations!");
                      Next.disableProperty().set(true);
                  }             
                
                
           }
            
    });
       
        
        
        /* making Search choice box */
        searchBox = new VBox(10);
       
        searchBox.setAlignment(Pos.CENTER);
        searchLabel = new Label("Search Type");
        search = new ChoiceBox();
        search.setValue("BF State Space Search");
        solver = BFStateSpace;
        search.getItems().add("BF State Space Search");
        search.getItems().add("DF State Space Search");
        search.getItems().add("Best-First Search");
        search.getItems().add("A* Search");
        search.setOnAction( e -> {
            if((String)search.getValue() == "BF State Space Search")
            {
                solver = BFStateSpace;
            }
            if((String)search.getValue() == "DF State Space Search")
            {
                solver = DFStateSpace;
            }
            if((String)search.getValue() == "Best-First Search")
            {
                solver = BestFirst;
            }
            if((String)search.getValue() == "A* Search")
            {
                solver = AStar;
            }
        });
        searchBox.getChildren().addAll(searchLabel, search);
        
        /* making Statistics Label and Box area */
        statsLabel = new Label("Statistics");
        stats = new Label();
        
        stats.setWrapText(true);
        stats.setFont(new Font("Sans", 18));
        stats.setContentDisplay(ContentDisplay.RIGHT);
        stats.setFont(new Font("Courier", 14));
        stats.setWrapText(true);
        stats.setBorder(new Border(new BorderStroke(Color.BLACK, 
           BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        stats.setPadding(new Insets(10));
        stats.setPrefSize(200, 200);
        stats.setAlignment(Pos.CENTER);
        
        statsView = new VBox(10);
        statsView.getChildren().addAll(statsLabel, stats);
        
        
        /* Creating benchmark list */
        benchBox = new VBox(10);
        benchBox.setAlignment(Pos.CENTER);
        benchLabel = new Label("Benchmarks");
        benchLabel.setFont(new Font("Sans", 18));
        bench = new ChoiceBox<>(FXCollections.observableArrayList(problem.getBenchmarks()));
        
        bench.setOnAction(a -> {
            
            Benchmark b = bench.getValue();
            problem.setCurrentState(b.getStart());
            currentState.setText(b.getStart().toString());
            solveAssist.reset();
            solveAssist.getProblem().setCurrentState(b.getStart());
            movesLabel.setText("Moves (0 so far):");
            for(Button button : buttons){
                
                button.setOnAction(e-> {
                    
                    
                    
                    for(String moveName : problem.getMover().getMoveNames()) {
             
                        State currState = problem.getCurrentState();
                        State next = problem.getMover().doMove(moveName, currState);
                        solveAssist.tryMove(moveName);

                            if(next != null){
                              problem.setCurrentState(next);
                              moveResponse.setText("");
                              currentState.setText(problem.getCurrentState().toString());
                              movesLabel.setText("Moves (" + solveAssist.getMoveCount() + " so far):");
                                
                              if(problem.getCurrentState() == problem.getFinalState()) 
                                    solveAssist.setProblemSolved(true);

                                if(solveAssist.isProblemSolved()) {
                                    moveResponse.setText("Congratulations!");
                                }
                            } 

                            else {
                              moveResponse.setText("Illegal Move. Try again.");
                            }
                    }
                
                });
            }
            
           });
        benchBox.getChildren().addAll(benchLabel, bench);
        
        
        statsBox = new HBox(50);
        statsBox.setAlignment(Pos.CENTER);
        statsBox.setPadding(new Insets(50, 50, 50, 50));
        statsBox.getChildren().addAll(solve, searchBox, statsView, benchBox);
        
        
        
    }
    
    /* This method creates an array of buttons, corresponding to the move name list, then adds a setOnAction for each button,
    then adds each button to the actionBox
    */
    private void makeButtons(Problem problem) {
      buttons = new ArrayList<>(); 
      for(int i = 0; i < problem.getMover().getMoveNames().size(); i++){
          
          String moveName = (String)problem.getMover().getMoveNames().get(i);
          
          buttons.add(new Button(problem.getMover().getMoveNames().get(i)));
          buttons.get(i).setWrapText(true);
          buttons.get(i).setOnAction( (ActionEvent e) -> {
          
          State currState = problem.getCurrentState();
          State next = problem.getMover().doMove(moveName, currState);
          solveAssist.tryMove(moveName);
              if(next != null){
                  problem.setCurrentState(next);
                  moveResponse.setText("");
                  currentState.setText(problem.getCurrentState().toString());
                  movesLabel.setText("Moves (" + solveAssist.getMoveCount() + " so far):");
                  if(problem.getCurrentState() == problem.getFinalState()) solveAssist.setProblemSolved(true);
                  if(solveAssist.isProblemSolved()) {
                      moveResponse.setText("Congratulations!");
                  }
              }
          
              else {
                  moveResponse.setText("Illegal Move. Try again.");
              }
          });
      }
      for(int i = 0; i < buttons.size(); i++){
          
          buttons.get(i).setAlignment(Pos.CENTER);
          buttons.get(i).setWrapText(true);
          movesBox.getChildren().add(buttons.get(i));
          defaultStyleEnabled = buttons.get(0).getStyle();
      }
      
    }

    private StateSpaceSolver AStar;
    private StateSpaceSolver BFStateSpace;
    private StateSpaceSolver DFStateSpace;
    private StateSpaceSolver BestFirst;
    private Solver solver;
    private SolvingAssistant solveAssist;
    private ArrayList<Button> buttons;
    
    /* Intro objects */
    private Label welcomeMsg;
    private Label intro;
    private VBox introBox;
    
    /*Action Objects */
    private Label currentStateLabel;
    private Label currentState;
    private Label movesLabel;
    private Label goalLabel;
    private Label goalState;
    private Text moveResponse;
    private Button reset;
    private HBox actionBox;
    private VBox currentBox;
    private VBox goalBox;
    private VBox movesBox;
    
    /* Stats Objexts */
    private HBox statsBox;
    private VBox solve;
    private VBox searchBox;
    private VBox statsView;
    private VBox benchBox;
    
    private Button Solve;
    private Button Next;
    private ChoiceBox search;
    private ChoiceBox<Benchmark> bench;
    private Label searchLabel;
    private Label statsLabel;
    private Label benchLabel;
    private Label stats;
    
    private ArrayList<Button> buttonsColor;
    private String defaultStyle;
    private String defaultStyleEnabled;
    
}
