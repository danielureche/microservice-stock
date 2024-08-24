package emazon.stock.ports.application.controller;


import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.input.ICategoryServicePort;
import emazon.stock.domain.util.PaginationUtil;
import emazon.stock.ports.application.dto.CategoryRequest;
import emazon.stock.ports.application.dto.CategoryResponse;
import emazon.stock.ports.application.mapper.ICategoryRequestMapper;
import emazon.stock.ports.application.mapper.ICategoryResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        categoryServicePort.createCategory(categoryRequestMapper.toCategory(categoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Pagination<CategoryResponse>> listCategories(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "1", required = false) int size,
            @RequestParam(defaultValue = "categoryName", required = false) String nameFilter,
            @RequestParam(defaultValue = "true",required = false) boolean isAscending
    ) {
        Pagination<Category> pagination = categoryServicePort.listCategories(new PaginationUtil(size, page, isAscending, nameFilter));
        List<Category> categories = pagination.getContent();

        return ResponseEntity.ok(
                new Pagination<>(
                        pagination.isAscending(),
                        pagination.getCurrentPage(),
                        pagination.getTotalPages(),
                        pagination.getTotalElements(),
                        categoryResponseMapper.toCategoryResponses(categories)
                )
        );
    }
}
