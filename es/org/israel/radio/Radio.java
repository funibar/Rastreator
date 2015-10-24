package es.org.israel.radio;

public class Radio {
	private String manufacturer;
	private double price;

	public Radio(String manufacturer, double price) {
		this.manufacturer = manufacturer;
		setPrice(price);
	}

	public void setPrice(double price) {
		if (price > 0D)
			this.price = price;
	}
	
	@Override
	public String toString() {
		return "Radio [manufacturer=" + manufacturer + ", price=" + price + "]<br>";
	}
}
