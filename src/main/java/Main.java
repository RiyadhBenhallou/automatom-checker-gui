import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Automaton Checker");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel promptLabel = new JLabel("Enter a word:");
        JTextField inputField = new JTextField(20);
        JButton checkButton = new JButton("Check");
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);

        resultLabel.setFont(new Font("Arial", Font.BOLD, 12));
        resultLabel.setPreferredSize(new Dimension(300, 20));

        gbc.gridx = 0; gbc.gridy = 0;
        frame.add(promptLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        frame.add(inputField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(checkButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; 
        frame.add(resultLabel, gbc);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = inputField.getText();
                boolean isAccepted = isAccepted(word);
                if (isAccepted) {
                    resultLabel.setText("The word is accepted by the automaton.");
                    resultLabel.setForeground(Color.GREEN);
                } else {
                    resultLabel.setText("The word is not accepted by the automaton.");
                    resultLabel.setForeground(Color.RED);
                }
            }
        });

        frame.setVisible(true);
    }

    public static boolean isAccepted(String word) {
        int currentState = 1; 

        for (char c : word.toCharArray()) {
            switch (currentState) {
                case 1:
                    if (c == 'a') currentState = 3;
                    else if (c == 'b') currentState = 2;
                    else return false; 
                    break;
                case 2:
                    if (c == 'b') currentState = 2;
                    else if (c == 'a') currentState = 4;
                    else return false; 
                    break;
                case 3:
                    if (c == 'a') currentState = 3;
                    else if (c == 'b') currentState = 4;
                    else return false; 
                    break;
                case 4:
                    if (c == 'a') currentState = 3;
                    else if (c == 'b') currentState = 2;
                    else return false; 
                    break;
                default:
                    return false;
            }
        }

        return currentState == 4;
    }
}
