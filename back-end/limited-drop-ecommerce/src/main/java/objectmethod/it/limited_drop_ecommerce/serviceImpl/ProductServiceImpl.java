package objectmethod.it.limited_drop_ecommerce.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import objectmethod.it.limited_drop_ecommerce.dtos.request.ProductCreationRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.ProductUpdateDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.ProductDto;
import objectmethod.it.limited_drop_ecommerce.entities.Drop;
import objectmethod.it.limited_drop_ecommerce.entities.Product;
import objectmethod.it.limited_drop_ecommerce.exceptions.ApiException;
import objectmethod.it.limited_drop_ecommerce.mappers.ProductMapper;
import objectmethod.it.limited_drop_ecommerce.repositories.DropRepository;
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
    DropRepository dropRepository;

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
    @Transactional
    public ProductDto createProduct(ProductCreationRequestDto productCreationRequestDto) {
        log.info("Creazione prodotto : {}" , productCreationRequestDto.getName());
        Drop drop = getDrop(productCreationRequestDto.getDropId());
        productRepository.findByName(productCreationRequestDto.getName()).ifPresent(
                p -> {
                   throw new ApiException("Prodotto esiste già" , HttpStatus.CONFLICT);
                });
        Product productToCreate = productMapper.toEntity(productCreationRequestDto);
        productToCreate.setDrop(drop);
        Product createdProduct = productRepository.save(productToCreate);
        log.debug("Prodotto : {} creato con successo" , createdProduct.getName());
        return productMapper.toDto(createdProduct);
    }

    @Override
    public ProductDto updateProduct(String productId , ProductUpdateDto productUpdateDto) {
        log.info("Aggiornamento prodotto : {}" , productId);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> {
                    log.warn("Impossibile aggiornare prodotto : {} , non esiste" , productId);
                    throw new ApiException("Prodotto non trovato" , HttpStatus.NOT_FOUND);
                }
        );
        productMapper.UpdateProductDtoToProduct(productUpdateDto , product);
        Product productUpdated = productRepository.save(product);
        log.debug("Prodotto aggionato con successo : {}" , productUpdated.getId());
        return productMapper.toDto(productUpdated);
    }

    @Override
    public void deleteProduct(String productId) {
        log.info("Cancellazione prodotto : {}" , productId);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> {
                    log.warn("Cancellazione prodotto non andata a buon fine , prodotto : {} non esiste" , productId);
                    throw new ApiException("Prodotto non trovato" , HttpStatus.NOT_FOUND);
                }
        );
        productRepository.delete(product);
        log.info("Cancellazione prodotto : {} andata con successo" , productId);
    }

    @Override
    public boolean isItAvailableForPurchase(String productId) {
        log.info("Controllo prodotto : {} , se è disponibile" , productId);
        boolean isValid =  productRepository.isPossibileToPurchase(productId);
        if (!isValid) {
            log.warn("Non è possibile acquistare questo prodotto non disponibile");
            throw new ApiException("Prodotto non disponibile", HttpStatus.BAD_REQUEST);
        }
        log.debug("Prodotto : {}  è acquistabile : {}" , productId ,  true);
        return isValid;
    }


    private Drop getDrop(String dropId) {
        log.info("Recupero drop : {}" , dropId);
        Drop drop = dropRepository.findById(dropId).orElseThrow(
                () -> {
                    log.warn("Errore recupero drop : {}" , dropId);
                    throw new ApiException("Drop non esiste" , HttpStatus.NOT_FOUND);
                }
        );
        log.info("Drop : {} recuperato con successo" , dropId);
        return drop;
    }






}
