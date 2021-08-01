package com.finartz.restaurantApi.exception;


import com.finartz.restaurantApi.error.BadCredentialError;
import com.finartz.restaurantApi.error.ErrorMessage;
import com.finartz.restaurantApi.error.InternalServerError;
import com.finartz.restaurantApi.error.NotAllowedError;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Locale;

@ControllerAdvice
public class CustomExceptionHandler  {

    private final MessageSource messageSource;

    public CustomExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /*
    exception tüm exceptionların atası olduğu için,en tepede o olduğu için onu alıyorum,ResourceNotFound sınıfımda RuntimeExceptiondan extend olduğu için,
    aslında burada exception değişkenim,resourceNotFound değişkenim oluyor. Oradaki değerleri,ErrorMessage ın propertielerine setliyorum
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundExceptionHandler(Exception exception, WebRequest request) {

        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        /*aşağıda da ,exception.getMessage() dan gelen symbol_name.code ile gelen hata kodunu,
        message.properties dosyamdaki hata koduyla eşleştir ve karşılığındaki değeri döndür diyoruz.
         */
        errors.setError(messageSource.getMessage(exception.getMessage(), null, Locale.getDefault()));
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setCode(exception.getMessage()); //symbol.code
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> generalException(Exception exception, WebRequest request) {
        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(messageSource.getMessage(InternalServerError.INTERNAL_SERVER_ERROR.getMessage(), null, Locale.getDefault()));
        errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errors.setCode(InternalServerError.INTERNAL_SERVER_ERROR.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

    }
/*
    @ExceptionHandler(MyJwtException.class)
    public ResponseEntity<ErrorMessage> tokenException(Exception exception, WebRequest request) {
        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(messageSource.getMessage(exception.getMessage(), null, Locale.getDefault()));
        errors.setStatus(HttpStatus.FORBIDDEN.value());
        errors.setCode(exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);

    }

    //400 Bad Request
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorMessage> tokenNotValid(Exception exception, WebRequest request) {
        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(messageSource.getMessage(JwtError.MALFORMED_EXCEPTION.getMessage(), null, Locale.getDefault()));
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        errors.setCode(JwtError.MALFORMED_EXCEPTION.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //401 Unauthorized HTTP" response
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorMessage> tokenIsExpired(Exception exception, WebRequest request) {
        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(messageSource.getMessage(JwtError.EXPIRED_JWT_EXCEPTION.getMessage(), null, Locale.getDefault()));
        errors.setStatus(HttpStatus.UNAUTHORIZED.value());
        errors.setCode(JwtError.EXPIRED_JWT_EXCEPTION.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }

 */

    //BadCredentials hatası,loginde password hatalı girilince,401 unauthorized
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> badCredentialsException(Exception exception, WebRequest request) {
        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(messageSource.getMessage(BadCredentialError.BAD_CREDENTIAL_ERROR.getMessage(), null, Locale.getDefault()));
        errors.setStatus(HttpStatus.UNAUTHORIZED.value());
        errors.setCode(BadCredentialError.BAD_CREDENTIAL_ERROR.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }

    //403 forbidden-Rolelere göre restaurantları göstermede fırlatılıyor.
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorMessage> notPermission(Exception exception, WebRequest request) {
        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(messageSource.getMessage(NotAllowedError.DO_NOT_HAVE_PERMISSION.getMessage(), null, Locale.getDefault()));
        errors.setStatus(HttpStatus.FORBIDDEN.value());
        errors.setCode(NotAllowedError.DO_NOT_HAVE_PERMISSION.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }

}
