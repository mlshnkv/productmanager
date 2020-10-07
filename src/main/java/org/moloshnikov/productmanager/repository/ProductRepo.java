package org.moloshnikov.productmanager.repository;

import com.sun.istack.Nullable;
import org.moloshnikov.productmanager.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProductRepo extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    @Override
    @EntityGraph(value = "product-entity-graph")
    List<Product> findAll(@Nullable Specification<Product> productSpecification, Sort sort);

    @EntityGraph(value = "product-entity-graph")
    Product getById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.id=:id")
    int deleteById(@Param("id") int id);
}
