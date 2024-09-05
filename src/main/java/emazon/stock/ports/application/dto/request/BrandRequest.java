package emazon.stock.ports.application.dto.request;

import emazon.stock.ports.application.utils.BrandValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Brand request object")
public class BrandRequest {

    @NotBlank(message = BrandValidationConstants.BRAND_NAME_REQUIRED)
    @Size(max = 50, message = BrandValidationConstants.BRAND_NAME_LENGTH)
    @Schema(description = "Brand name", example = "Microsoft Corporation", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = BrandValidationConstants.BRAND_DESCRIPTION_REQUIRED)
    @Size(max = 120, message = BrandValidationConstants.BRAND_DESCRIPTION_LENGTH)
    @Schema(description = "Brand description", example = "A multinational technology company producing computer software.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
}
