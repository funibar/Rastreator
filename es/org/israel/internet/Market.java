package es.org.israel.internet;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Market implements Runnable {
	private String url;
	protected Document doc;

	public Document getDoc() {
		return doc;
	}

	public Market(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void connect() {
		Response response = null;
		try {
			//TODO: check for content type and status for response
			response =  Jsoup.connect(getUrl())
					.ignoreContentType(true)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.timeout(12000) 
					.followRedirects(true)
					.execute();
			doc = response.parse();
		} catch (IOException e) {			
			System.err.println("Error: M8734 does the URL exists?"); //M8734 random choosen value
		}
	}

	@Override
	public void run() {
		connect();
	}
}
