package cpoo.noise.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * 
 * @author Rafał Radziejewski
 *
 */
@SuppressWarnings("serial")
public class StatusBar extends JPanel {

	private JLabel message = new JLabel(" Postęp  ");
	private JProgressBar progress = new JProgressBar(0, 100);
	private MainWindow mainWindow;
	
	StatusBar (MainWindow mainWindow) {
		super(new BorderLayout());
		this.mainWindow = mainWindow;
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 30);
		add(message, BorderLayout.WEST);
		progress.setMaximumSize(new Dimension(1, 1));
		progress.setValue(0);
		progress.setStringPainted(true);
		add(progress, BorderLayout.CENTER);
	}
	
	public void setMessage(String m)
	{
		message.setText(m);
		this.revalidate();
	}
	
	public void updateProgress (int newProgress)
	{
		progress.setValue(newProgress);
		if (newProgress == 100)
		{
			progress.setValue(0);
			JOptionPane.showMessageDialog(mainWindow, "Generacja obrazów została zakończona sukcesem.");
		}
		progress.update(progress.getGraphics());
		this.revalidate();
	}
}
