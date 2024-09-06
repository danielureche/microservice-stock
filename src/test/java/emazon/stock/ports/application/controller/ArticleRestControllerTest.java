package emazon.stock.ports.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import emazon.stock.domain.model.Article;
import emazon.stock.domain.ports.input.IArticleServicePort;
import emazon.stock.ports.application.dto.request.ArticleRequest;
import emazon.stock.ports.application.mapper.request.IArticleRequestMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {


    private MockMvc mockMvc;

    @MockBean
    private IArticleRequestMapper articleRequestMapperMock;

    @MockBean
    private IArticleServicePort articleServicePortMock;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach
    public void setUp() {
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController(articleServicePortMock, articleRequestMapperMock))
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (autoCloseableMocks != null) {
            autoCloseableMocks.close();
        }
    }

    @Test
    void createArticle_ShouldReturnCreated_WhenRequestIsValid() throws Exception {
        // Arrange
        Article article = new Article();
        article.setId(1L);
        article.setName("Smart TV");

        ArticleRequest articleRequest = new ArticleRequest(
                "Smart TV",
                "A Smart TV is a television set with integrated Internet connectivity.",
                3,
                2000.0,
                10L,
                List.of(1L, 2L, 3L)
        );

        doReturn(article).when(articleRequestMapperMock).toArticle(articleRequest);
        doNothing().when(articleServicePortMock).createArticle(article);

        String contentStr = new ObjectMapper().writeValueAsString(articleRequest);

        // Act
        ResultActions resultActions = this.mockMvc.perform(post("/articles")
                .content(contentStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isCreated());
    }
}
