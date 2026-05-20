package objectmethod.it.limited_drop_ecommerce.exceptions.handling;


import objectmethod.it.limited_drop_ecommerce.dtos.response.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDto>> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<ErrorResponseDto> validationErrorResponses = new ArrayList<>();

        for (FieldError fieldErr : fieldErrors) {
            ErrorResponseDto errRes = new ErrorResponseDto();
            errRes.setField(fieldErr.getField());
            errRes.setMessage(fieldErr.getDefaultMessage());
            validationErrorResponses.add(errRes);
        }
        return ResponseEntity.badRequest().body(validationErrorResponses);
    }

}
