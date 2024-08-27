package emazon.stock.ports.persistence.mapper;

import emazon.stock.domain.model.Article;
import emazon.stock.ports.persistence.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IArticleEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "amountArticles", source = "amountArticles")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "idBrand", source = "idBrand")
    @Mapping(target = "idsCategories", source = "idsCategories")
    ArticleEntity toEntity(Article article);
}
