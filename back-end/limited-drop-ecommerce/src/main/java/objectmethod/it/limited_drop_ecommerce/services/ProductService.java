package objectmethod.it.limited_drop_ecommerce.services;

import objectmethod.it.limited_drop_ecommerce.dtos.response.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto viewProductDetails(String productId);
}
