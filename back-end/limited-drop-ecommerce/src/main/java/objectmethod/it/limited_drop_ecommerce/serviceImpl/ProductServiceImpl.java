package objectmethod.it.limited_drop_ecommerce.serviceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import objectmethod.it.limited_drop_ecommerce.dtos.response.ProductDto;
import objectmethod.it.limited_drop_ecommerce.entities.Product;
import objectmethod.it.limited_drop_ecommerce.exceptions.ApiException;
import objectmethod.it.limited_drop_ecommerce.mappers.ProductMapper;
import objectmethod.it.limited_drop_ecommerce.repositories.ProductRepository;
import objectmethod.it.limited_drop_ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@Slf4j
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Visualizzazione di tuttti i prodotti per utente autenticato");
        List<Product> products = productRepository.findAll();
        log.debug("Ci sono : {} prodotti" , products.size());
        return productMapper.toListDto(products);
    }

    @Override
    public ProductDto viewProductDetails(String productId) {
        log.info("Cerca prodotto : {}" , productId);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ApiException("Product not found", HttpStatus.NOT_FOUND)
        );
        log.info("Prodotto : {} trovato" , product.getId());
        return productMapper.toDto(product);
    }
}
