package emazon.stock.ports.application.dto.request;

import emazon.stock.ports.application.utils.ArticleValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Article request object")
public class ArticleRequest {

    @NotBlank(message = ArticleValidationConstants.ARTICLE_NAME_REQUIRED)
    @Schema(description = "Article name", example = "Smart TV", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = ArticleValidationConstants.ARTICLE_DESCRIPTION_REQUIRED)
    @Schema(description = "Article description", example = "A Smart TV is a television set with integrated Internet connectivity.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @NotNull(message = ArticleValidationConstants.ARTICLE_QUANTITY_REQUIRED)
    @Min(value = ArticleValidationConstants.ARTICLE_QUANTITY_MIN_VALUE, message = ArticleValidationConstants.ARTICLE_QUANTITY_MIN)
    @Max(value = ArticleValidationConstants.ARTICLE_QUANTITY_MAX_VALUE, message = ArticleValidationConstants.ARTICLE_QUANTITY_MAX)
    @Schema(description = "Article quantity", example = "3")
    private int amountArticles;

    @NotNull(message = ArticleValidationConstants.ARTICLE_PRICE_REQUIRED)
    @Positive(message = ArticleValidationConstants.ARTICLE_PRICE_POSITIVE)
    @Schema(description = "Article price", example = "2000")
    private Double price;

    @NotNull(message = ArticleValidationConstants.ARTICLE_BRAND_REQUIRED)
    @Positive(message = ArticleValidationConstants.ARTICLE_BRAND_ID_POSITIVE)
    @Schema(description = "Brand ID", example = "10")
    private Long brandId;

    @NotEmpty(message = ArticleValidationConstants.ARTICLE_CATEGORY_REQUIRED)
    @Size(min = ArticleValidationConstants.ARTICLE_CATEGORY_MIN_SIZE, max = ArticleValidationConstants.ARTICLE_CATEGORY_MAX_SIZE, message = ArticleValidationConstants.ARTICLE_CATEGORY_SIZE)
    @UniqueElements(message = ArticleValidationConstants.DUPLICATE_CATEGORY)
    @Schema(description = "List of category IDs", example = "[1, 2, 3]")
    private List<Long> categoriesIds;
}
