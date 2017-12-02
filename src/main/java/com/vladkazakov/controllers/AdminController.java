package com.vladkazakov.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vladkazakov.entities.Audiobook;
import com.vladkazakov.entities.Book;
import com.vladkazakov.entities.EBook;
import com.vladkazakov.entities.Product;
import com.vladkazakov.services.IndexContent;
import com.vladkazakov.services.ProductService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/getProducts", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProducts(@RequestParam String sortMode, Integer numberPage, String category) {
		Sort sort;
		if (sortMode.equals("asc")) {
			sort = new Sort(new Sort.Order(Direction.ASC, "name"));
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "name"));
		}
		Pageable pageable = new PageRequest(numberPage, 5, sort);
		return productService.getAdminByCategory(category, pageable);
	}

	@RequestMapping(value = "/saveFile", method = RequestMethod.POST)
	public @ResponseBody Boolean saveFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "id") int id) {
		System.out.println(id);
		if (file != null) {
			byte[] bytes = null;
			try {
				bytes = file.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			productService.saveFile(id, bytes, file.getOriginalFilename());
		}
		return true;
	}

	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public @ResponseBody Boolean saveBook(@RequestBody Book book) {
		productService.saveProduct(book);
		return true;
	}

	@RequestMapping(value = "/saveEBook", method = RequestMethod.POST)
	public @ResponseBody Boolean saveEBook(@RequestBody EBook book) {
		productService.saveProduct(book);
		return true;
	}


	@RequestMapping(value = "/saveAudiobook", method = RequestMethod.POST)
	public @ResponseBody Boolean saveAudiobook(@RequestBody Audiobook book) {
		productService.saveProduct(book);
		return true;
	}

	@RequestMapping(value = "/removeProduct", method = RequestMethod.GET)
	public @ResponseBody Boolean removeProduct(@RequestParam int id) {
		productService.removeProduct(id);
		return true;
	}

	@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
	public @ResponseBody Product getProduct(@RequestParam int id) {
		return productService.getById(id);
	}

	@RequestMapping(value = "/changeIndex", method = RequestMethod.GET)
	public @ResponseBody List<Product> changeIndex(@RequestParam boolean flag) {
		IndexContent.contentFlag = flag;
		return productService.getIndexContent();
	}

	@RequestMapping(value = "/indexContentAdmin", method = RequestMethod.GET)
	public @ResponseBody List<Product> indexContent() {
		return productService.getIndexContent();
	}

}
