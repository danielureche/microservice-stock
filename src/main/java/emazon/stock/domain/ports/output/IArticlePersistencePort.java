package emazon.stock.domain.ports.output;

import emazon.stock.domain.model.Article;

public interface IArticlePersistencePort {
    void createArticle(Article article);
}
