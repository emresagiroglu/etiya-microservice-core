package io.github.emresagiroglu.exception.handler;


import io.github.emresagiroglu.exception.detail.BusinessProblemDetails;
import io.github.emresagiroglu.exception.detail.ValidationProblemDetails;
import io.github.emresagiroglu.exception.type.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler({BusinessException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public BusinessProblemDetails handleBusinessException(BusinessException exception) {

    BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
    businessProblemDetails.setDetail(exception.getMessage());
    return businessProblemDetails;
  }

  @ExceptionHandler({ MethodArgumentNotValidException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {

    Map<String,String> validationErrors = new HashMap<>();

    exception.getBindingResult().getFieldErrors().stream().map(error ->
      validationErrors.put(error.getField(),error.getDefaultMessage())
    ).toList();

    ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
    validationProblemDetails.setErrors(validationErrors);
    return validationProblemDetails;
  }
//
//  @ExceptionHandler(RuntimeException.class)
//  public ResponseEntity<ErrorMessage> handleRunTimeException(RuntimeException ex) {
//
//    ex.printStackTrace();
//
//    log.error(ex.toString());
//
//    return new ResponseEntity<>(createError(ErrorType.UNEXPECTED_ERROR, ex, ex.getMessage()), HttpStatus.BAD_REQUEST);
//  }
//  @ExceptionHandler(UserManagerException.class)
//  public ResponseEntity<ErrorMessage> handleManagerException(UserManagerException exception) {
//
//    ErrorType errorType = exception.getErrorType();
//
//    HttpStatus httpStatus = errorType.getHttpStatus();
//
//    ErrorMessage errorMessage = createError(errorType, exception);
//
//    errorMessage.setMessage(exception.getMessage());
//
//    return new ResponseEntity<>(errorMessage, httpStatus);
//  }
//  @ExceptionHandler(HttpMessageNotReadableException.class)
//  public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(HttpMessageNotReadableException exception) {
//
//    ErrorType errorType = ErrorType.BAD_REQUEST;
//
//    return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
//  }
//  @ExceptionHandler(InvalidFormatException.class)
//  public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
//    InvalidFormatException exception) {
//
//    ErrorType errorType = ErrorType.BAD_REQUEST;
//
//    return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
//  }
}
