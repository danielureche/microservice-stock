package emazon.stock.ports.application.mapper.request;

import emazon.stock.domain.model.Article;
import emazon.stock.ports.application.dto.request.ArticleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IArticleRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "amountArticles", source = "amountArticles")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "categoriesIds", source = "categoriesIds")
    Article toArticle(ArticleRequest articleRequest);
}
