import javax.swing.*;
import java.awt.*;
public class LevelSelectPanel extends JPanel {
	private JFrame mainFrame;
	
	public LevelSelectPanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(new GridLayout(3, 1));
		initLevelButtons();
	}
	
	private void initLevelButtons() {
		JButton levelOneButton = new JButton("Level 1 - Easy");
		JButton levelTwoButton = new JButton("Level 2 - Medium");
		JButton levelThreeButton = new JButton("Level 3 - Hard");
		
		levelOneButton.addActionListener(e -> startLevel(1));
		levelTwoButton.addActionListener(e -> startLevel(2));
		levelThreeButton.addActionListener(e -> startLevel(3));
		
		add(levelOneButton);
		add(levelTwoButton);
		add(levelThreeButton);
	}
	
	private void startLevel(int level) {
		GamePanel gamePanel = new GamePanel(mainFrame, level);
		mainFrame.setContentPane(gamePanel);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
}
