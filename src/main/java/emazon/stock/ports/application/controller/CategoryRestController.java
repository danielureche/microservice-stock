package emazon.stock.ports.application.controller;


import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.input.ICategoryServicePort;
import emazon.stock.domain.util.PaginationUtil;
import emazon.stock.ports.application.dto.CategoryRequest;
import emazon.stock.ports.application.dto.CategoryResponse;
import emazon.stock.ports.application.mapper.ICategoryRequestMapper;
import emazon.stock.ports.application.mapper.ICategoryResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Category API")
public class CategoryRestController {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Operation(
            summary = "Create a new category",
            description = "Creates a new category based on the provided category request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        categoryServicePort.createCategory(categoryRequestMapper.toCategory(categoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Get all categories paginated",
            description = "Retrieves a paginated list of categories based on query parameters.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of categories retrieved successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid query parameters", content = @Content)
            },
            parameters = {
                    @Parameter(name = "page", description = "Page number for pagination", example = "1"),
                    @Parameter(name = "size", description = "Number of categories per page", example = "10"),
                    @Parameter(name = "nameFilter", description = "Filter by category name", example = "name"),
                    @Parameter(name = "isAscending", description = "Sort order, ascending or descending", example = "true")
            }
    )
    @GetMapping
    public ResponseEntity<Pagination<CategoryResponse>> listCategories(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "1", required = false) int size,
            @RequestParam(defaultValue = "name", required = false) String nameFilter,
            @RequestParam(defaultValue = "true",required = false) boolean isAscending
    ) {
        Pagination<Category> pagination = categoryServicePort.listCategories(new PaginationUtil(size, page, nameFilter, isAscending));
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
