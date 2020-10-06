package org.moloshnikov.productmanager.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "article")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Article.class, property="id")
public class Article extends AbstractBaseEntity {

    private String content;
    private LocalDate created;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
   // @JsonIdentityReference
//    @JsonBackReference
    private Product product;


    public Article(String content, LocalDate created, Product product) {
        this.content = content;
        this.created = created;
        this.product = product;
    }

    public Article(Integer id, @NotBlank @Size(min = 2, max = 100) String name, String content, LocalDate created, Product product) {
        super(id, name);
        this.content = content;
        this.created = created;
        this.product = product;
    }

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
