import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Memory Card Game");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MenuPanel menuPanel = new MenuPanel(mainFrame);
            mainFrame.setContentPane(menuPanel);

            mainFrame.pack();
            mainFrame.setSize(900, 700);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
        });
    }
}

