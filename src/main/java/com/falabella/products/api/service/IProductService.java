package com.falabella.products.api.service;

import java.util.List;

import com.falabella.products.api.exceptions.BadRequestException;
import com.falabella.products.api.exceptions.NotFoundException;
import com.falabella.products.api.model.dto.ProductDTO;
import com.falabella.products.api.model.entity.Product;

public interface IProductService {

	public Product getProductBySku(String sku) throws NotFoundException;

	public List<Product> getAllProducts() throws NotFoundException;

	public Product createProduct(ProductDTO productDTO) throws BadRequestException;

	public Product updateProduct(long id, ProductDTO productDTO) throws NotFoundException;

	public void deleteProduct(long id) throws NotFoundException;
}
