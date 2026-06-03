package objectmethod.it.limited_drop_ecommerce.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.dtos.response.TrolleyDto;
import objectmethod.it.limited_drop_ecommerce.security.CustomDetails;
import objectmethod.it.limited_drop_ecommerce.services.TrolleyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carousels")
@FieldDefaults (makeFinal = true , level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TrolleyController {
    TrolleyService trolleyService;

    @PostMapping("/{productId}")
    public ResponseEntity<TrolleyDto> addProductToCarousel(@PathVariable("productId") String productId) {
        CustomDetails authenticatedUser = (CustomDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TrolleyDto res = trolleyService.addToCarousel(productId , authenticatedUser.getId());
        return ResponseEntity.ok(res);
    }
}
