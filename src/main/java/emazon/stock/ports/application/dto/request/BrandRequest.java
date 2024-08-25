package emazon.stock.ports.application.dto.request;

import emazon.stock.configuration.ExceptionConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequest {

    @NotBlank(message = ExceptionConstants.BRAND_NAME_REQUIRED)
    @Size(max = 50, message = ExceptionConstants.BRAND_NAME_LENGTH)
    private String name;

    @NotBlank(message = ExceptionConstants.BRAND_DESCRIPTION_REQUIRED)
    @Size(max = 120, message = ExceptionConstants.BRAND_DESCRIPTION_LENGTH)
    private String description;
}
