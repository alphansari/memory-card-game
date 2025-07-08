import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MenuPanel extends BackgroundPanel{
	private JButton startGameButton, selectLevelButton, instructionsButton, exitButton;
	private JFrame mainFrame;
	private JLabel titleLabel;
	public MenuPanel(JFrame mainFrame) {
		super("C:\\\\Users\\\\ALPHAN\\\\OneDrive\\\\Masaüstü\\\\Java Project Assets\\\\background.jpg");
		this.mainFrame = mainFrame;
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new GridBagLayout());
		//JLabel titleLabel = new JLabel("Memory Card Game", SwingConstants.CENTER);
		//titleLabel.setFont(new Font("Monospaced", Font.ITALIC, 48));
		//add(titleLabel);
		initTitle();
		initButtons();
	}
	
	private void initTitle() {
		titleLabel = new JLabel("Memory Card Game", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(20, 0, 20, 0);
		add(titleLabel, gbc);
	}

    private void initButtons() {
        Font buttonFont = new Font("SansSerif", Font.PLAIN, 18);

        startGameButton = new JButton("Start Game");
        startGameButton.setFont(buttonFont);

        selectLevelButton = new JButton("Select Level");
        selectLevelButton.setFont(buttonFont);

        instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(buttonFont);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);
        add(startGameButton, gbc);
        add(selectLevelButton, gbc);
        add(instructionsButton, gbc);
        add(exitButton, gbc);

        startGameButton.addActionListener(e -> startGame());
        selectLevelButton.addActionListener(e -> selectLevel());
        instructionsButton.addActionListener(e -> showInstructions());
        exitButton.addActionListener(e -> System.exit(0));
    }
    
	private void startGame() {
		GamePanel gamePanel = new GamePanel(mainFrame, 1);
		mainFrame.setContentPane(gamePanel);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	private void selectLevel() {
		LevelSelectPanel levelSelectPanel = new LevelSelectPanel(mainFrame);
		mainFrame.setContentPane(levelSelectPanel);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	private void showInstructions() {
		//JOptionPane.showMessageDialog(mainFrame,"Instructions:\nThere are 3 levels in the game. It gets gradually harder!\nMatch all pairs of cards to win!", "\nInstructions", JOptionPane.INFORMATION_MESSAGE);
		JDialog instructionsDialog = new JDialog(mainFrame, "Instructions", true);
		instructionsDialog.setLayout(new BorderLayout());
		
		JLabel instructionLabel = new JLabel("Instructions: \n There are 3 levels in the game. It gets gradually harder! Match all pairs of cards to win.");
		
		instructionLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionLabel.setHorizontalTextPosition(JLabel.CENTER);
		instructionLabel.setVerticalAlignment(JLabel.CENTER);
		instructionLabel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		
		
		instructionsDialog.add(instructionLabel, BorderLayout.CENTER);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(e -> instructionsDialog.dispose());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		
		instructionsDialog.add(buttonPanel, BorderLayout.SOUTH);
		
		instructionsDialog.setSize(700, 200);
		instructionsDialog.setLocationRelativeTo(mainFrame);
		instructionsDialog.setVisible(true);
		
	}
}
