package es.org.israel.internet;

import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Radiomarket extends Market {

	public Radiomarket(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public String[] recoverProducts() {
		Elements h2 = getDoc().select("h2 > a[href]");

		String[] products = h2.html().split("\n");
		
		return products;
	}

	public LinkedList<String> recoverPrices() {
		LinkedList<String> prices = new LinkedList<String>();
		Elements td = getDoc().select("td.tdcenter");
		for (Element element : td) {
			if (element.html().toString().contains("€")) {
				String[] conditions = element.html().toString().split("<br>");
				String price = conditions[2].replace("&nbsp;", " ");
				prices.add(price);
			}
		}

		return prices;
	}
}
