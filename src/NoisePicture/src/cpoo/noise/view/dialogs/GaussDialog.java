package cpoo.noise.view.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Odpowiada za wyświetlenie okienka dialogowego do podawania argumentów szumu Gaussa.
 * @author Rafał Radziejewski
 *
 */
public class GaussDialog extends JDialog implements ActionListener {

	/*
	 * 1.6.1 Szum Gaussowski Szum Gaussowski polega na dodaniu do kazdego
	 * piksela liczby losowanej niezaleznie z rozkładu normalnego N(mi, sigma).
	 * W oknie dialogowym nalezy podac parametry: mi — wartosc oczekiwana
	 * rozkładu, jest liczba rzeczywista, sigma — wariancja rozkładu, sigma>0.
	 */

	private static final long serialVersionUID = -5704210797431156236L;
	
	private JTextField mean = null;
	private JTextField variance = null;

	private JTextField numberOfPictures = null;
	private JTextField meanStep = null;
	private JTextField varianceStep = null;

	private JCheckBox serialGeneration = null;

	private JButton ok = null;
	private JButton cancel = null;

	boolean answer = false;

	public GaussDialog(JFrame frame, boolean modal) {
		super(frame, modal);
		setTitle("Szum Gaussa");
		constructGuiElements();

		this.setLayout(new GridLayout(11, 2, 2, 2));

		this.add(new JLabel("Parametry rozkładu:"));
		this.add(new JPanel());
		
		this.add(new JLabel("Średnia:"));
		this.add(mean);

		this.add(new JLabel("Wariancja:"));
		this.add(variance);

		this.add(new JPanel());
		this.add(new JPanel());
		
		this.add(new JLabel("Generacja seryjna:"));
		this.add(new JPanel());
		
		this.add(new JLabel("Seria obrazów"));
		this.add(serialGeneration);

		this.add(new JLabel("Liczba obrazów:"));
		this.add(numberOfPictures);

		this.add(new JLabel("Skok średniej:"));
		this.add(meanStep);

		this.add(new JLabel("Skok wariancji:"));
		this.add(varianceStep);

		this.add(new JPanel());
		this.add(new JPanel());
		
		this.add(ok);
		this.add(cancel);

		((JComponent) getContentPane()).setBorder(BorderFactory
				.createEmptyBorder(10, 10, 10, 10));

		this.setResizable(false);

		ok.addActionListener(this);
		cancel.addActionListener(this);

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}

	private void constructGuiElements() {
		mean = new JTextField(10);
		mean.setText("0");
		variance = new JTextField(10);
		variance.setText("1");
		numberOfPictures = new JTextField(10);
		numberOfPictures.setText("0");
		meanStep = new JTextField(10);
		meanStep.setText("0");
		varianceStep = new JTextField(10);
		varianceStep.setText("0");
		serialGeneration = new JCheckBox();
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
	}

	/**
	 * Klikniecie przycisku OK
	 */
	public void actionPerformed(ActionEvent e) {
		if (ok == e.getSource()) {
			setVisible(false);
			answer = true;
		} else if (cancel == e.getSource()) {
			setVisible(false);
			answer = false;
		}
	}
	
	public JTextField getMean() {
		return mean;
	}

	public JTextField getVariance() {
		return variance;
	}

	public JTextField getNumberOfPictures() {
		return numberOfPictures;
	}

	public JTextField getMeanStep() {
		return meanStep;
	}

	public JTextField getVarianceStep() {
		return varianceStep;
	}

	public JCheckBox getSerialGeneration() {
		return serialGeneration;
	}
	
	public boolean getAnswer() {
		return answer;
	}
}