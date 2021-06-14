package com.finartz.restaurantApi.exception;


import com.finartz.restaurantApi.error.ErrorMessage;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public CustomExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /*
    exception tüm exceptionların atası olduğu için,en tepede o olduğu için onu alıyorum,ResourceNotFound sınıfımda RuntimeExceptiondan extend olduğu için,
    aslında burada exception değişkenim,resourceNotFound değişkenim oluyor. Oradaki değerleri,ErrorMessage ın propertielerine setliyorum
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> exception(Exception exception, WebRequest request) {
        // return new ResponseEntity<>("Kullanıcı bulunamadı..", HttpStatus.INTERNAL_SERVER_ERROR);

        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        /*aşağıda da ,exception.getMessage() dan gelen symbol_name.code ile gelen hata kodunu,
        message.properties dosyamdaki hata koduyla eşleştir ve karşılığındaki değeri döndür diyoruz.
         */
        errors.setError(messageSource.getMessage(exception.getMessage(),null, Locale.getDefault()));
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setCode(exception.getMessage()); //symbol.code

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }


}
