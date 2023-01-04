package at.bmlv.test.demo.rest.exception;

import at.bmlv.test.demo.rest.error.ApiError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle person not found
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(PersonNotFoundException.class)
    protected ResponseEntity<Object> handlePersonNotFound(PersonNotFoundException ex){
        ApiError apiError = new ApiError( UNAUTHORIZED, "Person '" + ex.getName()+ "' not found" );
        return buildResponseEntity( apiError );
    }

    /**
     * Handle entity not found
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler( EntityNotFoundException.class )
    protected ResponseEntity<Object> handleEntityNotFound( EntityNotFoundException ex ) {
        ApiError apiError = new ApiError( NOT_FOUND, ex.getMessage() );
        return buildResponseEntity( apiError );
    }

    /**
     * Handle Argument type mismatch
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler( MethodArgumentTypeMismatchException.class )
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch( MethodArgumentTypeMismatchException ex, WebRequest request ) {
        Class<?> type = ex.getRequiredType();
        String required = ( type != null ) ? type.getSimpleName() : "-";

        ApiError apiError = new ApiError( BAD_REQUEST );
        apiError.setMessage( String.format( "The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), required ) );
        apiError.setDebugMessage( ex.getMessage() );
        return buildResponseEntity( apiError );
    }

    private ResponseEntity<Object> buildResponseEntity( ApiError apiError ) {
        return new ResponseEntity<>( apiError, apiError.getHttpStatus() );
    }
}
