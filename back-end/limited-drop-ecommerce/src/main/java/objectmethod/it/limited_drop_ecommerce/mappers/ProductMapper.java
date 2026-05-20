package objectmethod.it.limited_drop_ecommerce.mappers;

import objectmethod.it.limited_drop_ecommerce.dtos.response.ProductDto;
import objectmethod.it.limited_drop_ecommerce.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring" , uses = DropMapper.class)
public interface ProductMapper {

    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
    List<ProductDto> toListDto(List<Product> products);
    List<Product> toListProduct(List <ProductDto> productsDto);

}
