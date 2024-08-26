package emazon.stock.ports.application.controller;

import emazon.stock.domain.model.Brand;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.input.IBrandServicePort;
import emazon.stock.domain.util.PaginationUtil;
import emazon.stock.ports.application.dto.request.BrandRequest;
import emazon.stock.ports.application.dto.response.BrandResponse;
import emazon.stock.ports.application.mapper.request.IBrandRequestMapper;
import emazon.stock.ports.application.mapper.response.IBrandResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
@Tag(name = "Brand", description = "Brand API")
public class BrandRestController {

    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandServicePort brandServicePort;
    private final IBrandResponseMapper brandResponseMapper;

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

    public ResponseEntity<Pagination<BrandResponse>> listBrands(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "1", required = false) int size,
            @RequestParam(defaultValue = "name", required = false) String nameFilter,
            @RequestParam(defaultValue = "true",required = false) boolean isAscending
    ) {
        Pagination<Brand> pagination = brandServicePort.listBrands(new PaginationUtil(size, page, nameFilter, isAscending));
        List<Brand> brands = pagination.getContent();

        return ResponseEntity.ok(
                new Pagination<>(
                        pagination.isAscending(),
                        pagination.getCurrentPage(),
                        pagination.getTotalPages(),
                        pagination.getTotalElements(),
                        brandResponseMapper.toBrandResponses(brands)
                )
        );
    }

}
