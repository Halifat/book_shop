package com.vladkazakov.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vladkazakov.entities.Cart;
import com.vladkazakov.entities.Order;
import com.vladkazakov.entities.Product;
import com.vladkazakov.services.ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "products")
public class ProductController {
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/itemsLength", method = RequestMethod.GET)
	public @ResponseBody Integer itemsLength(@RequestParam String category) {
		return productService.getCountByCategory(category);
	}

	@RequestMapping(value = "/getProducts", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProducts(@RequestParam String sortMode, Integer numberPage, String category) {
		Sort sort;
		if (sortMode.equals("asc")) {
			sort = new Sort(new Sort.Order(Direction.ASC, "name"));
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "name"));
		}
		Pageable pageable = new PageRequest(numberPage, 5, sort);
		return productService.getByCategory(category, pageable);
	}



	@RequestMapping(value = "/getCart", method = RequestMethod.GET)
	public @ResponseBody List<Product> getCart(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if (!role.equals("user")) {
			return null;
		}
		int idUser = (Integer) session.getAttribute("idUser");
		return productService.getCart(idUser);
	}

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public @ResponseBody Boolean addOrder(@RequestBody Order[] orders, HttpSession session) {
		String role = (String) session.getAttribute("role");
		if (role != null && role.equals("user")) {
			int idUser = (Integer) session.getAttribute("idUser");
			for (int i = 0; i < orders.length; i++) {
				orders[i].setIdUser(idUser);
				productService.addOrder(orders[i]);
			}
		}
		for (int i = 0; i < orders.length; i++) {
			productService.changeProductCount(orders[i]);
		}
		return true;
	}

	@RequestMapping(value = "/removeFromCart", method = RequestMethod.GET)
	public @ResponseBody Boolean removeFromCart(@RequestParam int idProduct, HttpSession session) {
		int idUser = (Integer) session.getAttribute("idUser");
		productService.removeFromCart(idUser, idProduct);
		return true;
	}

	@RequestMapping(value = "/getOrders", method = RequestMethod.GET)
	public @ResponseBody List<Product> getOrders(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if (!role.equals("user")) {
			return null;
		}
		int idUser = (Integer) session.getAttribute("idUser");
		return productService.getOrders(idUser);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody List<Product> search(@RequestParam String reg) {
		return productService.search(reg);
	}

	@RequestMapping(value = "/indexContent", method = RequestMethod.GET)
	public @ResponseBody List<Product> indexContent() {
		return productService.getIndexContent();
	}


	@RequestMapping(value = "/checkRaiting", method = RequestMethod.GET)
	public @ResponseBody Boolean checkRaiting(@RequestParam int idProduct, HttpSession session) {
		System.out.println(idProduct);
		int idUser = (Integer) session.getAttribute("idUser");
		return productService.checkRaiting(idUser, idProduct);
	}

	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public @ResponseBody Boolean addToCart(@RequestParam(required = true) int idProduct, int count, HttpSession session) {
		int idUser = (Integer) session.getAttribute("idUser");
		productService.addToCart(idUser, idProduct, count);
		return true;
	}

	@RequestMapping(value = "/changeCart", method = RequestMethod.GET)
	public @ResponseBody Boolean changeCart(@RequestParam(required = true) int idProduct, int count, HttpSession session) {
		int idUser = (Integer) session.getAttribute("idUser");
		productService.changeCart(idUser, idProduct, count);
		return true;
	}

	@RequestMapping(value = "/updateCart", method = RequestMethod.POST)
	public @ResponseBody Boolean updateCart(@RequestBody Cart[] products, HttpSession session) {
		int idUser = (Integer) session.getAttribute("idUser");
		productService.updateCart(Arrays.asList(products), idUser);
		return true;
	}

	@RequestMapping(value = "/addRaiting", method = RequestMethod.GET)
	public @ResponseBody Boolean addRaiting(@RequestParam(required = true) int idProduct, int value, HttpSession session) {
		int idUser = (Integer) session.getAttribute("idUser");
		productService.addRaiting(idUser, idProduct, value);
		return true;
	}

	@RequestMapping(value = "/sizeCart", method = RequestMethod.GET)
	public @ResponseBody Integer getSizeCart(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if (!role.equals("user")) {
			return null;
		}
		int idUser = (Integer) session.getAttribute("idUser");
		return productService.getSizeCart(idUser);
	}

	@RequestMapping(value = "/images", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(String path) {
		return new ResponseEntity<byte[]>(productService.getFile(path), HttpStatus.CREATED);
	}

}
