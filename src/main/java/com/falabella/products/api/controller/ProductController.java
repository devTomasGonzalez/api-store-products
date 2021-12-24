package com.falabella.products.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.falabella.products.api.exceptions.BadRequestException;
import com.falabella.products.api.exceptions.ValidBodyException;
import com.falabella.products.api.model.dto.ProductDTO;
import com.falabella.products.api.model.entity.Product;
import com.falabella.products.api.service.IProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Products controller")
@RequestMapping(path = "/api/v1/products")
public class ProductController {

	@Autowired
    private IProductService productService;
	
//	public TransactionController(ITransactionService transactionService) {
//        this.transactionService = transactionService;
//    }
	
	@GetMapping(value = "/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find a Product by SKU", response = Product.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Successfully retrieved Product."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Product not found"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Error.") })
	public ResponseEntity<Product> getProductBySku(@PathVariable("sku") String sku){
		Product product = this.productService.getProductBySku(sku);
		return ResponseEntity.ok(product); 
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all Products", response = Product.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Successfully retrieved Product List."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Products not found"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Error.") })
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> productsList = this.productService.getAllProducts();
		return ResponseEntity.ok(productsList); 
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add new Product", response = Product.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "OK. Product has been created correctly.", response = ProductDTO.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad Request.", response = String.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Resource not found.") })
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) throws BadRequestException {
		if (bindingResult.hasErrors()){
			throw new ValidBodyException(bindingResult);

	    }

		Product productCreated = this.productService.createProduct(productDTO);
	    return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Product by ID", response = Product.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK. Product has been updated correctly.", response = ProductDTO.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad Request.", response = String.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Resource not found.") })
	public ResponseEntity<?> updateProduct(@PathVariable(value = "id") long id, @Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			throw new ValidBodyException(bindingResult);
	    }
		Product productModified = this.productService.updateProduct(id, productDTO);
	    return new ResponseEntity<Product>(productModified, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Delete Product by ID")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK. Product has been deleted correctly."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Product ID not found.") })
	public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") long id){
		this.productService.deleteProduct(id);			
		
		return ResponseEntity.ok("OK. Product has been deleted correctly."); 
	}
	
}