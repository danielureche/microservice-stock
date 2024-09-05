package emazon.stock.ports.persistence.mapper;

import emazon.stock.domain.model.Article;
import emazon.stock.ports.persistence.entity.ArticleEntity;
import emazon.stock.ports.persistence.entity.BrandEntity;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IArticleEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "amountArticles", source = "amountArticles")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "categoriesIds", source = "categoriesIds")
    ArticleEntity toEntity(Article article);

    default Long map(BrandEntity brand) {
        return brand != null ? brand.getId() : null;
    }

    default BrandEntity map(Long brandId) {
        if (brandId == null) {
            return null;
        }
        BrandEntity brand = new BrandEntity();
        brand.setId(brandId);
        return brand;
    }

    default List<Long> map(List<CategoryEntity> categories) {
        if (categories == null) {
            return null;
        }
        return categories.stream()
                .map(CategoryEntity::getId)
                .collect(Collectors.toList());
    }

    default List<CategoryEntity> mapFromIds(List<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(id -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setId(id);
                    return category;
                })
                .collect(Collectors.toList());
    }

}
