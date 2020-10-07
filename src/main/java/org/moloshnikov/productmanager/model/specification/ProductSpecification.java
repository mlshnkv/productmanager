package org.moloshnikov.productmanager.model.specification;

import org.moloshnikov.productmanager.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> getSpecification(String name,
                                                          Integer minPrice,
                                                          Integer maxPrice) {
        return Specification.where(ProductSpecification.productsByName(name)
                .and(ProductSpecification.productsByPrice(minPrice, maxPrice)));
    }

    public static Specification<Product> productsByName(String name) {
        return (r, q, cb) -> name == null ? null : cb.like(r.get("name"), "%" + name + "%");
    }

    public static Specification<Product> productsByPrice(Integer min, Integer max) {
        return (r, q, cb) -> {
            if (min == null && max == null) return null;
            if (min == null) return cb.lessThanOrEqualTo(r.get("price"), max);
            if (max == null) return cb.greaterThanOrEqualTo(r.get("price"), min);
            return cb.between(r.get("price"), min, max);
        };
    }
}
