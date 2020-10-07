package org.moloshnikov.productmanager.model;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraph(name = "product-entity-graph", attributeNodes = {@NamedAttributeNode("articles")})
@Entity
@Table(name = "product")
public class Product extends AbstractBaseEntity {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price")
    private int price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)//
    private List<Article> articles;

    public Product() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}