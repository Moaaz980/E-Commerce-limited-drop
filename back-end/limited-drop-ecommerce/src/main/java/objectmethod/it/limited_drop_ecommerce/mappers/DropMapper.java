package objectmethod.it.limited_drop_ecommerce.mappers;

import objectmethod.it.limited_drop_ecommerce.dtos.response.DropDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.DropCreationDto;
import objectmethod.it.limited_drop_ecommerce.entities.Drop;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring" )
public interface DropMapper {

    Drop toEntity(DropDto dropDto);
    DropDto toDto(Drop drop);
    Drop toEntity(DropCreationDto dropCreationDto);
}
