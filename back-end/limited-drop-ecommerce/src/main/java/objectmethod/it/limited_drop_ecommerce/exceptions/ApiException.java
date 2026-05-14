package objectmethod.it.limited_drop_ecommerce.exceptions;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class ApiException extends RuntimeException {
    HttpStatus status;
    public ApiException(String message , HttpStatus status) {
        super(message);
        this.status = status;
    }
}
