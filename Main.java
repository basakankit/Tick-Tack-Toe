import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;//option box that pop for output/input

public class Main{
    private static char player='X';
    private static JButton[][] buttons=new JButton[3][3];
    private static char[][] board = new char[3][3];
    
    public static void main(String[] args){
        initializeBoard();

        JFrame frame =new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));

        for(int row=0;row<3;row++){
            for(int col=0;col<3;col++){
                buttons[row][col]=new JButton(" ");
                buttons[row][col].setFont(buttons[row][col].getFont().deriveFont(50.0f));
                final int r = row;
                final int c = col;
                
                buttons[row][col].addActionListener(new ActionListener(){
                
                    public void actionPerformed(ActionEvent e){
                        if(board[r][c]==' ' &&!isGameOver()){
                            board[r][c]=player;
                            buttons[r][c].setText(String.valueOf(player));
                            if(haveWon(player)){
                                JOptionPane.showMessageDialog(frame, "Player "+ player+" has won!");
                                resetBoard();
                            }
                            else if(isDraw()){
                                JOptionPane.showMessageDialog(frame, "The game is a draw!");//frame keeps the dialog centered around parent dialog
                                resetBoard();
                            }
                            else{
                                player = (player == 'X') ? 'O' : 'X';
                            }
                        }
                    }
                });
                frame.add(buttons[row][col]);
            }
        }
        frame.setVisible(true);
        
    }
    private static void initializeBoard(){
        for(int row=0;row<3;row++){
            for(int col=0;col<3;col++){
                board[row][col]=' ';
            }
        }
    }
        
        
        private static boolean haveWon(char player){
            //check for row
            for(int row=0;row<3;row++){
                if(board[row][0]==player&&board[row][1]==player&&board[row][2]==player){
                    return true;
                }
            }
            //check for col
            for(int col=0; col<3;col++){
                if(board[0][col]==player&&board[1][col]==player&&board[2][col]==player){
                    return true;
                }
            }
            //diagonal
            if(board[0][0]==player&&board[1][1]==player&&board[2][2]==player){
                return true;
            }
            if(board[0][2]==player&&board[1][1]==player&&board[2][0]==player){
                return true;
            }
            return false;
        }

        private static boolean isDraw(){
            for(int row=0;row<3;row++){
                for(int col=0;col<3;col++){
                    if(board[row][col]==' '){
                        return false;
                    }
                }
            }
            return true;
        }

        private static void resetBoard(){
            initializeBoard();
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    System.out.println("reset");
                    buttons[row][col].setText("");
                }
            }
            player='X';
        }

        private static boolean isGameOver(){
            return haveWon('X') || haveWon('O') || isDraw();
        }
}