package objectmethod.it.limited_drop_ecommerce.services;


import objectmethod.it.limited_drop_ecommerce.dtos.response.TrolleyDto;

public interface TrolleyService {
    TrolleyDto addToCarousel(String productId , String userId);
}
