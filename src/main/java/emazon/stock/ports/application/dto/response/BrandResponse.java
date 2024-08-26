package emazon.stock.ports.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Brand response object")
public class BrandResponse {

    @Schema(description = "Brand ID", example = "1")
    private Long id;

    @Schema(description = "Brand name", example = "Microsoft Corporation", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Brand description", example = "A multinational technology company producing computer software.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
}
