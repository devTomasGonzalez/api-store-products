package com.falabella.products.api.service;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falabella.products.api.exceptions.BadRequestException;
import com.falabella.products.api.exceptions.NotFoundException;
import com.falabella.products.api.exceptions.SkuExistException;
import com.falabella.products.api.model.dto.ProductDTO;
import com.falabella.products.api.model.entity.Product;
import com.falabella.products.api.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements IProductService {

	private static final Logger LOG = Logger.getLogger(ProductServiceImpl.class.getName());

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public Product getProductBySku(String sku) {
		Product product = this.productRepository.getProductBySku(sku);
		
		if (product==null) {
			LOG.debug("Product with SKU '" + sku + "' not found.");
			throw new NotFoundException("Product with SKU '" + sku + "' not found.");
		}
		
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> productsList = this.productRepository.findAll();

		return productsList;
	}

	@Override
	public Product createProduct(ProductDTO productDTO) {
		if (skuIsValid(productDTO.getSku())) {
			if (this.productRepository.getProductBySku(productDTO.getSku()) == null) {
				Product product = objectMapper.convertValue(productDTO, Product.class);
				Product productCreated = this.productRepository.save(product);
				
				return productCreated;				
			} else {
				LOG.debug("Product with SKU '" + productDTO.getSku() + "' exist.");
				throw new SkuExistException("Product with SKU '" + productDTO.getSku() + "' exist.");
			}
		} else {
			throw new BadRequestException("SKU value must be min FAL-1000000 and max FAL-99999999");
		}
	}

	@Override
	public Product updateProduct(long id, ProductDTO productDTO) {
		if (skuIsValid(productDTO.getSku())) {			

			Optional<Product> product = this.productRepository.findById(id);
			if (product.isPresent()) {
				Product _product = product.get();

				//Validate Sku if value change is detected
				if (!_product.getSku().equals(productDTO.getSku())) {
					if (this.productRepository.getProductBySku(productDTO.getSku()) != null) {
						LOG.debug("Product with SKU '" + productDTO.getSku() + "' exist.");
						throw new SkuExistException("Product with SKU '" + productDTO.getSku() + "' exist.");
					}
					
				}
				_product.getImageList().clear();
				
				_product.setImage(productDTO.getImage());
				_product.setImageList(productDTO.getImageList());
				_product.setBrand(productDTO.getBrand());
				_product.setName(productDTO.getName());
				_product.setPrice(productDTO.getPrice());
				_product.setSize(productDTO.getSize());
				_product.setSku(productDTO.getSku());
				Product productModified = productRepository.save(_product);
				return productModified;
			} else {
				LOG.debug("Product with ID " + id + " not found.");
				throw new NotFoundException("Product with ID '" + id + "' not found.");			
			}
		} else {
			throw new BadRequestException("SKU value must be start with 'FAL-', followed by a numeric value between 1000000 and 99999999");
		}
	}

	@Override
	public void deleteProduct(long id) {
		try {
			this.productRepository.deleteById(id);
		} catch (Exception e) {
			LOG.debug("Product with ID " + id + " not found.");
			throw new NotFoundException("Product with ID '" + id + "' not found.");
		}
	}
	
	private boolean skuIsValid(String sku) {
		int index = sku.indexOf("FAL-");
		if (index > -1) {
			try {
				long valueFrom = Long.parseLong(sku.substring(4, sku.length()));
				if (valueFrom >= 1000000 && valueFrom <= 99999999)
					return true;			
			}catch(NumberFormatException e) {
				return false;
			}
		}
		return false;
		
	}
	
}
