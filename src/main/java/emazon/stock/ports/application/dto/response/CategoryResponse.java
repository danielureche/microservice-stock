package emazon.stock.ports.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Category response object")
public class CategoryResponse {

    @Schema(description = "Category ID", example = "1")
    private Long id;

    @Schema(description = "Category name", example = "Clothing", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Description of category", example = "Apparel and fashion accessories",  requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
}
