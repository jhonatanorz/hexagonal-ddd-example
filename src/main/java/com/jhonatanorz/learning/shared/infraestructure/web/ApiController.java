package com.jhonatanorz.learning.shared.infraestructure.web;

import com.jhonatanorz.learning.shared.domain.exceptions.ForbiddenException;
import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;
import com.jhonatanorz.learning.shared.domain.exceptions.UnauthorizedException;
import com.jhonatanorz.learning.shared.infraestructure.web.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
public abstract class ApiController extends AbstractController<ResponseEntity> {

    public ApiController(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity onNotFound() {
        return ResponseEntity.notFound().build();
    }

    @Override
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity onBadRequest(IllegalArgumentException ex) {

        log.info("Bad request", ex);

        ErrorResponse details = new ErrorResponse(ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(details);
    }

    @Override
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity onUnauthorized(UnauthorizedException ex) {
        log.warn("Unauthorized", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @Override
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity onForbidden(ForbiddenException ex) {
        log.warn("Forbidden", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity onHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        log.info("Bad request", ex);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity onMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {

        log.info(ex.getMessage(), ex);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity onMissingServletRequestParameterException(MissingServletRequestParameterException ex) {

        log.info(ex.getMessage(), ex);
        return ResponseEntity.badRequest().build();
    }

    @Override
    @ExceptionHandler(Exception.class)
    public ResponseEntity onInternalServerError(Exception ex) {

        log.error("An unexpected error occurred", ex);
        return ResponseEntity.internalServerError().build();
    }

}
