package com.capgemini.productapp.test;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.ProductController;
import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;
	MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void testAddProductWhichReturnsProduct() throws Exception {
		Product product = new Product(101,"kettle","crockery",1000);
		
		when(productService.addProduct(Mockito.isA(Product.class))).thenReturn(product);
				
		String content=("{\r\n" + 
				        "   \"productId\":101,\r\n" + 
				        "	\"productName\":\"kettle\",\r\n" + 
						"	\"productCategory\":\"crockery\",\r\n" + 
						"	\"productPrice\":1000\r\n" + 
						"}");
				mockMvc.perform(post("/product")
				.contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists())
				.andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").value(101))
				.andExpect(jsonPath("$.productName").value("kettle"))
				.andExpect(jsonPath("$.productCategory").value("crockery"))
				.andExpect(jsonPath("$.productPrice").value(1000))
				.andDo(print());
	
      }
	@Test
	public void testfindproductbyId() throws Exception {
		
		when(productService.findProductById(101)).thenReturn(new Product(101, "kettle", "crockery", 1000));
		mockMvc.perform(get("/products/101").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists())
				.andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").value(101))
				.andExpect(jsonPath("$.productName").value("kettle"))
				.andExpect(jsonPath("$.productCategory").value("crockery"))
				.andExpect(jsonPath("$.productPrice").value(1000));

	}

	@Test
	public void testupdateProduct() throws Exception {

		when(productService.updateProduct(Mockito.isA(Product.class))).thenReturn(new Product(101, "kettle", "crockery", 1000));
		when(productService.findProductById(1234)).thenReturn(new Product(101, "kettle", "crockery", 1000));
		
		String content = "{\r\n" + "	\"productId\":101,\r\n" + "	\"productName\":\"kettle\",\r\n"
				+ "	\"productCategory\":\"crockery\",\r\n" + "	\"productPrice\":1000\r\n" + "}";

		mockMvc.perform(put("/product").contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists())
				.andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").value(101))
				.andExpect(jsonPath("$.productName").value("kettle"))
				.andExpect(jsonPath("$.productCategory").value("crockery"))
				.andExpect(jsonPath("$.productPrice").value(1000))
				.andDo(print());

	}
	  @Test
	  public void testDeleteProduct() throws Exception {
		 when(productService.findProductById(101)).thenReturn(new Product(101,"kettle","crockery",1000));
		  mockMvc.perform(delete("/products/101")
		  .accept(MediaType.APPLICATION_JSON))
	      //.andExpect(jsonPath("$.status").value(200)) 
		  .andExpect(status().isOk()) 
		  //.andExpect(status().isOk()) 
		  //.andExpect(jsonPath("$.message").value("Product deleted"))
		  .andDo(print());
	}
	  

}
