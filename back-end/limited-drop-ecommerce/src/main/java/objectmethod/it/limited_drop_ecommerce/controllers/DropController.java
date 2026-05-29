package objectmethod.it.limited_drop_ecommerce.controllers;


import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.dtos.model.DropDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.DropCreationDto;
import objectmethod.it.limited_drop_ecommerce.services.DropService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/drops")
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DropController {
    DropService dropService;

    @PostMapping()
    public ResponseEntity<DropDto> dropCreations(@Valid @RequestBody DropCreationDto dropCreationDto) {
        DropDto res = dropService.createDrop(dropCreationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

}
