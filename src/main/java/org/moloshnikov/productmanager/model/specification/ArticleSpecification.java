package org.moloshnikov.productmanager.model.specification;

import org.moloshnikov.productmanager.model.Article;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ArticleSpecification {
    public static Specification<Article> getSpecification(String name,
                                                          LocalDate startDate,
                                                          LocalDate endDate) {
        return Specification.where(ArticleSpecification.articlesByName(name)
                .and(ArticleSpecification.articlesByDate(startDate, endDate)));
    }

    public static Specification<Article> articlesByName(String name) {
        return (r, q, cb) -> name == null ? null : cb.like(r.get("name"), "%" + name + "%");
    }

    public static Specification<Article> articlesByDate(LocalDate start, LocalDate end) {
        return (r, q, cb) -> {
            if (start == null && end == null) return null;
            if (start == null) return cb.lessThanOrEqualTo(r.get("created"), end);
            if (end == null) return cb.greaterThanOrEqualTo(r.get("created"), start);
            return cb.between(r.get("created"), start, end);
        };
    }
}
