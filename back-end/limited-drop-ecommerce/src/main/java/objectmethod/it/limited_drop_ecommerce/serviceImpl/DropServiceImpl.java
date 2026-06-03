package objectmethod.it.limited_drop_ecommerce.serviceImpl;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import objectmethod.it.limited_drop_ecommerce.dtos.response.DropDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.DropCreationDto;
import objectmethod.it.limited_drop_ecommerce.entities.Drop;
import objectmethod.it.limited_drop_ecommerce.exceptions.ApiException;
import objectmethod.it.limited_drop_ecommerce.mappers.DropMapper;
import objectmethod.it.limited_drop_ecommerce.repositories.DropRepository;
import objectmethod.it.limited_drop_ecommerce.services.DropService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE , makeFinal = true)
@Slf4j
public class DropServiceImpl implements DropService {
    DropRepository dropRepository;
    DropMapper dropMapper;

    @Override
    public DropDto createDrop(DropCreationDto dropCreationDto) {
        log.info("Creazione drop");
        dropRepository.findByStartDateTime(dropCreationDto.getStartDateTime()).ifPresent(
                drop -> {
                        log.warn("Creazione drop non andata a buon fine , esiste gia");
                        throw new ApiException("Drop esiste gia" , HttpStatus.CONFLICT);
                }
        );
        Drop dropToCreate = dropMapper.toEntity(dropCreationDto);
        Drop createdDrop = dropRepository.save(dropToCreate);
        log.debug("Drop : {} creato con successo" , createdDrop.getId());
        return dropMapper.toDto(createdDrop);
    }



}
