/**
 * This class represents a game of 'Four in a line'.
 * In each turn, every player clicks on a column to put their disc.
 * If on of the player get 4 discs in line\column\diagonal, he wins.
 * If no one wins - it's a tie!
 * @author Asia Zhivov
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FourInLineGameController {
	 // declaration of variables and constants we use in class
	final int NUM_OF_COLS = 7;
	final int NUM_OF_ROWS = 7;
	final double RADIUS = 35; // grid width/7/2
	final double MARGIN = 70; // grid width/7
	final int PLAYER_ONE = 1;
	final int PLAYER_TWO = 2;
	int currentPlayer=PLAYER_ONE; // player 1 is the first to play
	int currentCol;
	
	Circle curCircle;
	
	GameMatrix gameBoard = new GameMatrix();
	ArrayList<Circle> circleList = new ArrayList<Circle>(); // array list to save all discs

	@FXML
    private GridPane grid;

	// initialize the board game
    @FXML
    public void initialize() {
    	
    	// create a line of buttons which represent the columns of game matrix
    	Button[] colButtons = new Button[NUM_OF_COLS];
    	for(int i=0; i<colButtons.length; i++) {
    		colButtons[i] = new Button(String.valueOf(i+1));
    		colButtons[i].setPrefSize(MARGIN,MARGIN);
    		grid.add(colButtons[i], i, NUM_OF_ROWS-1);
    		
    		// each button set to click mouse action
    		colButtons[i].setOnAction(new EventHandler<ActionEvent>() {
    			public void handle(ActionEvent e) {
    				handleButton(e);
    			}
    		});
    	}
    }

    // clear the board game from discs and start a new game
    @FXML
    void clearPressed(ActionEvent event) {
    	gameBoard = new GameMatrix();
    	currentPlayer=PLAYER_ONE;
    	
    	// clear the discs from gridpane
    	for(Circle curCircle : circleList) {
    	        grid.getChildren().remove(curCircle);
    	  } 
    	
    

    	// reset the array list of discs
    	circleList= new ArrayList<Circle>();	
    }
    
    private void handleButton(ActionEvent e) {
    	// using the text in the button to determine the current row
    	currentCol = Integer.parseInt(((Button)e.getSource()).getText())-1;
    	
    	// add disc to current column to an available row
    	// if there is no space in column, show message and return to game
    	int availableRow = gameBoard.addDisc(currentCol,currentPlayer);
    	if (availableRow==-1) {
    		JOptionPane.showMessageDialog(null,"No space in column!");
    		return;
    	}
    	
    	// determine players' turn
    	if (currentPlayer == PLAYER_ONE) {
    		curCircle = new Circle(RADIUS,Color.RED); // player 1 has red discs
    	}
    	else if (currentPlayer == PLAYER_TWO) {
    		curCircle = new Circle(RADIUS,Color.BLUE); // player 2 has blue discs
    	}
    	
    	// add the disc to grid
    	grid.add(curCircle,currentCol,availableRow);
    	circleList.add(curCircle);
    	
    	// check if we have a winner after we draw a disc to grid
    	// if we have a winner, show message and exit the game
    	boolean a = gameBoard.isThereAWinner(availableRow,currentCol);
    	if (a) {
    		JOptionPane.showMessageDialog(null,"Player " + currentPlayer + " is the winner!!!");
    		System.exit(0);
    	}
    	
    	// check if we reached a tie, if so - exit the program
    	if (gameBoard.isTie()) {
    		JOptionPane.showMessageDialog(null,"It's a tie!!!");
    		System.exit(0);
    	}
    	
    	// switch the current player to the other player for the next turn
    	currentPlayer=currentPlayer%2 + 1;
    }
}