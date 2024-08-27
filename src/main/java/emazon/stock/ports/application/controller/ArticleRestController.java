package emazon.stock.ports.application.controller;

import emazon.stock.domain.ports.input.IArticleServicePort;
import emazon.stock.ports.application.dto.request.ArticleRequest;
import emazon.stock.ports.application.dto.request.BrandRequest;
import emazon.stock.ports.application.mapper.request.IArticleRequestMapper;
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
public class ArticleRestController {

    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper articleRequestMapper;

    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody ArticleRequest articleRequest){
        articleServicePort.createArticle(articleRequestMapper.toArticle(articleRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}