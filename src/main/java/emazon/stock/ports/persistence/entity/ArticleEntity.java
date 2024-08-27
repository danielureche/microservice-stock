package emazon.stock.ports.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 120)
    private String description;

    @Column(name = "amount_articles", nullable = false)
    private int amountArticles;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity brandId;

    @ManyToMany
    @JoinTable(
            name = "category_article",
            joinColumns = @JoinColumn(name = "id_article"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<CategoryEntity> categoriesIds;

    public List<CategoryEntity> getCategoriesIds() {
        return categoriesIds;
    }

    public void setCategoriesIds(List<CategoryEntity> categoriesIds) {
        this.categoriesIds = categoriesIds;
    }
}
