import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundPanel extends JPanel{
	private Image backgroundImage;
	
	public BackgroundPanel(String imagePath) {
		File file = new File(imagePath);
		if (file.exists()) {
			try {
				backgroundImage = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Failed to load background image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Background image file does not exist: " + file.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	
	

}
