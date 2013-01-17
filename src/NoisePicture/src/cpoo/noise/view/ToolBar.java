package cpoo.noise.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * Pasek narzędzi.
 * 
 * @author Rafał Radziejewski
 * 
 */
@SuppressWarnings("serial")
public class ToolBar extends JPanel {

	private JToolBar bar = new JToolBar();

	private JButton load;
	private JButton save;
	private JButton clear;
	private JButton exit;
	private JButton runGauss;
	private JButton runImpulse;
	private JButton close;

	ToolBar() {
		super();
		loadIcons();
		addComponents();
		setTooltips();
		addListeners();
		setProperties();
		add(bar, BorderLayout.WEST);
	}

	private void loadIcons() {
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();

			InputStream is = classLoader.getResourceAsStream("load.png");
			BufferedImage bi = ImageIO.read(is);
			load = new JButton(new ImageIcon(bi));

			is = classLoader.getResourceAsStream("save.png");
			bi = ImageIO.read(is);
			save = new JButton(new ImageIcon(bi));

			is = classLoader.getResourceAsStream("clear.png");
			bi = ImageIO.read(is);
			clear = new JButton(new ImageIcon(bi));

			is = classLoader.getResourceAsStream("exit.png");
			bi = ImageIO.read(is);
			exit = new JButton(new ImageIcon(bi));

			is = classLoader.getResourceAsStream("runGauss.png");
			bi = ImageIO.read(is);
			runGauss = new JButton(new ImageIcon(bi));

			is = classLoader.getResourceAsStream("runImpulse.png");
			bi = ImageIO.read(is);
			runImpulse = new JButton(new ImageIcon(bi));

			is = classLoader.getResourceAsStream("close.png");
			bi = ImageIO.read(is);
			close = new JButton(new ImageIcon(bi));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setProperties() {
		bar.setFloatable(false);
		bar.setRollover(false);
		Dimension d = bar.getPreferredSize();
		d.width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20);
		d.height = 60;
		bar.setPreferredSize(d);
		bar.setMaximumSize(d);
		bar.setMinimumSize(d);

		// FIXME setButtonsEnabled(false);
		setButtonsEnabled(true);
	}

	private void addComponents() {
		bar.add(load);
		bar.add(save);
		bar.addSeparator();
		bar.add(close);
		bar.add(clear);
		bar.addSeparator();
		bar.add(runGauss);
		bar.add(runImpulse);
		bar.add(Box.createHorizontalGlue());
		bar.add(exit);
	}

	private void setTooltips() {
		load.setToolTipText("Wczytaj plik");
		save.setToolTipText("Zapisz plik");
		clear.setToolTipText("Zamknij wsztstkie zakładki");
		runGauss.setToolTipText("Uruchom zaszumianie szumem Gaussa");
		runImpulse.setToolTipText("Uruchom zaszumianie szumem impulsowym");

		exit.setToolTipText("Wyjdź z programu");
		close.setToolTipText("Zamknij bieżący plik");
	}

	/**
	 * Dodaje wszystkie listenery dla paska narzędzi - korzystają one z metod
	 * menu, żeby nie duplikować kodu jako że robią one dokłądnie to samo
	 */
	private void addListeners() {

		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.handleClear(e);
			}
		});

		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.handleLoad(e);
			}
		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.handleSave(e);
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.handleExit(e);
			}
		});

		runGauss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.handleRunGauss();
			}
		});

		runImpulse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.handleRunImpulse();
			}
		});

		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.handleFileClose();
			}
		});
	}

	public JToolBar getBar() {
		return bar;
	}

	public JButton getLoad() {
		return load;
	}

	public JButton getSave() {
		return save;
	}

	public JButton getClear() {
		return clear;
	}

	public JButton getExit() {
		return exit;
	}

	public JButton getRunGauss() {
		return runGauss;
	}

	public JButton getRunImpulse() {
		return runGauss;
	}

	public JButton getClose() {
		return close;
	}

	public void setButtonsEnabled(boolean b) {
		runGauss.setEnabled(b);
		runImpulse.setEnabled(b);
		close.setEnabled(b);
		clear.setEnabled(b);
	}
}
