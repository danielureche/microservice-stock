package emazon.stock.ports.persistence.adapter;

import emazon.stock.domain.model.Article;
import emazon.stock.domain.ports.output.IArticlePersistencePort;
import emazon.stock.ports.persistence.entity.ArticleEntity;
import emazon.stock.ports.persistence.entity.BrandEntity;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import emazon.stock.ports.persistence.mapper.IArticleEntityMapper;
import emazon.stock.ports.persistence.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    public static ArticleEntity modelToEntity(Article article ){
        ArticleEntity article1 = new ArticleEntity();
        article1.setId(article.getId());
        article1.setName(article.getName());
        article1.setDescription(article.getDescription());
        article1.setAmountArticles(article.getAmountArticle());
        article1.setPrice(article.getPrice());
        article1.setBrandId(new BrandEntity(article.getBrandId()));
        List<CategoryEntity> categories = article.getCategoriesIds().stream().map(category->new CategoryEntity(category)).toList();
        article1.setCategoriesIds(categories);
        return article1;
    }

    @Override
    public void createArticle(Article article) {
        articleRepository.save(ArticleAdapter.modelToEntity(article));
    }
}
