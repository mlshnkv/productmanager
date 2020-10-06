package org.moloshnikov.productmanager.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Product.class, property="name")
public class Product extends AbstractBaseEntity {

    private String description;
    private int price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
//    @JsonManagedReference
//    @JsonIdentityReference
    private List<Article> articles;

    public Product(String description, int price, List<Article> articles) {
        this.description = description;
        this.price = price;
        this.articles = articles;
    }

    public Product(Integer id, @NotBlank @Size(min = 2, max = 100) String name, String description, int price, List<Article> articles) {
        super(id, name);
        this.description = description;
        this.price = price;
        this.articles = articles;
    }

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
