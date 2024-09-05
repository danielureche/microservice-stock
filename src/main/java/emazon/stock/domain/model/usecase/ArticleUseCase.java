package emazon.stock.domain.model.usecase;

import emazon.stock.configuration.exception.BrandNotFoundException;
import emazon.stock.configuration.exception.CategoryNotFoundException;
import emazon.stock.domain.model.Article;
import emazon.stock.domain.ports.input.IArticleServicePort;
import emazon.stock.domain.ports.output.IArticlePersistencePort;
import emazon.stock.domain.ports.output.IBrandPersistencePort;
import emazon.stock.domain.ports.output.ICategoryPersistencePort;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort,
                          ICategoryPersistencePort categoryPersistencePort,
                          IBrandPersistencePort brandPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void createArticle(Article article) {
        validateCategories(article.getCategoriesIds());
        validateBrand(article.getBrandId());
        articlePersistencePort.createArticle(article);
    }

    public void validateCategories(List<Long> categoryIds){
        Set<Long> uniqueCategoryIds = new HashSet<>(categoryIds);

        for (Long categoryId : categoryIds) {
            if (!categoryPersistencePort.existsCategory(categoryId)) {
                throw new CategoryNotFoundException();
            }
        }
    }

    public void validateBrand(Long brandId) {
        if (!brandPersistencePort.existsBrand(brandId)) {
            throw new BrandNotFoundException();
        }
    }
}
