import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Tic tac toe game
 *
 * @author Helen
 */

public class Tictactoe extends JFrame{
    public static final int CELL = 50;
    public static final Font FONT = new Font("Monospaced", Font.BOLD, 20);
    public int grid_size = 3;
    private JTextField[][] board;
    private char[] players = {'o','x'};
    private char curPlayer;

    private class InputListener implements ActionListener {
        private JTextField cur;
        private int row, col;
        public InputListener(JTextField field, int r, int c)
        {
            cur = field;
            row = r;
            col = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cur.setEditable(false);
            curPlayer = curPlayer==players[0] ? players[1] : players[0];
            checkFinished();
        }

        private void checkFinished() {
            boolean same = true;

            //column
            for (int r=1;r<grid_size;r++) {
                if (!board[r-1][col].getText().equals(board[r][col].getText())) {
                    same = false;
                    break;
                }
            }
            if (same) {
                isFinished(board[0][col].getText());
                for (int r=0;r<grid_size;r++)
                    board[r][col].setBackground(Color.green);
                return;
            }

            //row
            same = true;
            for (int c=1;c<grid_size;c++) {
                if (!board[row][c-1].getText().equals(board[row][c].getText())) {
                    same = false;
                    break;
                }
            }
            if (same) {
                isFinished(board[row][0].getText());
                for (int c=0;c<grid_size;c++)
                    board[row][c].setBackground(Color.green);
                return;
            }

            //diagonal if applicable
            if (row==col || row+col==grid_size-1) {
                same = true;
                if (row==col) {
                    for (int i=1;i<grid_size;i++) {
                        if (!board[i - 1][i - 1].getText().equals(board[i][i].getText())) {
                            same = false;
                            break;
                        }
                    }
                }
                else {
                    for (int i=1;i<grid_size;i++) {
                        if (!board[i-1][grid_size-i].getText().equals(board[i][grid_size-i-1].getText())) {
                            same = false;
                            break;
                        }
                    }
                }
                if (same) {
                    isFinished(board[row][col].getText());
                    if (row==col)
                        for (int i=0;i<grid_size;i++)
                            board[i][i].setBackground(Color.green);
                    else
                        for (int i=0;i<grid_size;i++)
                            board[i][grid_size-i-1].setBackground(Color.green);
                    return;
                }
            }
        }

        private void isFinished(String winner) {
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, winner+" wins!");
            return;
        }
    }

    public Tictactoe(int n) {
        grid_size = n;
        board = new JTextField[grid_size][grid_size];
        curPlayer = players[0];

        Container cp = getContentPane();
        cp.setLayout(new GridLayout(grid_size, grid_size));

        for (int r=0;r<grid_size;r++) {
            for (int c=0;c<grid_size;c++) {
                board[r][c] = new JTextField();
                cp.add(board[r][c]);
                board[r][c].setText("");
                board[r][c].setEditable(true);
                board[r][c].addActionListener(new InputListener(board[r][c], r, c));
                board[r][c].setHorizontalAlignment(JTextField.CENTER);
                board[r][c].setFont(FONT);
        }

        cp.setPreferredSize(new Dimension(CELL*grid_size, CELL*grid_size));
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic-tac-toe");
        setVisible(true);
        }
    }

//    public static void main(String[] args)
//    {
//        Tictactoe game = new Tictactoe();
//    }
}
