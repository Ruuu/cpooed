package cpoo.noise.view;

import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 
 * @author Rafał Radziejewski
 * 
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private static final int WINDOWS_BAR_HEIGHT = 35;
	private static final String title = "NoisePicture v1.0";

	private ContentPane content;
	private MainMenu menu;

	MainWindow() {
		super(title);
		adjustSize();

		content = new ContentPane(this);
		menu = new MainMenu(this);

		setContentPane(content);
		setJMenuBar(menu);
		setVisible(true);
	}

	private void adjustSize() {
		super.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height
						- WINDOWS_BAR_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public ContentPane getContent() {
		return content;
	}

}
