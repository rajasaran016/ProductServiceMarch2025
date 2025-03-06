package com.brainstormapp.productservicemarch2025.advise;

import com.brainstormapp.productservicemarch2025.DTO.ErrorDTO;
import com.brainstormapp.productservicemarch2025.exceptions.ProductListIsEmptyException;
import com.brainstormapp.productservicemarch2025.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("400");
        errorDTO.setErrorMessage("Illegal argument");

        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("404");
        errorDTO.setErrorMessage("Product Not found");

        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(ProductListIsEmptyException.class)
    public ResponseEntity<ErrorDTO> handleProductListIsEmptyException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("404");
        errorDTO.setErrorMessage("Product List is empty");

        return ResponseEntity.badRequest().body(errorDTO);
    }

}
