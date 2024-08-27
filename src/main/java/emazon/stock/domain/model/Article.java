package emazon.stock.domain.model;

import java.util.List;

public class Article {
    private Long id;
    private String name;
    private String description;
    private Integer amountArticles;
    private Double price;
    private Long brandId;
    private List<Long> categoriesIds;

    public Article(Long id, String name, String description, Integer amountArticles,Double price, Long brandId, List<Long> categoriesIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amountArticles = amountArticles;
        this.price = price;
        this.brandId = brandId;
        this.categoriesIds = categoriesIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmountArticle() {
        return amountArticles;
    }

    public void setAmountArticle(Integer amountArticle) {
        this.amountArticles = amountArticle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public List<Long> getCategoriesIds() {
        return categoriesIds;
    }

    public void setIdCategories(List<Long> categoriesIds) {
        this.categoriesIds = categoriesIds;
    }
}
