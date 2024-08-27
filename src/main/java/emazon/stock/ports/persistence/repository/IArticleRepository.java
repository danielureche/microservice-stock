package emazon.stock.ports.persistence.repository;

import emazon.stock.ports.persistence.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
