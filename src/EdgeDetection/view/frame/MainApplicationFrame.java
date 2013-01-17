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
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import view.View;
import view.dialog.AboutProgramDialog;
import view.listener.CannyListener;
import view.listener.ExitListener;
import view.listener.OpenImageListener;
import view.listener.OurAlgorithmListener;
import view.listener.SobelListener;
import controller.event.BrokerActionEvent;

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
    /** menu 'Algorytmy' */
    private JMenu algorithmsMenu;
    /** menu 'Pomoc' */
    private JMenu helpMenu;
    /** element menu do wyswietlenia okna z informacja o programie */
    private JMenuItem aboutProgramMenuItem;
    /** element menu do zaladowania zdjecia */
    private JMenuItem loadImageMenuItem;

    /** element menu do testowania algorytmu przykladowego */
    private JMenuItem ourAlgoritmhMenuItem;
    /** element menu do testowania algorytmu Canny */
    private JMenuItem cannyMenuItem;
    /** element menu do testowania algorytmu Sobel */
    private JMenuItem sobelMenuItem;
    /** element menu do wyjscia z programu */
    private JMenuItem exitMenuItem;
    
    /** */
    private JSplitPane jSplitPane;
    
    /** ramka obrazu */
    private ImageFrame imageFrame;
    /** */
    private ImageFrame modImageFrame;
    
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
        setPreferredSize(screenSize);

        imageFrame = new ImageFrame();
        modImageFrame = new ImageFrame();
        
        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imageFrame, modImageFrame);
        jSplitPane.setOneTouchExpandable(true);
        jSplitPane.setDividerLocation((int)screenSize.getWidth() / 2);
        jSplitPane.setPreferredSize(new Dimension(800, 600));
        
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
    	loadImageMenuItem.addActionListener(new OpenImageListener(blockingQueue));
    	ourAlgoritmhMenuItem.addActionListener(new OurAlgorithmListener(blockingQueue));
    	cannyMenuItem.addActionListener(new CannyListener(blockingQueue));
    	sobelMenuItem.addActionListener(new SobelListener(blockingQueue));
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
        algorithmsMenu = new JMenu();
        helpMenu = new JMenu();
    
        exitMenuItem = new JMenuItem(new ImageIcon(MainApplicationFrame.class.getResource("buttonIcons/exit16.png")));
        loadImageMenuItem = new JMenuItem(new ImageIcon(MainApplicationFrame.class.getResource("buttonIcons/open16.png")));
        ourAlgoritmhMenuItem = new JMenuItem();
        cannyMenuItem = new JMenuItem();
        sobelMenuItem = new JMenuItem();
        aboutProgramMenuItem = new JMenuItem(new ImageIcon(MainApplicationFrame.class.getResource("buttonIcons/about16.png")));
        
        /**
         * Dodanie Menu do MenuBar'a
         */
        fileMenu.setText("Plik");
        algorithmsMenu.setText("Algorytmy");
        helpMenu.setText("Pomoc");
        
        fileMenu.setMnemonic('P');
        algorithmsMenu.setMnemonic('A');
        helpMenu.setMnemonic('O');

        menuBar.add(fileMenu);
        menuBar.add(algorithmsMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
        
        loadImageMenuItem.setText("Otwórz...");
        loadImageMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        fileMenu.add(loadImageMenuItem);
        
        fileMenu.addSeparator();
        
        exitMenuItem.setText("Wyjście");
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
        fileMenu.add(exitMenuItem);
        
        ourAlgoritmhMenuItem.setText("Autorski prosty algorytm");
        ourAlgoritmhMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl B"));
        algorithmsMenu.add(ourAlgoritmhMenuItem);
       
        cannyMenuItem.setText("Canny Edge Detector");
        cannyMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        algorithmsMenu.add(cannyMenuItem); 
        
        sobelMenuItem.setText("Sobel Edge Detector");
        sobelMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        algorithmsMenu.add(sobelMenuItem); 
        
        aboutProgramMenuItem.setText("O programie");
        helpMenu.add(aboutProgramMenuItem);
    }

    /**
     * Tworzenie zarzadcy rozkladu i dodanie elementow interfejsu
     */
    private void myGroupLayout()
    {	
    	setLayout(new BorderLayout());
    	
    	JPanel mainPanel = new JPanel();
    	
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(jSplitPane, BorderLayout.CENTER);
    	add(mainPanel, BorderLayout.CENTER);
    	
    	
    	pack();
    	setVisible(true);
    }
    
    /**
     * 
     */
    public void showMainImage(String imagePath)
    {
//    	JPanel imagePanel = new ImagePanel(imagePath);
//    	this.getContentPane().add(imagePanel);
//    	this.pack();
    	
//    	BufferedImage image = null;
//		try 
//		{
//			image = ImageIO.read(new File(imagePath));
//		} 
//		catch (IOException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		ImageIcon imageIcon = new ImageIcon(image);
//    	JLabel imageLabel = new JLabel(imageIcon);
//    	Dimension size = new Dimension();
//    	size.width = imageIcon.getIconWidth();
//    	size.height = imageIcon.getIconHeight();
//    	imageLabel.setPreferredSize(size);
//    	add(imageLabel);
//    	pack();
    	
    	imageFrame.paintImage(imagePath);
    }
    
    /**
     * 
     */
    public void showModImage(String imagePath)
    {
    	modImageFrame.paintImage(imagePath);
    	jSplitPane.setDividerLocation((int)screenSize.getWidth() / 2);
    }
}
