package emazon.stock.ports.application.controller;

import emazon.stock.domain.ports.input.IArticleServicePort;
import emazon.stock.ports.application.dto.request.ArticleRequest;
import emazon.stock.ports.application.mapper.request.IArticleRequestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
@Tag(name = "Article", description = "Article API")
public class ArticleRestController {

    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper articleRequestMapper;

    @Operation(summary = "Create a new product", description = "Creates a new article in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> createArticle(
            @Parameter(description = "Article request body", required = true)
            @Valid
            @RequestBody ArticleRequest articleRequest){
        articleServicePort.createArticle(articleRequestMapper.toArticle(articleRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}