import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import javax.swing.Timer;


public class GamePanel extends BackgroundPanel {
    private int level;
    private int score = 0;
    private int triesLeft;
    private List<JButton> cards = new ArrayList<>();
    private JButton selectedCard = null;
    private ImageIcon selectedIcon = null;
    private JFrame mainFrame;
    private ImageIcon backIcon;
    private JLabel statusLabel;
    private JMenuBar menuBar;
    // added these two variables below 
    private JDialog statusDialog;
    private JLabel statusContent;
    
    public GamePanel(JFrame mainFrame, int level) {
        super("C:\\Users\\ALPHAN\\OneDrive\\Masaüstü\\Java Project Assets\\background.jpg");
        this.mainFrame = mainFrame;
        this.level = level;
        setLayout(new GridLayout(4, 4));
        initializeTries();
        setupStatusLabel();
        initializeMenuBar();
        loadCards();
        shuffleCards();
    }
    


    private void setupStatusDialog() {
        if (statusDialog == null) {
            statusDialog = new JDialog(mainFrame, "Game Status", false); 
            statusDialog.setLayout(new BorderLayout());
            
            statusContent = new JLabel("Level " + level + " - Tries left: " + triesLeft, SwingConstants.CENTER);
            statusContent.setFont(new Font("Arial", Font.BOLD, 16));
            statusContent.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
            statusDialog.add(statusContent, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> statusDialog.dispose());
            statusDialog.add(closeButton, BorderLayout.SOUTH);

            statusDialog.pack();
            statusDialog.setLocationRelativeTo(mainFrame);
        }
        statusDialog.setVisible(true);
    }

    private void updateStatusDialog() {
        if (statusDialog != null && statusDialog.isVisible()) {
            statusContent.setText("Level " + level + " - Tries left: " + triesLeft);
        }
    }

    
    private void initializeMenuBar() {
        menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem statusItem = new JMenuItem("Show Status");
        JMenuItem restartItem = new JMenuItem("Restart");
        JMenuItem highScoresItem = new JMenuItem("High Scores");

        statusItem.addActionListener(e -> setupStatusDialog());
        restartItem.addActionListener(e -> restartGame());
        //highScoresItem.addActionListener(e -> showHighScores());
        highScoresItem.addActionListener(e -> displayHighScores());
        
        gameMenu.add(statusItem);
        gameMenu.add(restartItem);
        gameMenu.add(highScoresItem);

        menuBar.add(gameMenu);
        menuBar.add(new JMenu("About"));
        menuBar.add(new JMenu("Exit"));

        mainFrame.setJMenuBar(menuBar);
    }
    
    private void restartGame() {
    	GamePanel newGamePanel = new GamePanel(mainFrame, level);
    	mainFrame.setContentPane(newGamePanel);
    	mainFrame.revalidate();
    	mainFrame.repaint();
    }

