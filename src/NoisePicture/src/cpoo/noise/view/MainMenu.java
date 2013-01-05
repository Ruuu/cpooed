package cpoo.noise.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import cpoo.noise.controller.Controller;
import cpoo.noise.view.dialogs.GaussDialog;
import cpoo.noise.view.dialogs.ImpulseDialog;


/**
 * 
 * @author Rafał Radziejewski
 * 
 */
@SuppressWarnings("serial")
public class MainMenu extends JMenuBar {

	static MainWindow mainWindow;

	private JMenu file = new JMenu("Plik");
	private JMenuItem saveSequences = new JMenuItem("Zapisz");
	private JMenuItem loadSequences = new JMenuItem("Wczytaj");
	private JMenuItem clearSequences = new JMenuItem("Wyczyść");
	private JMenuItem exit = new JMenuItem("Zakończ");

	private JMenu noise = new JMenu("Zaszumienie");
	private JMenuItem runGauss = new JMenuItem("Szum Gaussa");
	private JMenuItem runImpulse = new JMenuItem("Szum Impulsowy");

	private JMenu help = new JMenu("Pomoc");
	private JMenuItem helpContent = new JMenuItem("Zawartość pomocy");
	private JMenuItem autors = new JMenuItem("Autorzy");
	private JMenuItem about = new JMenuItem("O programie");

	MainMenu(MainWindow mW) {
		super();
		mainWindow = mW;
		populateMenuBar();
		createListeners();
	}

	private void populateMenuBar() {
		file.add(loadSequences);
		file.add(saveSequences);
		file.add(clearSequences);
		file.add(exit);
		add(file);

		noise.add(runGauss);
		noise.add(runImpulse);
		add(noise);

		help.add(helpContent);
		help.add(autors);
		help.add(about);
		add(help);
	}

	/**
	 * Tworzy wsystkie listenery dla Menu. Metody z których korzystają są także
	 * wykorzystywane w listenerach paska narzędziowego
	 */
	private void createListeners() {
		saveSequences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSave(e);
			}
		});

		loadSequences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleLoad(e);
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleExit(e);
			}
		});

		helpContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleHelp(e);
			}
		});
		autors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleAutoors(e);
			}
		});
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleAbout(e);
			}
		});
		clearSequences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleClear(e);
			}
		});
		runGauss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleRunGauss();
			}
		});
		runImpulse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleRunImpulse();
			}
		});
	}

	/**
	 * Szum Gaussa.
	 */
	protected static void handleRunGauss() {

		GaussDialog dialog = new GaussDialog(mainWindow, true);
		if (dialog.getAnswer()) {
			double mean = Double.valueOf(dialog.getMean().getText());
			double variance = Double.valueOf(dialog.getVariance().getText());

			boolean isSerial = Boolean.valueOf(dialog.getSerialGeneration()
					.isSelected());

			BufferedImage input = ((ContentPane) mainWindow.getContentPane())
					.getInput();
			String title = ((ContentPane) mainWindow.getContentPane())
					.getInputTitle();

			if (isSerial) {
				int numberOfPictures = Integer.valueOf(dialog
						.getNumberOfPictures().getText());
				double meanStep = Double
						.valueOf(dialog.getMeanStep().getText());
				double varianceStep = Double.valueOf(dialog.getVarianceStep()
						.getText());

				Controller.generateGaussianNoise(input, title, mean, variance,
						numberOfPictures, meanStep, varianceStep);
			} else {
				Controller.generateGaussianNoise(input, title, mean, variance);
			}
		}
	}

	/**
	 * Szum impulsowy.
	 */
	protected static void handleRunImpulse() {

		ImpulseDialog dialog = new ImpulseDialog(mainWindow, true);
		if (dialog.getAnswer()) {

			int v1 = Integer.valueOf(dialog.getVal1().getText());
			double p1 = Double.valueOf(dialog.getP1().getText());
			int v2 = Integer.valueOf(dialog.getVal2().getText());
			double p2 = Double.valueOf(dialog.getP2().getText());

			boolean isSerial = Boolean.valueOf(dialog.getSerialGeneration()
					.isSelected());

			BufferedImage input = ((ContentPane) mainWindow.getContentPane())
					.getInput();
			String title = ((ContentPane) mainWindow.getContentPane())
					.getInputTitle();

			if (isSerial) {
				int numberOfPictures = Integer.valueOf(dialog
						.getNumberOfPictures().getText());
				int v1Step = Integer.valueOf(dialog.getVal1Step().getText());
				double p1Step = Double.valueOf(dialog.getP1Step().getText());
				int v2Step = Integer.valueOf(dialog.getVal2Step().getText());
				double p2Step = Double.valueOf(dialog.getP2Step().getText());

				Controller.generateImpulseNoise(input, title, p1, v1, p2, v2,
						numberOfPictures, p1Step, v1Step, p2Step, v2Step);
			} else {
				Controller.generateImpulseNoise(input, title, p1, v1, p2, v2);
			}
		}
	}

	/**
	 * Obsługa zamkniecia wszystkich zakładek
	 * @param e
	 */
	static void handleClear(ActionEvent e) {
		((ContentPane) mainWindow.getContentPane()).clearAllItems();
	}

	protected void handleAbout(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	protected void handleAutoors(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	protected void handleHelp(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Obsługa wyjścia z programu.
	 * @param e
	 */
	protected static void handleExit(ActionEvent e) {
		int response = JOptionPane.showConfirmDialog(mainWindow,
				"Czy napewno chcesz zakończyć?", "", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			mainWindow.dispose();
		}
	}

	/**
	 * Obsługa odczytu wskazanej ścieżki, tworzy nową zakładkę
	 * 
	 * @param e
	 */
	protected static void handleLoad(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(mainWindow);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String name = fileChooser.getSelectedFile().getAbsolutePath();
			File file = new File(name);
			try {
				BufferedImage content = ImageIO.read(file);

				((ContentPane) mainWindow.getContentPane()).addExistingItem(
						content, fileChooser.getSelectedFile().getName());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}

	/**
	 * Obsługa zapisu pliku z aktywnej zakładki pod wskazaną ścieżką
	 * 
	 * @param e
	 */
	protected static void handleSave(ActionEvent e) {

		String title = ((ContentPane) mainWindow.getContentPane())
				.getInputTitle();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File(title));
		int returnVal = fileChooser.showSaveDialog(mainWindow);
		String fileName;
		String filePath;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			filePath = fileChooser.getSelectedFile().getAbsolutePath();
			fileName = fileChooser.getSelectedFile().getName();
			File file = new File(filePath);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				BufferedImage content = ((ContentPane) mainWindow
						.getContentPane()).getInput();
				// Zapis w tym samym formacie co obraz zrodlowy
				String ext = title.substring(title.lastIndexOf('.') + 1);
				ImageIO.write(content, ext, fos);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(mainWindow, "Plik '" + fileName
					+ "' został poprawnie zapisany.", "",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	/**
	 * Obsługa zamknięcia pliku (nie przejmuje się czy plik był edytowany w
	 * edytorze czy nie
	 */
	protected static void handleFileClose() {
		((ContentPane) mainWindow.getContentPane()).closeSelectedItem();
	}
}
