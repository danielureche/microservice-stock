package emazon.stock.domain.model.usecase;

import emazon.stock.domain.model.Article;
import emazon.stock.domain.ports.output.IArticlePersistencePort;
import emazon.stock.domain.ports.output.IBrandPersistencePort;
import emazon.stock.domain.ports.output.ICategoryPersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePortMock;

    @Mock
    private ICategoryPersistencePort categoryPersistencePortMock;

    @Mock
    private IBrandPersistencePort brandPersistencePortMock;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    public ArticleUseCaseTest() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void createArticleTest() {
        // Arrange: Preparar el artículo con categorías y marca válidas
        Article articleMock = mock(Article.class);
        when(articleMock.getCategoriesIds()).thenReturn(Arrays.asList(1L, 2L)); // 2 categorías
        when(articleMock.getBrandId()).thenReturn(1L); // 1 marca

        // Configurar el mock para que las categorías y la marca existan
        when(categoryPersistencePortMock.existsCategory(1L)).thenReturn(true);
        when(categoryPersistencePortMock.existsCategory(2L)).thenReturn(true);
        when(brandPersistencePortMock.existsBrand(1L)).thenReturn(true);

        // Act: Ejecutar el método bajo prueba
        articleUseCase.createArticle(articleMock);

        // Assert: Verificar que los métodos correctos fueron llamados
        assertAll("Verificación de métodos",
                () -> verify(categoryPersistencePortMock, times(2)).existsCategory(anyLong()), // Verifica que se verificaron ambas categorías
                () -> verify(brandPersistencePortMock).existsBrand(1L), // Verifica que se verificó la marca
                () -> verify(articlePersistencePortMock).createArticle(articleMock) // Verifica que se creó el artículo
        );
    }

}
