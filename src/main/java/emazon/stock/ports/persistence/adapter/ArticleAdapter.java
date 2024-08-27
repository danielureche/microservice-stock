package emazon.stock.ports.persistence.adapter;

import emazon.stock.domain.model.Article;
import emazon.stock.domain.ports.output.IArticlePersistencePort;
import emazon.stock.ports.persistence.mapper.IArticleEntityMapper;
import emazon.stock.ports.persistence.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;


    @Override
    public void createArticle(Article article) {
        articleRepository.save(articleEntityMapper.toEntity(article));
    }
}
