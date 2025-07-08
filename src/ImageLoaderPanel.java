import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.io.IOException;
public class ImageLoaderPanel extends JPanel {
	private List<Image> images = new ArrayList<>();
	
	public ImageLoaderPanel(String directoryPath) {
		loadImagesFromDirectory(directoryPath);
	}
	
	private void loadImagesFromDirectory(String directoryPath) {
		File dir = new File(directoryPath);
		
		if (dir.isDirectory()) {
			File[] files = dir.listFiles((d, name) -> name.endsWith(".png") || name.endsWith(".jpg"));
			if (files != null) {
				for (File file : files) {
					try {
						images.add(ImageIO.read(file));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = 10;
		for (Image img : images) {
			g.drawImage(img, x, 10, this);
			x += img.getWidth(this) + 10;
		}
	}
	
	
}
