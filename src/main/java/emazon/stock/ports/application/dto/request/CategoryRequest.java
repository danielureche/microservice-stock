package emazon.stock.ports.application.dto.request;

import emazon.stock.ports.application.utils.CategoryValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Category request object")
public class CategoryRequest {

    @NotBlank(message = CategoryValidationConstants.CATEGORY_NAME_REQUIRED)
    @Size(max = 50, message = CategoryValidationConstants.CATEGORY_NAME_LENGTH)
    @Schema(description = "Category name", example = "Clothing", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = CategoryValidationConstants.CATEGORY_DESCRIPTION_REQUIRED)
    @Size(max = 90, message = CategoryValidationConstants.CATEGORY_DESCRIPTION_LENGTH)
    @Schema(description = "Description of category", example = "Apparel and fashion accessories",  requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
}
