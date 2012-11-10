package Main;

import CyckowyAlgorytm.Cycki;
import DupnyAlgorytm.Dupa;
/**
 * 
 */

/**
 * @author Ru
 *
 */
public class Main 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Dupa dupa = new Dupa();
		dupa.compute(33);
		
		Cycki cycki = new Cycki();
		cycki.compute(99);
		
		System.out.println("Zaawansowany dzialam!");
	}

}
