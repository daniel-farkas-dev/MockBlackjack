import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonAreaLayout;

public class MainFrame {
    static int FRAME_WIDTH = 940, FRAME_HEIGHT = 580;
    JFrame frame;
    Game game;
    ImprovedJTextField inputField;
    JPanel playerHand, dealerHand, southPanel;

    private enum SouthPanelState {
        BETTING, PLAYING
    }

    SouthPanelState southPanelState = SouthPanelState.BETTING;

    public MainFrame(Game game) {
        frame = new JFrame("BlackJack");
        this.game = game;
    }

    public void createFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        Color darkGreen = new Color(47, 104, 45);
        frame.getContentPane().setBackground(darkGreen);
        frame.setName("BlackJack");
        frame.setLayout(new BorderLayout(20,15));
        frame.setResizable(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(darkGreen);
        JLabel titleLabel = new JLabel("BlackJack");
        titleLabel.setForeground(Color.yellow);
        titleLabel.setFont(new Font("SanSerif", Font.BOLD, 75));
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);


        JPanel mainAreaPanel = new JPanel();
        mainAreaPanel.setBackground(Color.green);
        mainAreaPanel.setLayout(new BoxLayout(mainAreaPanel, BoxLayout.Y_AXIS));

        dealerHand = new JPanel();
        dealerHand.setBackground(darkGreen);
        setDealerTotal("0");
        mainAreaPanel.add(dealerHand, BorderLayout.NORTH);

        playerHand = new JPanel();
        playerHand.setBackground(darkGreen);
        setPlayerTotal("0");
        mainAreaPanel.add(playerHand, BorderLayout.SOUTH);

        frame.add(mainAreaPanel, BorderLayout.CENTER);

        southPanel = new JPanel();
        bettingPanelLoad();

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(darkGreen);
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setBackground(darkGreen);
        emptyPanel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        frame.add(emptyPanel, BorderLayout.WEST);
        frame.add(emptyPanel1, BorderLayout.EAST);

