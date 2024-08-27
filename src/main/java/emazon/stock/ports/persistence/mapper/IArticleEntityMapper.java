package emazon.stock.ports.persistence.mapper;

import emazon.stock.domain.model.Article;
import emazon.stock.ports.persistence.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

//@Mapper(componentModel = "spring")
public interface IArticleEntityMapper {
    ArticleEntity toEntity(Article article);
}
