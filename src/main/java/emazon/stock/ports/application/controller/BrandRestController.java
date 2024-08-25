package emazon.stock.ports.application.controller;

import emazon.stock.domain.ports.input.IBrandServicePort;
import emazon.stock.ports.application.dto.request.BrandRequest;
import emazon.stock.ports.application.mapper.request.IBrandRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandRestController {

    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandServicePort brandServicePort;

    @PostMapping
    public ResponseEntity<Void> createBrand(@RequestBody BrandRequest brandRequest){
        brandServicePort.createBrand(brandRequestMapper.toBrand(brandRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