        frame.setVisible(true);
    }

    public void addPlayerCard(Card card) {
        frame.setVisible(false);
        String filepath = "com/ConsoleBlackJack/images/" + card.getSuit().toString()+"/"+card.getRank().toString()+".png";
        JLabel cardLabel = new JLabel(new ImageIcon(filepath));
        playerHand.add(cardLabel);
        frame.setVisible(true);
    }
    public void addDealerCard(Card card) {
        frame.setVisible(false);
        if (card.isFaceUp) {
            String filepath = "com/ConsoleBlackJack/images/" + card.getSuit().toString()+"/"+card.getRank().toString()+".png";
            JLabel cardLabel = new JLabel(new ImageIcon(filepath));
            dealerHand.add(cardLabel);
        } else {
            String filepath = "com/ConsoleBlackJack/images/CardFaceDown.png";
            JLabel cardLabel = new JLabel(new ImageIcon(filepath));
            dealerHand.add(cardLabel);
        }
        frame.setVisible(true);
    }
    public void clearHands() {
        frame.setVisible(false);
        playerHand.removeAll();
        dealerHand.removeAll();
        frame.setVisible(true);
    }
    public void switchSouthPanelState() {
        frame.setVisible(false);
        if (southPanelState == SouthPanelState.BETTING) {
            southPanelState = SouthPanelState.PLAYING;
            southPanel.removeAll();
            playingPanelLoad();                                                     
        } else {
            southPanelState = SouthPanelState.BETTING;
            southPanel.removeAll();
            bettingPanelLoad();
        }
        frame.setVisible(true);
    }

    private void playingPanelLoad() {
        southPanel.setBackground(Color.DARK_GRAY);
        southPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0),"User Actions:"));
        ((TitledBorder) southPanel.getBorder()).setTitleFont(new Font("SanSerif", Font.BOLD, 20));
        ((TitledBorder) southPanel.getBorder()).setTitleColor(Color.white);
        southPanel.setLayout(new ButtonAreaLayout(true, 20));
        
        JButton blankLeft = new JButton();
        blankLeft.setOpaque(true);
        blankLeft.setBorderPainted(false);
        blankLeft.setFocusPainted(false);
        blankLeft.setBackground(Color.gray);

        JButton hitButton = new JButton("HIT");
        hitButton.setOpaque(true);
        hitButton.setBorderPainted(false);
        hitButton.setFocusPainted(false);
        hitButton.setBackground(Color.green);
        hitButton.addActionListener(e -> {
            synchronized (game.currentRound.o) {
                System.out.println("Bruh hit");
                game.currentRound.selectedAction = Round.Actions.HIT;
                game.currentRound.o.notify();
            }
        });

        JButton standButton = new JButton("STAND");
        standButton.setOpaque(true);
        standButton.setBorderPainted(false);
        standButton.setFocusPainted(false);
        standButton.setBackground(Color.red);
        standButton.addActionListener(e -> {
            synchronized (game.currentRound.o) {
                System.out.println("Bruh stand");
                game.currentRound.selectedAction = Round.Actions.STAND;
                game.currentRound.o.notify();
            }
        });

        JButton doubleButton = new JButton("DOUBLE-DOWN");
        doubleButton.setOpaque(true);
        doubleButton.setBorderPainted(false);
        doubleButton.setFocusPainted(false);
        doubleButton.setBackground(Color.yellow);
        doubleButton.addActionListener(e -> {
            synchronized (game.currentRound.o) {
                System.out.println("Bruh double");
                game.currentRound.selectedAction = Round.Actions.DOUBLE_DOWN;
                game.currentRound.o.notify();
            }
        });

        JButton splitButton = new JButton("SPLIT");
        splitButton.setOpaque(true);
        splitButton.setBorderPainted(false);
        splitButton.setFocusPainted(false);
        splitButton.setBackground(Color.blue);
        splitButton.addActionListener(e -> {
            synchronized (game.currentRound.o) {
                System.out.println("Bruh split");
                game.currentRound.selectedAction = Round.Actions.SPLIT;
                game.currentRound.o.notify();
            }
        });

        JButton insuranceButton = new JButton("INSURANCE");
        insuranceButton.setOpaque(true);
        insuranceButton.setBorderPainted(false);
        insuranceButton.setFocusPainted(false);
        insuranceButton.setBackground(Color.orange);
        insuranceButton.addActionListener(e -> {
            synchronized (game.currentRound.o) {
                System.out.println("Bruh insurance");
                game.currentRound.selectedAction = Round.Actions.INSURANCE;
                game.currentRound.o.notify();
            }
        });

        JButton blankRight = new JButton();
        blankRight.setOpaque(true);
        blankRight.setBorderPainted(false);
        blankRight.setFocusPainted(false);
        blankRight.setBackground(Color.gray);

        southPanel.add(blankLeft);
        southPanel.add(hitButton);
        southPanel.add(standButton);
        southPanel.add(doubleButton);
        southPanel.add(splitButton);
        southPanel.add(insuranceButton);
        southPanel.add(blankRight);
        frame.add(southPanel, BorderLayout.SOUTH);
    }
    private void bettingPanelLoad() {
        southPanel.setBackground(Color.DARK_GRAY);
        southPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0),"Place a bet:"));
        ((TitledBorder) southPanel.getBorder()).setTitleFont(new Font("SanSerif", Font.BOLD, 20));
        ((TitledBorder) southPanel.getBorder()).setTitleColor(Color.white);
        southPanel.setLayout(new GridBagLayout());

        JLabel SouthWest = new JLabel(new ImageIcon("com/ConsoleBlackJack/images/empty_card.png"));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        southPanel.add(SouthWest, c);
        
        inputField = new ImprovedJTextField("Enter bet here...");
        inputField.setColumns(1);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 1;
        c.gridy = 0;
        southPanel.add(inputField, c);

        JButton button = new JButton("PLACE WAGER!");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        button.addActionListener(e -> {
            synchronized (game.currentRound.o) {
                System.out.println("WINDOWS ACTIVATED");
                game.currentRound.o.notify();
            }
        });
        southPanel.add(button, c);

        JLabel SouthEast = new JLabel(new ImageIcon("com/ConsoleBlackJack/images/empty_card.png"));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        southPanel.add(SouthEast, c);

        frame.add(southPanel, BorderLayout.SOUTH);
    }
    public void setDealerTotal(String t) {
        dealerHand.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(144,238,144)),
                "Total: " + t , TitledBorder.RIGHT, TitledBorder.BOTTOM, new Font("SanSerif", Font.BOLD, 20), Color.white
                ), /*      ^^^ this is the main part :) */
                "Dealer's Hand", TitledBorder.LEFT, TitledBorder.TOP, new Font("SanSerif", Font.BOLD, 20), Color.red
        ));
    }
    public void setPlayerTotal(String t) {
        playerHand.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(144,238,144)),
                "Total: " + t , TitledBorder.RIGHT, TitledBorder.BOTTOM, new Font("SanSerif", Font.BOLD, 20), Color.white
                ), /*      ^^^ this is the main part :) */
                "Player's Hand", TitledBorder.LEFT, TitledBorder.TOP, new Font("SanSerif", Font.BOLD, 20), Color.blue
        ));
    }
}
