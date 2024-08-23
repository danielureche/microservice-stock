package emazon.stock.ports.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "category")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String description;
}
