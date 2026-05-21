package objectmethod.it.limited_drop_ecommerce.serviceImpl;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import objectmethod.it.limited_drop_ecommerce.dtos.model.DropDto;
import objectmethod.it.limited_drop_ecommerce.mappers.DropMapper;
import objectmethod.it.limited_drop_ecommerce.repositories.DropRepository;
import objectmethod.it.limited_drop_ecommerce.services.DropService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE , makeFinal = true)
@Slf4j
public class DropServiceImpl implements DropService {
    DropRepository dropRepository;
    DropMapper dropMapper;

    @Override
    public DropDto handleDrop(DropDto drop) {

    }
}
