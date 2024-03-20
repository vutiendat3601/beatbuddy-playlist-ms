package vn.io.datvutech.beatbuddy.playlist.exception;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.TypeMismatchException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.servlet.http.HttpServletRequest;
import vn.io.datvutech.beatbuddy.playlist.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return ResponseEntity.badRequest().body(null);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> handleInvalidFormatException(
            InvalidFormatException e) {

        return ResponseEntity.badRequest().body(null);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatchException(TypeMismatchException e) {
        return ResponseEntity.badRequest().body(null);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleHandlerMethodArgumentNotValid(
            ResourceNotFoundException e,
            HttpServletRequest req) {
        ErrorResponseDto errorRespDto = new ErrorResponseDto(
                req.getServletPath(),
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                ZonedDateTime.now());
        return new ResponseEntity<>(errorRespDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e,
            HttpServletRequest req

    ) {
        final Map<String, List<String>> validationErrors = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fe -> {
            String fieldName = fe.getField();
            List<String> messages = Optional.ofNullable(validationErrors.get(fieldName))
                    .orElseGet(() -> {
                        List<String> newMessages = new LinkedList<>();
                        validationErrors.put(fieldName, newMessages);
                        return newMessages;
                    });
            messages.add(fe.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(validationErrors);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<?> handleHandlerMethodArgumentNotValid(
            HandlerMethodValidationException e,
            HttpServletRequest req

    ) {
        final Map<String, String[]> validationErrors = new HashMap<>();
        List<ParameterValidationResult> valiationResults = e.getValueResults();
        valiationResults.forEach(vr -> {
            MethodParameter param = vr.getMethodParameter();
            String messages[] = vr.getResolvableErrors().stream().map(re -> re.getDefaultMessage())
                    .toArray(String[]::new);
            validationErrors.put(param.getParameterName(), messages);
        });

        return ResponseEntity.badRequest().body(validationErrors);
    }

    @ExceptionHandler(MethodValidationException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(
            MethodValidationException e,
            HttpServletRequest req

    ) {
        final Map<String, String[]> validationErrors = new HashMap<>();
        List<ParameterValidationResult> valiationResults = e.getValueResults();
        valiationResults.forEach(vr -> {
            MethodParameter param = vr.getMethodParameter();
            String messages[] = vr.getResolvableErrors().stream().map(re -> re.getDefaultMessage())
                    .toArray(String[]::new);
            validationErrors.put(param.getParameterName(), messages);
        });

        return ResponseEntity.badRequest().body(validationErrors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            RuntimeException e,
            HttpServletRequest req

    ) {
        ErrorResponseDto errorRespDto = new ErrorResponseDto(
                req.getServletPath(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                ZonedDateTime.now());
        return new ResponseEntity<>(errorRespDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
