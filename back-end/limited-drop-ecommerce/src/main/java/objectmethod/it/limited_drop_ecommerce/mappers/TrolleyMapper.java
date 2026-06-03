package objectmethod.it.limited_drop_ecommerce.mappers;


import objectmethod.it.limited_drop_ecommerce.dtos.response.TrolleyDto;
import objectmethod.it.limited_drop_ecommerce.entities.Trolley;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper (componentModel = "spring" , uses = {UserMapper.class})
public interface TrolleyMapper {

    Trolley toTrolley(TrolleyDto TrolleyDto);
    TrolleyDto toTrolleyDto(Trolley Trolley);
    List <TrolleyDto> toListTrolleyDto(List<Trolley> Trolleys);
    List<Trolley> toListTrolley(List<TrolleyDto> TrolleysDto);
}
