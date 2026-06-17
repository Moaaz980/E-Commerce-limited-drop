package objectmethod.it.limited_drop_ecommerce.serviceImpl;


import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import objectmethod.it.limited_drop_ecommerce.dtos.response.TrolleyDto;
import objectmethod.it.limited_drop_ecommerce.entities.Product;
import objectmethod.it.limited_drop_ecommerce.entities.Trolley;
import objectmethod.it.limited_drop_ecommerce.entities.TrolleyProduct;
import objectmethod.it.limited_drop_ecommerce.entities.User;
import objectmethod.it.limited_drop_ecommerce.exceptions.ApiException;
import objectmethod.it.limited_drop_ecommerce.mappers.TrolleyMapper;
import objectmethod.it.limited_drop_ecommerce.repositories.ProductRepository;
import objectmethod.it.limited_drop_ecommerce.repositories.TrolleyProductRepository;
import objectmethod.it.limited_drop_ecommerce.repositories.TrolleyRepository;
import objectmethod.it.limited_drop_ecommerce.repositories.UserRepository;
import objectmethod.it.limited_drop_ecommerce.services.TrolleyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE , makeFinal = true)
@Slf4j
public class TrolleyServiceImpl implements TrolleyService {
    TrolleyRepository trolleyRepository;
    TrolleyMapper trolleyMapper;
    UserRepository userRepository;
    ProductRepository productRepository;
    TrolleyProductRepository trolleyProductRepository;

    @Override
    @Transactional
    public TrolleyDto addToCarousel(String productId , String userId) {
        log.info("Tentativo di aggiunta del prodotto al carrello");
        Trolley trolley;
        List<TrolleyProduct> trolleyItems = new ArrayList<>();
        Product productToAdd = productRepository.findById(productId).orElseThrow(
                () -> {
                    log.warn("Prodotto : {} non trovato" , productId);
                    throw new ApiException("Prodotto non trovato" , HttpStatus.NOT_FOUND);
                }
        );

        boolean validToPurchase = productRepository.isPossibileToPurchase(productToAdd.getId());

        if (validToPurchase) {
            log.info("Prodotto valido per l'acquisto");
            trolley = userTrolley(userId);
            int totalTrolleyItems = getTotalItemsForTrolley(trolley.getId());
            trolley.setTotalItems(totalTrolleyItems);
            TrolleyProduct trolleyProduct = setProductToTrolley(productToAdd , trolley);
            trolleyItems.add(trolleyProduct);
            trolley.setProducts(trolleyItems);
        }
        else {
            log.warn("Prodotto non valido per aggiunta carrello");
            throw new ApiException("Aggiunta carrello fallita , prodotto non disponibile" , HttpStatus.BAD_REQUEST);
        }
        TrolleyDto trolleyDto = trolleyMapper.toTrolleyDto(trolley);
        log.debug("user id : {}" , trolleyDto.getUserId());
        return trolleyDto;
    }


    // metodo per associare un prodotto al carello tramite la tabella ponte
    private TrolleyProduct setProductToTrolley(Product product , Trolley trolley) {
        log.info("Mettere il  prodotto: {} nel carrello: {}" , product.getId() , trolley.getId());
        TrolleyProduct trolleyProduct = new TrolleyProduct();
        trolleyProduct.setProduct(product);
        trolleyProduct.setTrolley(trolley);
        TrolleyProduct productAddedToCarousel = trolleyProductRepository.save(trolleyProduct);
        log.info("Prodotto aggiunto con successo");
        return productAddedToCarousel;
    }

    // metodo che mi recupera il carrello dello user autenticato cosi evito di crearlo più volte
    private Trolley userTrolley(String userId) {
        log.info("Recupero carrello dello user se non c'è lo creo se c'è lo uso: {}" , userId);
        Trolley trolley = trolleyRepository.findByUserId(userId).orElseGet(
                () -> createTrolley(userId)
        );
        return trolley;
    }


    // Metodo per creare il carrello quando utente aggiunge prodotto (Lazy creation)
    private Trolley createTrolley(String userId) {
        log.info("Crezione carrello per utente : {}" , userId);
        User utente = userRepository.findById(userId).orElseThrow(
                () -> new ApiException("Utente non trovato", HttpStatus.NOT_FOUND)
        );
        Trolley trolley = new Trolley();
        trolley.setTotalItems(0);
        trolley.setUser(utente);
        Trolley createdTrolley = trolleyRepository.save(trolley);
        log.info("Carrello creato con successo");
        return createdTrolley;
    }

    // metodo che mi restituisce tutti i prodotti presenti in quel carrello
    private int getTotalItemsForTrolley(String trolleyId) {
        log.info("Conteggio prodtti per carrello : {}" , trolleyId);
        int totalItemsForTrolley = trolleyProductRepository.countProductByTrolleyId(trolleyId);
        log.debug("Prodotti presenti in questo carrello sono : {}" , totalItemsForTrolley);
        return totalItemsForTrolley;
    }



    // Da migliorare la struttura del codice e l'architettura










}
