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

    @Column(nullable = false, length = 50)
    private int amountArticles;

    @Column(nullable = false, length = 50)
    private double price;

    @ManyToOne
    @JoinColumn(name = "idBrand")
    private BrandEntity brand;

    @ManyToMany
    @JoinTable(
            name = "category_article",
            joinColumns = @JoinColumn(name = "idArticle"),
            inverseJoinColumns = @JoinColumn(name = "idCategory")
    )
    private List<CategoryEntity> categories;
}
