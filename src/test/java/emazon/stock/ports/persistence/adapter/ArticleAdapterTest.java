package emazon.stock.ports.persistence.adapter;

import emazon.stock.domain.model.Article;
import emazon.stock.ports.persistence.entity.ArticleEntity;
import emazon.stock.ports.persistence.mapper.IArticleEntityMapper;
import emazon.stock.ports.persistence.repository.IArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ArticleAdapterTest {

    @Mock
    private IArticleRepository articleRepositoryMock;

    @Mock
    private IArticleEntityMapper articleEntityMapperMock;

    @InjectMocks
    private ArticleAdapter articleAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createArticle_ShouldCallRepositorySave() {
        // Arrange
        Article article = new Article();
        article.setName("Smart TV");

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setName("Smart TV");

        when(articleEntityMapperMock.toEntity(article)).thenReturn(articleEntity);

        // Act
        articleAdapter.createArticle(article);

        // Assert
        verify(articleRepositoryMock).save(articleEntity);
    }
}
