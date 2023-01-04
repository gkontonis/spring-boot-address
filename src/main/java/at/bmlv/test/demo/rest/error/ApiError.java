package at.bmlv.test.demo.rest.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ApiError implements Serializable {
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }
    public ApiError(HttpStatus httpStatus){
        this();
        this.httpStatus = httpStatus;
    }

    public ApiError( HttpStatus httpStatus, String message ) {
        this();
        this.httpStatus = httpStatus;
        this.message = message;
    }

    /*
    public ApiError( HttpStatus httpStatus, String message, Throwable ex ) {
        this();
        this.httpStatus = httpStatus;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }*/






}
