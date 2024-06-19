import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JEditorPane;

public class FrameTest {
    static int FRAME_WIDTH = 1000, FRAME_HEIGHT = 800;
    public static void createFrame() {
        JFrame frame = new JFrame("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        // JTextField textField = new JTextField(50);
        // frame.add(textField);
        // frame.setLocationRelativeTo(null);
        // frame.setAlwaysOnTop(true);
        // frame.setResizable(false);
        Color darkGreen = new Color(70, 133, 95);
        frame.getContentPane().setBackground(darkGreen);
        frame.setName("BlackJack");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        // frame.setUndecorated(false);
        // JLabel label = new JLabel("BlackJack");
        // // label.setBackground(Color.WHITE);
        // label.setHorizontalAlignment(JLabel.CENTER);
        // frame.add(label, BorderLayout.CENTER);
        String titleText = "<html style='background-color: rgb(70,133,95)'><h1 style='color: yellow; text-align: center; font-size: 50px; font-family: verdana;'>BlackJack</h1></html>";
        JEditorPane planeEditor = new JEditorPane("text/html", titleText);
        planeEditor.setEditable(false);
        planeEditor.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -1));
        frame.add(planeEditor, BorderLayout.NORTH);

        JPanel dealerHand = new JPanel();
        dealerHand.setBackground(darkGreen);
        dealerHand.setBorder(BorderFactory.createTitledBorder("Dealer's Hand"));

        frame.add(dealerHand);

        JPanel panel = new JPanel();
        panel.setBackground(darkGreen);

        ImageIcon discard = new ImageIcon("com/ConsoleBlackJack/images/empty_card.png");
        JLabel discardLabel = new JLabel(discard); panel.add(discardLabel, BorderLayout.EAST);

        String middleHTMLText = "<html><body style='background-color: rgb(70,133,95)'><p style='color: white; text-align: center; font-size: 20px; font-family: arial;'>Dealer Must Draw to 16 and stand on all 17s</p></body></html>";
        JEditorPane ruleText = new JEditorPane("text/html", middleHTMLText);
        ruleText.setEditable(false); ruleText.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -1));
        panel.add(ruleText, BorderLayout.CENTER);  

        ImageIcon pile = new ImageIcon("com/ConsoleBlackJack/images/empty_card.png");
        JLabel pileLabel = new JLabel(pile); panel.add(pileLabel, BorderLayout.WEST);
        panel.setBorder(BorderFactory.createEmptyBorder(150, 0, 150, 0));
        
        frame.add(panel);

        JPanel playerHand = new JPanel();
        playerHand.setBackground(darkGreen);
        playerHand.setBorder(BorderFactory.createTitledBorder("Player's Hand"));
        playerHand.setSize(100, 200);
        frame.add(playerHand, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(darkGreen);

        JButton button = new JButton("Start!");
        button.setBackground(Color.gray);

        buttonPanel.add(button, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);


    
    }
}
