package com.vladkazakov.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vladkazakov.entities.Cart;
import com.vladkazakov.entities.Order;
import com.vladkazakov.entities.Product;
import com.vladkazakov.entities.Raiting;
import com.vladkazakov.repositories.CartRepository;
import com.vladkazakov.repositories.OrderRepository;
import com.vladkazakov.repositories.ProductRepository;
import com.vladkazakov.repositories.RaitingRepository;

@Service("productService")
public class ProductService {
	private static final String PATH_TO_PHOTO = "d:\\images";
	@Autowired
	ProductRepository productRepository;
	@Autowired
	RaitingRepository raitingRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	OrderRepository orderRepository;

	public ProductService() {
	}

	@Transactional
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Transactional
	public String saveFile(int id, byte[] bytes, String filename) {

		String path = id + filename;
		File photo = new File(PATH_TO_PHOTO + File.separator + path);
		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(photo));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			stream.write(bytes);
			stream.flush();
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	@Transactional
	public byte[] getFile(String path) {
		ByteArrayOutputStream out = null;
		InputStream input = null;

		try {
			out = new ByteArrayOutputStream();
			try {
				System.out.println(PATH_TO_PHOTO + File.separator + path);

				input = new BufferedInputStream(new FileInputStream(PATH_TO_PHOTO + File.separator + path));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			int data = 0;
			try {
				while ((data = input.read()) != -1) {
					out.write(data);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		byte[] bytes = out.toByteArray();
		return bytes;

	}
	@Transactional
	public void removeProduct(int id) {
		productRepository.delete(id);
	}

	@Transactional
	public Product getById(int id) {
		SOAPService soapService = new SOAPService();
		List<Raiting> raitings;
		Product product = (Product) productRepository.findOne(id);
		product.setPrice(soapService.getById(product.getId()));// получение цен на продукт
		raitings=raitingRepository.findByIdProduct(product.getId());//получение и установка рейтинга товара
		product.setReiting(Raiting.stars(raitings));	
		return product;
	}

	@Transactional
	public List<Product> getCart(int idUser) {
		SOAPService soapService = new SOAPService();
		List<Raiting> raitings;
		List<Cart> carts = cartRepository.findByIdUser(idUser);// получение корзины пользователя
		List<Integer> keys = new ArrayList<Integer>();
		for (int i = 0; i < carts.size(); i++) {
			keys.add(carts.get(i).getIdProduct());// получение списка id товаров из корзины
		}
		List<Product> products = productRepository.findAll(keys);// получение списка продуктов
		soapService.getByKeys(keys, products);// получение цен на продукты
		for(int j=0;j<products.size();j++) {
			for (int i = 0; i < carts.size(); i++) {
				if (products.get(j).getId() == carts.get(i).getIdProduct()) {
				products.get(j).setCartCount(carts.get(i).getCount());// получение количества продуктов	
				}
				raitings=raitingRepository.findByIdProduct(products.get(i).getId());//получение и установка рейтинга товара
				products.get(i).setReiting(Raiting.stars(raitings));
			}	
		}
			
		for (int i = 0; i < carts.size(); i++) {
			if (products.get(i).getPrice() == 0) {
				products.remove(i);// удаление продуктов, на кторые нет цены
			}
		}
		System.out.println("Корзина" + products);
		return products;
	}

	@Transactional
	private List<Product> getAll(String category, Pageable pageable) {
		List<Raiting> raitings;
		SOAPService soapService = new SOAPService();
		List<Product> products = productRepository.findByCategory(category,pageable);//получение товаров заданной категории
		List<Integer> keys = new ArrayList<Integer>();
		for (int i = 0; i < products.size(); i++) {
			keys.add(products.get(i).getId());// получение списка id товаров
		}
		soapService.getByKeys(keys, products);
		for (int i = 0; i < products.size(); i++) {
			raitings = raitingRepository.findByIdProduct(products.get(i).getId());//получение и установка рейтинга товара
			products.get(i).setReiting(Raiting.stars(raitings));
		}		
		return products;
	}

	public List<Product> getByCategory(String category, Pageable pageable) {
		List<Product> products = new ArrayList<Product>();
		List<Product> temp = getAll(category, pageable);
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getPrice() != 0) {
				products.add(temp.get(i));// удаление продуктов, на кторые нет
											// цены
			}
		}
		return products;
	}

	public List<Product> getAdminByCategory(String category, Pageable pageable) {
		return getAll(category, pageable);
	}

	@Transactional
	public List<Product> getOrders(int idUser) {
		List<Order> orders = orderRepository.findByIdUserOrder(idUser);
		List<Product> products = new ArrayList<Product>();
		Product product;
		for (int i = 0; i < orders.size(); i++) {
			product = productRepository.findOne(orders.get(i).getIdProduct());
			product.setCartCount(orders.get(i).getCount());
			products.add(product);
		}
		return products;
	}

	@Transactional
	public void addToCart(int idUser,int idProduct,int count) {
		Cart cart = cartRepository.findByIdUserAndIdProduct(idUser, idProduct);
		if (cart == null) {
		cartRepository.save(new Cart(idUser, idProduct, count));
		}
		else {
			cart.setCount(cart.getCount() + count);
			cartRepository.save(cart);
		}
	}

	@Transactional
	public void changeCart(int idUser, int idProduct, int count) {
		Cart cart = cartRepository.findByIdUserAndIdProduct(idUser, idProduct);
		if (cart == null) {
			cartRepository.save(new Cart(idUser, idProduct, count));
		} else {
			cart.setCount(count);
			cartRepository.save(cart);
		}
	}

	@Transactional
	public void addOrder(Order order) {
		System.out.println(order);
		orderRepository.save(order);
	}

	@Transactional
	public void changeProductCount(Order order) {
		Product product = productRepository.findOne(order.getIdProduct());
		product.setCount(product.getCount() - order.getCount());
		productRepository.save(product);
	}

	@Transactional
	public void removeFromCart(int idUser, int idProduct) {
		Cart cart = cartRepository.findByIdUserAndIdProduct(idUser, idProduct);
		cartRepository.delete(cart);
	}

	@Transactional
	public void addRaiting(int idUser, int idProduct, int value) {
		raitingRepository.save(new Raiting(idUser, idProduct, value));
	}

	@Transactional
	public boolean checkRaiting(int idUser, int idProduct) {
		Raiting raiting = raitingRepository.findByIdProductAndIdUser(idProduct, idUser);
		if (raiting == null) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public void updateCart(List<Cart> products, int idUser) {
		for (int i = 0; i < products.size(); i++) {
			System.out.println(products.get(i));
		}
		List<Cart> carts = cartRepository.findByIdUser(idUser);
		System.out.println(carts);
		boolean flag;
		for (int i = 0; i < products.size(); i++) {
			flag = true;
			for (int j = 0; j < carts.size(); j++) {
				if (carts.get(j).getIdProduct() == products.get(i).getIdProduct()) {
					System.out.println("inside");
					carts.get(j).setCount(products.get(i).getCount() + carts.get(j).getCount());
					cartRepository.save(carts.get(j));
					flag = false;
				}
			}
			if (flag) {
				cartRepository.save(new Cart(idUser, products.get(i).getIdProduct(), products.get(i).getCount()));
			}
		}
}

	@Transactional
	public int getSizeCart(int idUser) {
		return cartRepository.findByIdUser(idUser).size();
	}

	@Transactional
	public List<Product> search(String reg) {
		
		List<Raiting> raitings;
		SOAPService soapService = new SOAPService();
		List<Product> products = productRepository.findByNameContaining(reg);//получение товаров заданной категории
		List<Integer> keys = new ArrayList<Integer>();
		for (int i = 0; i < products.size(); i++) {
			keys.add(products.get(i).getId());// получение списка id товаров
		}
		soapService.getByKeys(keys, products);
		for (int i = 0; i < products.size(); i++) {
			raitings=raitingRepository.findByIdProduct(products.get(i).getId());//получение и установка рейтинга товара
			products.get(i).setReiting(Raiting.stars(raitings));
		}		
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getPrice() == 0) {
				products.remove(i);// удаление продуктов, на кторые нет цены
			}
		}
		return products;
	}

	@Transactional
	public List<Product> getIndexContent() {
		SOAPService soapService = new SOAPService();
		List<Integer> keys = new ArrayList<Integer>();
		List<Raiting> raitings;
		List<Product> products;
		if (!IndexContent.contentFlag) {
			products = new ArrayList<Product>();
			raitings = raitingRepository.findSum();
			for (int i = 0; i < raitings.size(); i++) {
				products.add(productRepository.findOne(raitings.get(i).getIdProduct()));
				keys.add(raitings.get(i).getIdProduct());
			}
		} else {
			products = productRepository.findFirst4ByOrderByIdDesc();
			for (int i = 0; i < products.size(); i++) {
				keys.add(products.get(i).getId());
			}
			}
		soapService.getByKeys(keys, products);
		for (int i = 0; i < products.size(); i++) {
			raitings=raitingRepository.findByIdProduct(products.get(i).getId());//получение и установка рейтинга товара
			products.get(i).setReiting(Raiting.stars(raitings));
		}		
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getPrice() == 0) {
				products.remove(i);
			}
		}
		return products;

	}

	public Integer getCountByCategory(String category) {
		return productRepository.countByCategory(category);
	}

}
