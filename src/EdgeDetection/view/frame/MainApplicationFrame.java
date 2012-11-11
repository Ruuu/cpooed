package view.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import view.View;
import view.dialog.AboutProgramDialog;
import view.listener.ExitListener;
import controller.event.BrokerActionEvent;
import javax.swing.JPanel;

/**
 * Klasa opisujaca glowne okno programu
 * @author Piotr Róż
 * @since 2012-11-11
 */
public class MainApplicationFrame extends JFrame
{
    /** dodanie ID */
    private static final long serialVersionUID = 1L;
    /** rozdzielczosc ekranu uzytkownika */
    private final Dimension screenSize;
    /** okno ukazujace informacje o programie */
    private AboutProgramDialog aboutProgramDialog;
    /** glowna klasa widoku */
    private View mainViewClass;
    
    /** menu programu */    
    private JMenuBar menuBar;
    /** menu 'Plik' */
    private JMenu fileMenu;
    /** menu 'Pomoc' */
    private JMenu helpMenu;
    /** element menu do wyswietlenia okna z informacja o programie */
    private JMenuItem aboutProgramMenuItem;
    /** element menu do wyjscia z programu */
    private JMenuItem exitMenuItem;
    
    /** oryginalny tytul aplikacji */
    public static final String originalTitle = "Edge Detection v1.0";

	/**
     * Konstruktor. Wywoluje glowne okno aplikacji.
     */
    public MainApplicationFrame(View view)
    {
        setTitle(originalTitle);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();
        setSize(new Dimension(600, 600));

        mainViewClass = view;

        aboutProgramDialog = new AboutProgramDialog();

        addMenu();
        
        myGroupLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Dodanie nasluchiwaczy do wszystkich obiektow programu
     * 
     * @param blockingQueue
     *            kolejka blokujaca
     */
    public void addMultipleListener(final BlockingQueue<BrokerActionEvent> blockingQueue)
    {
    	exitMenuItem.addActionListener(new ExitListener(blockingQueue));
    	aboutProgramMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				aboutProgramDialog.setVisible(true);
			}
		});
    }

    /**
     * Dodanie menu do glownego okna aplikacji
     */
    private void addMenu()
    {
    	menuBar = new JMenuBar();
        fileMenu = new JMenu();
        helpMenu = new JMenu();
    
        exitMenuItem = new JMenuItem(new ImageIcon(MainApplicationFrame.class.getResource("buttonIcons/exit16.png")));
        aboutProgramMenuItem = new JMenuItem(new ImageIcon(MainApplicationFrame.class.getResource("buttonIcons/about16.png")));
        
        /**
         * Dodanie Menu do MenuBar'a
         */
        fileMenu.setText("Plik");
        helpMenu.setText("Pomoc");
        
        fileMenu.setMnemonic('P');
        helpMenu.setMnemonic('O');

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
        
        exitMenuItem.setText("Wyjście");
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
        fileMenu.add(exitMenuItem);

        aboutProgramMenuItem.setText("O programie");
        helpMenu.add(aboutProgramMenuItem);
    }

    /**
     * Tworzenie zarzadcy rozkladu i dodanie elementow interfejsu
     */
    private void myGroupLayout()
    {	
    	getContentPane().setLayout(new BorderLayout());

    	pack();
    	setVisible(true);
    }
}
