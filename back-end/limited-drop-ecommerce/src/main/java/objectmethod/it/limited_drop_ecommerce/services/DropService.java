package objectmethod.it.limited_drop_ecommerce.services;

import objectmethod.it.limited_drop_ecommerce.dtos.response.DropDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.DropCreationDto;

public interface DropService {
    DropDto createDrop(DropCreationDto dropCreationDto);
}
