package com.example.exceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptionHandling.Exceptions.ProductAlreadyExists;
import com.example.exceptionHandling.Exceptions.ProductNotFoundException;
import com.example.exceptionHandling.Model.Product;
@RestController
public class ProductServiceController {
	
	private static Map<Integer, Product> productList =  new HashMap<>();
	static {
		Product honey = new Product();
		honey.setId(1);
		honey.setName("Dabur Honey");
		productList.put(1, honey);
		
		Product coffee = new Product();
		coffee.setId(2);
		coffee.setName("Nescafe");
		productList.put(2,coffee);	
	}
	@RequestMapping(value="/products", method= RequestMethod.GET)
	public ResponseEntity<Object> getAllProducts(){
		if(productList.isEmpty()) throw new ProductNotFoundException();
		return new ResponseEntity<>(productList,HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{id}", method= RequestMethod.GET)
	public ResponseEntity<Object> getProduct(@PathVariable("id") int id){
		if(!productList.containsKey(id)) throw new ProductNotFoundException();
		return new ResponseEntity<>(productList.get(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{id}", method= RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Product product){
		if(!productList.containsKey(id)) throw new ProductNotFoundException();
		productList.remove(id);
		product.setId(id);
		productList.put(id, product);
		return new ResponseEntity<>("Product is updated successfully",HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{id}", method= RequestMethod.POST)
	public ResponseEntity<Object> addProduct(@PathVariable("id") int id, @RequestBody Product product){
		if(productList.containsKey(id)) throw new ProductAlreadyExists();
		product.setId(id);
		productList.put(id, product);
		return new ResponseEntity<>("Product is added successfully",HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Object> removeProduct(@PathVariable("id") int id){
		if(!productList.containsKey(id)) throw new ProductNotFoundException();
		productList.remove(id);
		return new ResponseEntity<>("Product is removed successfully",HttpStatus.OK);
	}
	
	
	
	

	

}
