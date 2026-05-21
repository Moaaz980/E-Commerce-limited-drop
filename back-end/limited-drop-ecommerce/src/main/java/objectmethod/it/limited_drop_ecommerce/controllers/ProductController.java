package objectmethod.it.limited_drop_ecommerce.controllers;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.dtos.request.ProductCreationRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.ProductDto;
import objectmethod.it.limited_drop_ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductController {
    ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> allProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> viewDetailsProduct(@PathVariable("id") String productId) {
        return ResponseEntity.ok(productService.viewProductDetails(productId));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> productCreation(@Valid @RequestBody ProductCreationRequestDto productCreationRequestDto) {
        ProductDto res = productService.createProduct(productCreationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

}
