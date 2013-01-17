package view.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private Image  image;
	private Dimension size;
	
	public ImagePanel(String imagePath) 
	{
		image = null;
		try 
		{
			System.out.println(imagePath);
			File input = new File(imagePath);
			image = ImageIO.read(input);
		} 
		catch (IOException ie) 
		{
			System.out.println("Error:" + ie.getMessage());
		}
		
		size = new Dimension();
		size.width = image.getWidth(null);
        size.height = image.getHeight(null);
        setPreferredSize(size);
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}