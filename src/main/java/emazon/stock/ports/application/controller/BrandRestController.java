package emazon.stock.ports.application.controller;

import emazon.stock.domain.ports.input.IBrandServicePort;
import emazon.stock.ports.application.dto.request.BrandRequest;
import emazon.stock.ports.application.mapper.request.IBrandRequestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Brand", description = "Brand API")
public class BrandRestController {

    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandServicePort brandServicePort;

    @Operation(
            summary = "Create a new brand",
            description = "Creates a new brand based on the provided category request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "409", description = "Brand already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> createBrand(@Valid @RequestBody BrandRequest brandRequest){
        brandServicePort.createBrand(brandRequestMapper.toBrand(brandRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
