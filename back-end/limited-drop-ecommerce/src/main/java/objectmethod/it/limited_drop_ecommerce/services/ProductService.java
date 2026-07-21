package objectmethod.it.limited_drop_ecommerce.services;

import objectmethod.it.limited_drop_ecommerce.dtos.request.ProductCreationRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.ProductUpdateDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.AvailabilityDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.ProductDto;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto viewProductDetails(String productId);
    ProductDto createProduct(ProductCreationRequestDto productCreationRequestDto);
    ProductDto updateProduct(String productId , ProductUpdateDto productUpdateDto);
    void deleteProduct(String productId);
    AvailabilityDto isItAvailableForPurchase(String productId);
}
