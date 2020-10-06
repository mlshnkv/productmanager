package org.moloshnikov.productmanager.repository;

import com.sun.istack.Nullable;
import org.moloshnikov.productmanager.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    @Override
    @EntityGraph(attributePaths = {"articles"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Product> findAll(@Nullable Specification<Product>productSpecification, Sort sort);

    @EntityGraph(attributePaths = {"articles"}, type = EntityGraph.EntityGraphType.LOAD)
    Product getById(int id);
}
