package shakh.supermarketdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class AlreadyExistException extends RuntimeException
{
    public AlreadyExistException(String message) {
        super(message);
    }
}


