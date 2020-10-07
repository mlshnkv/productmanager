package org.moloshnikov.productmanager.repository;

import com.sun.istack.Nullable;
import org.moloshnikov.productmanager.model.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ArticleRepo extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

    @Override
    @EntityGraph(value = "article-entity-graph")
    List<Article> findAll(@Nullable Specification<Article> productSpecification, Sort sort);

    @EntityGraph(value = "article-entity-graph")
    Article getById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Article a WHERE a.id=:id")
    int deleteById(@Param("id") int id);
}
