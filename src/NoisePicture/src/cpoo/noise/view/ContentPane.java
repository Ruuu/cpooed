package cpoo.noise.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * 
 * @author Rafał Radziejewski
 * 
 */
@SuppressWarnings("serial")
public class ContentPane extends JPanel {

	private MainWindow mainWindow;
	// pasek narzędzi
	private ToolBar toolbar;
	// dolny pasek z progressbarem
	private StatusBar status;
	// panel z zakładkami do wyświetlania obrazów
	private JTabbedPane editor;

	public ContentPane(MainWindow mainWindow) {
		super(new BorderLayout());
		this.mainWindow = mainWindow;
		buildComponents();
		addAllComponents();
	}

	private void buildComponents() {
		toolbar = new ToolBar();
		status = new StatusBar(mainWindow);
		editor = new JTabbedPane();
		editor.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		status.updateProgress(0);
	}

	private void addAllComponents() {
		add(toolbar, BorderLayout.NORTH);
		add(editor, BorderLayout.CENTER);
		add(status, BorderLayout.SOUTH);
	}

	/**
	 * Zwraca zawartość aktywnej zakładki
	 * 
	 * @return
	 */
	public BufferedImage getInput() {
		if (editor.getSelectedComponent() != null) {
			return ((ImagePanel) editor.getSelectedComponent()).getImage();
		}
		return null;
	}

	/**
	 * Zwraca tytuł aktywnej zakładki
	 * 
	 * @return
	 */
	public String getInputTitle() {
		if (editor.getSelectedComponent() != null) {
			return ((ImagePanel) editor.getSelectedComponent()).getTitle();
		}
		return null;
	}

	/**
	 * Dodaje nową zakładkę, zawierającą zadaną treść
	 */
	public void addExistingItem(BufferedImage content, String title)
			throws IOException {

		ImagePanel newPanel = new ImagePanel();
		TitlePannel titlePannel = new TitlePannel(title);
		titlePannel.setName(title);
		editor.addTab("", newPanel);
		editor.setTabComponentAt(editor.indexOfComponent(newPanel), titlePannel);
		newPanel.setImage(content, title);

		// Zamyka zakładkę przyciskiem "X"
		titlePannel.getCloseButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDistinctItem(e);
			}
		});

		editor.setSelectedComponent(newPanel);
		toolbar.setButtonsEnabled(true);
	}

	/**
	 * Zamyka konkretną zakładkę, po kliknięciu "X". Pyta o potwierdzenie.
	 * 
	 * @param e
	 */
	protected void closeDistinctItem(ActionEvent e) {
		int response = JOptionPane.showConfirmDialog(mainWindow,
				"Czy napewno chcesz zamknąć plik?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			JPanel source = (JPanel) ((Component) e.getSource()).getParent();
			int i = editor.indexOfTabComponent(source);
			if (i != -1) {
				editor.remove(i);
			}
		}
		if ((editor.getTabCount() == 0)) {
			toolbar.setButtonsEnabled(false);
		}
	}

	/**
	 * Odpowiada za zamknięcie zakładki z plikiem przyciskiem na toolbarze
	 */
	public void closeSelectedItem() {
		if (editor.getTabCount() > 0) {
			int response = JOptionPane.showConfirmDialog(mainWindow,
					"Czy napewno chcesz zamknąć plik?", "",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				Component selected = editor.getSelectedComponent();
				if (selected != null) {
					int index = editor.indexOfComponent(selected);
					editor.remove(index);
				}
			}
		}
		if ((editor.getTabCount() == 0)) {
			toolbar.setButtonsEnabled(false);
		}
	}

	/**
	 * Czyści obszar edycji aktywnej zakładki
	 */
	public void clearAllItems() {

		int response = JOptionPane.showConfirmDialog(mainWindow,
				"Czy napewno chcesz zamknąć wszystkie zakładki?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			int tabCount = editor.getTabCount();
			for (int i = tabCount - 1; i >= 0; i--) {
				editor.remove(i);
			}
		}
	}

	/**
	 * Panel dodawany do zakładki, zawierający label w tytułem zakładki i
	 * przycisk "x"
	 * 
	 */
	private static class TitlePannel extends JPanel {

		private JButton closeButton = new JButton("x");
		private JLabel title;
		private static final int X_SIZE = 15;

		TitlePannel(String title) {
			super();
			this.title = new JLabel(title);
			constructTitlePannel();
		}

		private void constructTitlePannel() {
			closeButton.setPreferredSize(new Dimension(X_SIZE, X_SIZE));
			closeButton.setToolTipText(title.getText());
			closeButton.setUI(new BasicButtonUI());
			closeButton.setContentAreaFilled(false);
			closeButton.setFocusable(false);
			closeButton.setBorder(BorderFactory.createEtchedBorder());
			closeButton.setBorderPainted(false);
			closeButton.setRolloverEnabled(true);
			add(title);
			add(closeButton);
		}

		public JButton getCloseButton() {
			return closeButton;
		}
	}

	public void setProgress(int progress) {
		status.updateProgress(progress);
	}
	
	public StatusBar getStatus ()
	{
		return status;
	}
}
