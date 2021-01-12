import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Initial extends JFrame implements ActionListener {
    static JTextField input;
    static JFrame frame;
    static JButton button;
    static JLabel heading = new JLabel("<html><font size=30 color=blue>Tic Tac Toe<br></font></html>"),
            instructions = new JLabel("<html><font size=5>First person to fill an entire row, column, or either <br>diagonal with their chosen character wins!<br>Don't forget to press enter after each turn!<br></font></html>"),
            prompt = new JLabel("<html><font size=3>Enter the size of the grid you would like to play on: <br></font></html>");

    public static void main(String[] args)
    {
        frame = new JFrame("Welcome to tic-tac-toe");
        button = new JButton("begin!");

        Initial obj = new Initial();
        button.addActionListener(obj);

        input = new JTextField(5);

        JPanel p = new JPanel();
        p.add(heading);
        p.add(instructions);
        p.add(prompt);
        p.add(input);
        p.add(button);

        frame.add(p);
        frame.setSize(500, 300);
        frame.show();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("begin!")) {
            int n = 3;
            try {
                n = Integer.parseInt(input.getText());
            }
            catch (Exception exc) {
                JFrame f = new JFrame();
                JOptionPane.showMessageDialog(f,"Error: please enter a valid number greater than 0");
                return;
            }
            if (n<1) {
                JFrame f = new JFrame();
                JOptionPane.showMessageDialog(f,"Error: please enter a valid number greater than 0");
                return;
            }
            new Tictactoe(n);
            input.setText("");
        }
    }
}