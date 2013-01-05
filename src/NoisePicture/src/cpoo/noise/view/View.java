package cpoo.noise.view;


/**
 * 
 * @author Rafał Radziejewski
 *
 */
public class View {

	private MainWindow mainWindow;
	
	public View() {
		setMainWindow(new MainWindow());
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void setProgres(int progress) {
		mainWindow.getContent().setProgress(progress);
		
	}	
}
