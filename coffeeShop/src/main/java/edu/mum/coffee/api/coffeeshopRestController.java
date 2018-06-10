package edu.mum.coffee.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.ProductService;

@RestController
@RequestMapping("/api")
public class coffeeshopRestController {

	@Autowired
	private ProductService productService;

	// save Products
	@PostMapping("/product")
	public Product createProducts(@Valid @RequestBody Product product) {
		return productService.save(product);
	}

	// get Products

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProduct();
	}
	//get by type
	@GetMapping("/productType/{type}")
	public List<Product> getProductsByType(@PathVariable(value = "type") ProductType type) {
		return productService.findByProductType(type);
	}

	// get product by id

	@GetMapping("/product/{id}")

	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") int id) {
		Product product = productService.getProduct(id);

		if (product == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(product);
	}

	// update product by id
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateEmployee(@PathVariable(value = "id") int id,
			@Valid @RequestBody Product productDetail) {
		// find the product
		Product product = productService.getProduct(id);
		if (product == null) {
			return ResponseEntity.notFound().build();

		}
		product.setProductName(productDetail.getProductName());
		product.setDescription(productDetail.getDescription());
		product.setPrice(productDetail.getPrice());
		product.setProductType(productDetail.getProductType());
		// add it ti the service
		Product updatedProduct = productService.save(product);
		return ResponseEntity.ok().body(updatedProduct);
	}

	// delete product

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Product> deketeProduct(@PathVariable(value = "id") int id) {
		Product product = productService.getProduct(id);

		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		productService.delete(product);
		return ResponseEntity.ok().build();
	}

}
