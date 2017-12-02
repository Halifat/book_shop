package com.epam.vladkazakov.services;

import java.util.List;

import com.epam.vladkazakov.entities.Product;

import service.PriceService;
import service.PriceServiceImplService;

class SOAPService {
	// private final String SOAP_SERVICE_URL =
	// "http://localhost:8090/price_service/services/PriceServiceImpl?wsdl";
	private PriceService webservice;
	public SOAPService() {
		PriceServiceImplService service = new PriceServiceImplService();
		webservice = service.getPriceServiceImplPort();
	}

	public double getById(int id) {
		return webservice.getById(id);
	}

	public List<Product> getAll(List<Product> books) {
		List<Double> prices = webservice.getAll();
		for (int i = 0; i < books.size(); i++) {
			books.get(i).setPrice(prices.get(i));
		}
		return books;
	}

	public List<Product> getByKeys(List<Integer> keys, List<Product> products) {
		List<Double> prices = webservice.getByKeys(keys);
		System.out.println(keys);
		System.out.println(prices);
		System.out.println(products);
		for (int i = 0; i < products.size(); i++) {
			products.get(i).setPrice(prices.get(i));
		}
		return products;
	}
}
