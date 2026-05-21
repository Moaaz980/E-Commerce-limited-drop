package objectmethod.it.limited_drop_ecommerce.serviceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import objectmethod.it.limited_drop_ecommerce.dtos.request.ProductCreationRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.ProductDto;
import objectmethod.it.limited_drop_ecommerce.entities.Product;
import objectmethod.it.limited_drop_ecommerce.exceptions.ApiException;
import objectmethod.it.limited_drop_ecommerce.mappers.ProductMapper;
import objectmethod.it.limited_drop_ecommerce.repositories.ProductRepository;
import objectmethod.it.limited_drop_ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


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
        log.info("Cerca prodotto");
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            log.warn("Prodotto : {} non trovato" , productId);
            throw new ApiException("Product not found", HttpStatus.NOT_FOUND);
        }
        Product productFound = product.get();
        log.debug("Prodotto : {} trovato" , productId);
        return productMapper.toDto(productFound);
    }

    @Override
//    @Transactional
    public ProductDto createProduct(ProductCreationRequestDto productCreationRequestDto) {
        log.info("Creazione prodotto : {}" , productCreationRequestDto.getName());
        productRepository.findByName(productCreationRequestDto.getName()).ifPresent(
                p -> {
                   throw new ApiException("Prodotto esiste già" , HttpStatus.CONFLICT);
                });
        Product productToCreate = productMapper.toEntity(productCreationRequestDto);
        Product createdProduct = productRepository.save(productToCreate);
        log.debug("Prodotto : {} creato con successo" , createdProduct.getName());
        return productMapper.toDto(createdProduct);
    }




}
