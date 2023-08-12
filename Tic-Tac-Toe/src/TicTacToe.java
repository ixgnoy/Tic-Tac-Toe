import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth=600;
    int boardHeight=700;

    JFrame frame= new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    JButton[][] board = new JButton[3][3];
    String player1 = "X";
    String player2 = "O";
    String currentPlayer = player1;

    boolean gameOver = false;
    int turns=0;

    int scorePlayer1 = 0;
    int scorePlayer2 = 0;


    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);
        
        textPanel.setLayout(new BorderLayout());
        // Create a new panel for the label and buttons
        JPanel labelAndButtonsPanel = new JPanel();
        labelAndButtonsPanel.setLayout(new BorderLayout());

        // Add the textLabel to the labelAndButtonsPanel
        labelAndButtonsPanel.add(textLabel, BorderLayout.NORTH);

        // Create the Restart button
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 30));
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        // Add the Restart button to the labelAndButtonsPanel
        labelAndButtonsPanel.add(restartButton, BorderLayout.CENTER);

        // Create the Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        exitButton.setBackground(Color.RED);  // Set the background color to red
        exitButton.setForeground(Color.WHITE);  // Set the text color to white
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitGame();
            }
        });
        // Add the Exit button to the labelAndButtonsPanel
        labelAndButtonsPanel.add(exitButton, BorderLayout.SOUTH);

        // Add the labelAndButtonsPanel to the textPanel
        textPanel.add(labelAndButtonsPanel, BorderLayout.CENTER);

        frame.add(textPanel, BorderLayout.NORTH);


        //Create the board 
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                JButton tile= new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile = (JButton)e.getSource(); 
                        if(tile.getText() == ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                 currentPlayer = currentPlayer == player1 ? player2 : player1;
                                 textLabel.setText(currentPlayer + "'s turn.");
                            }

                           
                        }
                        

                    }
                });
            }
        }
    }
    void checkWinner(){
            //Horizontal
            for(int r=0;r<3;r++){
                if(board[r][0].getText() == ""){continue;}

                if(board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()){
                    for(int i=0; i<3; i++){
                        setWinner(board[r][i]);
                    }
                    gameOver=true;
                    return;
                }
            }
            //Vertical
            for(int c=0; c<3; c++){
                if(board[0][c].getText() == "")continue;

                if(board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()){
                    for(int i=0; i<3; i++){
                        setWinner(board[i][c]);
                    }
                    gameOver=true;
                    return;
                }
            }
            //diagonally
            if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != ""){
                for(int i=0; i<3; i++){
                    setWinner(board[i][i]);
                }
                gameOver=true;
                return;
            }
            //anti-diagonally
            if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != ""){
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                gameOver=true;
                return;
            }
            if(turns == 9){
                for(int r=0; r<3; r++){
                    for(int c=0; c<3; c++){
                        setTie(board[r][c]);
                    }
                }
                gameOver=true;
                return;
            }
     }
     void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        if (currentPlayer.equals(player1)) {
            scorePlayer1++;
        } else {
            scorePlayer2++;
        }
        textLabel.setText(String.format("<html><div style='text-align: center; font-size: 20px;'>%s is the Winner! Score: Player1: %d, Player2: %d</div></html>", currentPlayer, scorePlayer1, scorePlayer2));

     }
     void setTie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        scorePlayer1+=0;
        scorePlayer2+=0;
        textLabel.setText(String.format("<html><div style='text-align: center; font-size: 20px;'>%s is the Winner! Score: Player1: %d, Player2: %d</div></html>", currentPlayer, scorePlayer1, scorePlayer2));
     }
     void resetGame() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(Color.DARK_GRAY);
                board[r][c].setForeground(Color.WHITE);
            }
        }
    
        currentPlayer = player1;
        gameOver = false;
        turns = 0;
        textLabel.setText("Tic-Tac-Toe");
    }
    void exitGame() {
        resetGame();  // Reset the game
        scorePlayer1 = 0;  // Clear Player 1 score
        scorePlayer2 = 0;  // Clear Player 2 score
        updateScoreLabel();  // Update the score label to show the cleared scores
    }
    void updateScoreLabel() {
        textLabel.setText(String.format("<html><div style='text-align: center; font-size: 20px;'>%s is the Winner! Score: Player1 %d, Player2 %d</div></html>", currentPlayer, scorePlayer1, scorePlayer2));
    }
    
    
    
}
