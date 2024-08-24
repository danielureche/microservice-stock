package emazon.stock.ports.application.dto;

import emazon.stock.configuration.ExceptionConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank(message = ExceptionConstants.CATEGORY_NAME_REQUIRED)
    @Size(max = 50, message = ExceptionConstants.CATEGORY_NAME_LENGTH)
    private String name;

    @NotBlank(message = ExceptionConstants.CATEGORY_DESCRIPTION_REQUIRED)
    @Size(max = 90, message = ExceptionConstants.CATEGORY_DESCRIPTION_LENGTH)
    private String description;
}
