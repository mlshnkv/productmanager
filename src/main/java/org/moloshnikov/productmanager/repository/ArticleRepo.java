package org.moloshnikov.productmanager.repository;

import com.sun.istack.Nullable;
import org.moloshnikov.productmanager.model.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ArticleRepo extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

    @Override
    @EntityGraph(attributePaths = {"product"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Article> findAll(@Nullable Specification<Article> productSpecification, Sort sort);

    @EntityGraph(attributePaths = {"product"}, type = EntityGraph.EntityGraphType.LOAD)
    Article getById(int id);
}
