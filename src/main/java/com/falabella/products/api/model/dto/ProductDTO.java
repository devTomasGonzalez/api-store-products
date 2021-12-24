package com.falabella.products.api.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.falabella.products.api.model.entity.ProductImage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value= "NewProductDTO", description = "Class representing the request object to create or update Product.")
public class ProductDTO implements Serializable{
		
	private static final long serialVersionUID = 1L;

	public ProductDTO(@NotEmpty(message = "SKU is required") String sku,
			@NotEmpty(message = "Name is required") @NotBlank String name,
			@NotEmpty(message = "Brand is required") @NotBlank String brand, 
			@NotBlank String size,
			@NotEmpty(message = "Price is required") @NotBlank @Min(value = 1, message = "Amount must be more than 1") @Max(value = 99999999, message = "Amount must be less than 99999999") Long price,
			@NotEmpty(message = "Image is required") @NotBlank String image, 
			List<ProductImage> imageList) {
		super();
		this.sku = sku;
		this.name = name;
		this.brand = brand;
		this.size = size;
		this.price = price;
		this.image = image;
		this.imageList = imageList;
	}

	@NotEmpty(message="SKU is required")
    @ApiModelProperty(notes = "Internal stock-keeping unit. It is the candidate identifier of a product. Min value: FAL-1000000 / Max value: FAL-99999999", dataType = "String", example= "FAL-8406270", required = true, position = 1)
	@Column(name = "sku")
	private String sku;
	
	@NotEmpty(message="Name is required")
	@NotBlank
    @ApiModelProperty(notes = "Short description of the product", dataType = "String", example= "Bicicleta Bal-toro Aro 29", required = true, position = 2)
	@Column(name = "name")
	private String name;

	@NotEmpty(message="Brand is required")
	@NotBlank
    @ApiModelProperty(notes = "Name of the brand", dataType = "String", example= "Jeep", required = true, position = 3)
	@Column(name = "brand")
	private String brand;

	@NotBlank
    @ApiModelProperty(notes = "Size of the product", dataType = "String", example= "ST", required = false, position = 4)
	@Column(name = "size")
	private String size;
	
	@NotNull(message="Price is required")
    @Min(value = 1, message = "Amount must be more than 1")
    @Max(value = 99999999, message = "Amount must be less than 99999999")
    @ApiModelProperty(notes = "Sell price", dataType = "Long", example= "399990.00", required = true, position = 5)
	@Column(name = "price")
	private Long price;

	@NotEmpty(message="Image is required")
	@NotBlank
    @ApiModelProperty(notes = "URL of the principal image of the product", dataType = "String", example= "https://falabella.scene7.com/is/image/Falabella/8406270_1", required = true, position = 6)
	@Column(name = "image")
	private String image;

    @ApiModelProperty(notes = "List of images of the product", dataType = "ProductImage", example= "['url' : 'https://falabella.scene7.com/is/image/Falabella/8406270_1']", required = false, position = 7)
	@Column(name = "imageList")
	private List<ProductImage> imageList = new ArrayList<>();

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ProductImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<ProductImage> imageList) {
		this.imageList = imageList;
	}
}
