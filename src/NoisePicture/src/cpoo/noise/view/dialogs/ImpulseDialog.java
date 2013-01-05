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
 * 
 * @author Rafał Radziejewski
 *
 */
@SuppressWarnings("serial")
public class ImpulseDialog extends JDialog implements ActionListener {

	/*
	 * Szum impulsowy Pn, lub tzw. szum sól i pieprz, obliczany jest w sposób
	 * niezalezny dla kazdego piksela Po:
	 * 
	 * Pn = val1 z p-ństwem p1 LUB val2 z p-ństwem p2 LUB Po w p.p.
	 * 
	 * Wartości val1 i val2 są losowo dodawane lub odejmowane.
	 */

	private JTextField p1 = null;
	private JTextField val1 = null;
	private JTextField p2 = null;
	private JTextField val2 = null;

	private JTextField numberOfPictures = null;
	private JTextField p1Step = null;
	private JTextField p2Step = null;

	private JTextField val1Step = null;
	private JTextField val2Step = null;

	private JCheckBox serialGeneration = null;

	private JButton ok = null;
	private JButton cancel = null;

	boolean answer = false;

	public ImpulseDialog(JFrame frame, boolean modal) {
		super(frame, modal);
		setTitle("Szum Impulsowy");
		constructGuiElements();

		this.setLayout(new GridLayout(15, 2, 2, 2));

		this.add(new JLabel("Parametry szumu:"));
		this.add(new JPanel());

		this.add(new JLabel(
				"<html>Prawdopodobieństwo 1:<br><font size='1'>(0 - 1)</font></html>"));
		this.add(p1);

		this.add(new JLabel(
				"<html>Wartość 1:<br><font size='1'>(0 - 255)</font></html>"));
		this.add(val1);

		this.add(new JLabel(
				"<html>Prawdopodobieństwo 2:<br><font size='1'>(0 - '1-prawd. 1')</font>"));
		this.add(p2);

		this.add(new JLabel(
				"<html>Wartość 2:<br><font size='1'>(0 - 255)</font></html>"));
		this.add(val2);

		this.add(new JPanel());
		this.add(new JPanel());

		this.add(new JLabel("Generacja seryjna:"));
		this.add(new JPanel());

		this.add(new JLabel("Seria obrazów:"));
		this.add(serialGeneration);

		this.add(new JLabel("Liczba obrazów:"));
		this.add(numberOfPictures);

		this.add(new JLabel("Skok prawd. 1:"));
		this.add(p1Step);

		this.add(new JLabel("Skok wartości 1:"));
		this.add(val1Step);

		this.add(new JLabel("Skok prawd. 2:"));
		this.add(p2Step);

		this.add(new JLabel("Skok wartości 2:"));
		this.add(val2Step);

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

		p1 = new JTextField(10);
		p1.setText("0");
		val1 = new JTextField(10);
		val1.setText("0");
		p2 = new JTextField(10);
		p2.setText("0");
		val2 = new JTextField(10);
		val2.setText("0");

		serialGeneration = new JCheckBox();
		numberOfPictures = new JTextField(10);
		numberOfPictures.setText("0");
		p1Step = new JTextField(10);
		p1Step.setText("0");
		val1Step = new JTextField(10);
		val1Step.setText("0");
		p2Step = new JTextField(10);
		p2Step.setText("0");
		val2Step = new JTextField(10);
		val2Step.setText("0");

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

	public JTextField getP1() {
		return p1;
	}

	public JTextField getVal1() {
		return val1;
	}

	public JTextField getP2() {
		return p2;
	}

	public JTextField getVal2() {
		return val2;
	}

	public JTextField getNumberOfPictures() {
		return numberOfPictures;
	}

	public JTextField getP1Step() {
		return p1Step;
	}

	public JTextField getP2Step() {
		return p2Step;
	}

	public JTextField getVal1Step() {
		return val1Step;
	}

	public JTextField getVal2Step() {
		return val2Step;
	}

	public JCheckBox getSerialGeneration() {
		return serialGeneration;
	}

	public boolean getAnswer() {
		return answer;
	}
}