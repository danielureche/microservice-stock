package emazon.stock.domain.ports.input;

import emazon.stock.domain.model.Article;
import emazon.stock.domain.model.Brand;
import emazon.stock.domain.model.Category;

import java.util.Set;

public interface IArticleServicePort {
    void createArticle(Article article);
}
