package emazon.stock.ports.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {
    private String name;
    private String description;
    private Integer amountArticles;
    private Double price;
    private Long brandId;
    private List<Long> categoriesIds;
}
