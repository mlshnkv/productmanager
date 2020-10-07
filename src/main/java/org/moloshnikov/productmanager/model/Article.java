package org.moloshnikov.productmanager.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.moloshnikov.productmanager.controller.View;

import javax.persistence.*;
import java.time.LocalDate;

@NamedEntityGraph(name = "article-entity-graph", attributeNodes = {@NamedAttributeNode("product")})
@Entity
@Table(name = "article")
public class Article extends AbstractBaseEntity {

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created", updatable = false)
    private LocalDate created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonView(View.Internal.class)
    private Product product;

    public Article() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