    private void saveHighScores(List<Integer> scores) {
        Path path = Paths.get("C:\\Users\\ALPHAN\\OneDrive\\Masaüstü\\Java Project Assets\\highscores.txt");
    	try {
            Files.write(path, scores.stream().map(String::valueOf).collect(Collectors.toList()), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void updateScoreDisplay() {
    	statusLabel.setText("Score: "+ score);
    }
    
    private void updateScore(boolean isMatch) {
    	int scoreIncrement = 0;
    	int scoreDecrement = 0;
    	
    	switch(level) {
    	case 1:
    		scoreIncrement = 5;
    		scoreDecrement = 1;
    		break;
    	case 2:
    		scoreIncrement = 4;
    		scoreDecrement = 2;
    		break;
    	case 3: 
    		scoreIncrement = 3;
    		scoreDecrement = 3;
    		break;
    	}
    	
    	if (isMatch) {
    		score += scoreIncrement;
    	} else {
    		score -= scoreDecrement;
    	}
    	
    	updateScoreDisplay();
    }
    
    private void updateHighScores(int newScore) {
        List<Integer> scores = loadHighScores();
        scores.add(newScore);
        Collections.sort(scores, Collections.reverseOrder());
        scores.remove(scores.size() - 1);
        saveHighScores(scores);
    }
    
    private void displayHighScores() {
        List<Integer> scores = loadHighScores();
        JOptionPane.showMessageDialog(mainFrame, "High Scores:\n" + scores.stream().map(Object::toString).collect(Collectors.joining("\n")));
    }

    private void initializeTries() {
        switch (level) {
            case 1: triesLeft = 18; break;
            case 2: triesLeft = 15; break;
            case 3: triesLeft = 12; break;
            default: triesLeft = 18;
        }
    }

    private void setupStatusLabel() {
        statusLabel = new JLabel("Level " + level + " - Tries left: " + triesLeft, SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        // added below lines
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.LIGHT_GRAY);
        statusLabel.setForeground(Color.BLACK);
        // added above lines
        add(statusLabel, BorderLayout.NORTH);
    }

    private void handleCardAction(ActionEvent e) {
        JButton cardButton = (JButton) e.getSource();
        ImageIcon cardIcon = (ImageIcon) cardButton.getPressedIcon();

        if (selectedCard == null) {
            selectedCard = cardButton;
            selectedIcon = cardIcon;
            cardButton.setIcon(cardIcon);
            cardButton.setDisabledIcon(cardIcon);
            cardButton.setEnabled(false);
        } else {
            if (selectedIcon.getDescription().equals(cardIcon.getDescription())) {
                updateScore(true);
                cardButton.setIcon(cardIcon);
                cardButton.setDisabledIcon(cardIcon);
                cardButton.setEnabled(false);
                selectedCard.setEnabled(false);
                selectedCard = null;
                checkWinCondition();
            } else {
                updateScore(false);
                cardButton.setIcon(cardIcon);
                Timer timer = new Timer(500, ev -> {
                    if (cardButton != null && selectedCard != null) {
                        cardButton.setIcon(backIcon);
                        selectedCard.setIcon(backIcon);
                        cardButton.setEnabled(true);
                        selectedCard.setEnabled(true);
                    }
                    selectedCard = null;
                    decrementTries();
                    if (level == 3) {
                        Thread shuffleThread = new Thread(() -> {
                            try {
                                Thread.sleep(500);
                                shuffleCards();
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                                ie.printStackTrace();
                            }
                        });
                        shuffleThread.start();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    private void shuffleCards() {
        Collections.shuffle(cards);
        removeAll(); 

        for (JButton card : cards) {
            add(card);
        }
        validate();
        repaint();
    }
    
    private String getBasePathForLevel(int level) {
        switch (level) {
            case 1:
                return "C:\\Users\\ALPHAN\\OneDrive\\Masaüstü\\Java Project Assets\\Level1-InternetAssets";
            case 2:
                return "C:\\Users\\ALPHAN\\OneDrive\\Masaüstü\\Java Project Assets\\Level2-CyberSecurityAssets";
            case 3:
                return "C:\\Users\\ALPHAN\\OneDrive\\Masaüstü\\Java Project Assets\\Level3-GamingComputerAssets";
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    private List<Integer> loadHighScores() {
        Path path = Paths.get("C:\\Users\\ALPHAN\\OneDrive\\Masaüstü\\Java Project Assets\\highscores.txt");
        if (!Files.exists(path)) {
            System.out.println("High scores file not found, creating one.");
            try {
                Files.write(path, Arrays.asList("0", "0", "0", "0", "0"), StandardOpenOption.CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            List<Integer> scores = Files.lines(path).map(Integer::parseInt).collect(Collectors.toList());
            System.out.println("Loaded high scores: " + scores);
            return scores;
        } catch (IOException e) {
            e.printStackTrace();
            return Arrays.asList(0, 0, 0, 0, 0);
        }
    }

    private void loadCards() {
        String basePath = getBasePathForLevel(level);
        File folder = new File(basePath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));
        List<ImageIcon> cardIcons = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (!file.getName().equals("no_image.png")) {
                    try {
                        ImageIcon icon = new ImageIcon(ImageIO.read(file));
                        icon.setDescription(file.getName());
                        cardIcons.add(icon);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error loading image from file: " + file.getName());
                    }
                } else {
                    try {
                        backIcon = new ImageIcon(ImageIO.read(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (cardIcons.size() > 8) {
                cardIcons = cardIcons.subList(0, 8);
            }

            List<ImageIcon> pairedIcons = new ArrayList<>(cardIcons);
            pairedIcons.addAll(cardIcons);
            Collections.shuffle(pairedIcons);

            this.removeAll();
            cards.clear();
            for (ImageIcon icon : pairedIcons) {
                JButton cardButton = new JButton(backIcon);
                cardButton.setPressedIcon(icon);
                cardButton.addActionListener(this::handleCardAction);
                cards.add(cardButton);
                this.add(cardButton);
            }
        } else {
            System.out.println("No files found in the specified directory: " + basePath);
        }

        revalidate();
        repaint();
    }
    
    private void decrementTries() {
        triesLeft--;
        //statusLabel.setText("Level " + level + " - Tries left: " + triesLeft);
        updateStatusDialog();
        if (triesLeft <= 0) {
            gameOver();
        }
    }

    private void checkWinCondition() {
        if (cards.stream().allMatch(c -> !c.isEnabled())) {
            if (level == 3) {
                displayMessage("Congrats, you won the entire game!!");
            } else {
                displayMessage("Congrats, you won!! Moving to next level.");
                advanceToNextLevel();
            }
        }
    }

    private void advanceToNextLevel() {
        GamePanel nextLevelPanel = new GamePanel(mainFrame, level + 1);
        mainFrame.setContentPane(nextLevelPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        updateStatusDialog();
    }

    private void gameOver() {
    	updateHighScores(score);
        displayMessage("You lost, try again");
        GamePanel newGamePanel = new GamePanel(mainFrame, 1);
        mainFrame.setContentPane(newGamePanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(mainFrame, message);
    }
}