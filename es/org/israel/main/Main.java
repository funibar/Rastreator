/**
 * 
 */
package es.org.israel.main;

import java.util.LinkedList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import es.org.israel.internet.Radiomarket;
import es.org.israel.internet.mail.Mail;
import es.org.israel.radio.Radio;

/**
 * @author Funibar
 *
 */
public class Main {
	public static String manufacturers[] = { "icom", "yaesu" };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Radiomarket m = new Radiomarket(
				"URL_TO_PARSE");
		m.run();

		String[] products = m.recoverProducts();
		LinkedList<String> prices = m.recoverPrices();
		LinkedList<Radio> radios = new LinkedList<Radio>();
		for (int i = 0; i < products.length; i++) {
			for (String manufacturer : manufacturers) {
				if (products[i].toLowerCase().contains(manufacturer)	
						&& !products[i].toLowerCase().contains("compro") 
						&& !products[i].toLowerCase().contains("reservad"))
					radios.add(new Radio(products[i], Double.parseDouble( prices.get(i))) );
			}
		}		
		
		Mail mail = new Mail(null, null);//set your email account settings (username and password)
		String[] personalMailSettings = {"SET_TITLE", radios.toString()};
		try {
			mail.generateAndSendEmail(personalMailSettings);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
