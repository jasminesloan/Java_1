package com.jasminesloan.packages;

public enum Info {
	
	MAC("21.5-inch", "1,299.00", "2.7GHz quad-core Intel Core i5", "1TB hard drive"),
	MACBOOKPRO("13-inch", "1,199.00", "2.5GHz dual-core Intel Core i5", "500GB 5400-rpm hard drive"),
	MACBOOKAIR("11-inch", "999.00", "1.3GHz dual-core Intel Core i5 ", "128GB flash storage"),
	IPAD("9.50 inch", "499.00", "802.11a/b/g/n Wi‑Fi (802.11n 2.4GHz and 5GHz)", "16GB"),
	MINI("7.87 inch", "3299.00", "802.11a/b/g/n Wi‑Fi (802.11n 2.4GHz and 5GHz)", "16GB"),
	IPOD("4.86 inch", "229.00", "802.11a/b/g/n Wi-Fi (802.11n 2.4GHz and 5GHz)", "16GB"),
	IPHONE("4.87", "199.00", "Wifi/LTE/4G", "16GB");
	
	private final String screenSize;
	private final String price;
	private final String processor;
	private final String storage; 
	
	private Info(String screenSize, String price, String processor, String storage){
		this.screenSize = screenSize;
		this.price = price;
		this.processor = processor;
		this.storage = storage;
	}
	
	public String setScreenSize(){
		return screenSize;
	}
	
	public String setPrice(){
		return price;
	}
	
	public String setProcessor(){
		return processor;
	}
	
	public String setStorage(){
		return storage;
	}
	
}
