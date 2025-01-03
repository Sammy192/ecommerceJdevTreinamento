package jdev.mentoria.lojavirtual.controllers.handlers;

import jdev.mentoria.lojavirtual.dtos.CustomErrorDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        CustomErrorDTO customErrorDTO = new CustomErrorDTO();

        String msg = "";

        if (ex instanceof MethodArgumentNotValidException) {

            List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();

            for (ObjectError objectError : list) {
                msg += objectError.getDefaultMessage() + "\n";
            }
        }else {
            msg = ex.getMessage();
        }

        customErrorDTO.setError(msg);
        customErrorDTO.setStatusCode(status.value() + " ==> " + status.getReasonPhrase());

        ex.printStackTrace();

        return new ResponseEntity<>(customErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class,
            ConstraintViolationException.class, SQLException.class})
    protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex){

        CustomErrorDTO customErrorDTO = new CustomErrorDTO();

        String msg = "";

        if (ex instanceof DataIntegrityViolationException) {
            msg = "Erro de integridade no banco: " +  (ex).getCause().getCause().getMessage();
        }else
        if (ex instanceof ConstraintViolationException) {
            msg = "Erro de chave estrangeira: " + (ex).getCause().getCause().getMessage();
        }else
        if (ex instanceof SQLException) {
            msg = "Erro de SQL do Banco: " + (ex).getCause().getCause().getMessage();
        }else {
            msg = ex.getMessage();
        }

        customErrorDTO.setError(msg);
        customErrorDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        ex.printStackTrace();

        return new ResponseEntity<>(customErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
