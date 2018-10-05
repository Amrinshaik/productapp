package com.capgemini.productapp.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.repository.ProductRepository;
import com.capgemini.productapp.service.ProductService;
import com.capgemini.productapp.service.impl.ProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
	
	@InjectMocks
	ProductServiceImpl productServiceImpl;


	@Mock
	ProductRepository productRepository;
	MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productServiceImpl).build();
	}
	@Test
	public void   testaddProduct() {
		Product product = new Product(101, "kettle", "crockery", 1000);
		when(productRepository.save(product)).thenReturn(product);
		Product addProduct = productServiceImpl.addProduct(product);
		assertEquals(101, addProduct.getProductId());
		//verify(productService).addProduct(product);
	}
		
/*	
	@Test
	public void findProductById() {
		Product product = new Product(101, "kettle", "crockery", 1000);
		Optional<Product> optionalProduct = productRepository.findById(product.getProductId());
		when(productRepository.findById(product.getProductId()).thenReturn(product));
		Product findProductById = productServiceImpl.findById(product);
		assertEquals((101, "kettle", "crockery", 1000), findProductById.getProductId());
	}*/
	
	@Test
	public void testupdateProduct() {
		Product product = new Product(101, "kettle", "crockery", 1000);
		when(productRepository.save(product)).thenReturn(product);
		Product updateProduct = productServiceImpl.updateProduct(product);
		assertEquals(101, updateProduct.getProductId());
	
	}
	@Test
	public void testdeleteproduct() {
		Product product = new Product(101, "kettle", "crockery", 1000);
		productServiceImpl.deleteProduct(product);

	}

}
