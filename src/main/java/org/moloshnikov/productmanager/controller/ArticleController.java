package org.moloshnikov.productmanager.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.moloshnikov.productmanager.controller.order.ArticleOrder;
import org.moloshnikov.productmanager.model.Article;
import org.moloshnikov.productmanager.model.specification.ArticleSpecification;
import org.moloshnikov.productmanager.repository.ArticleRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.moloshnikov.productmanager.util.ValidationUtil.*;

@RestController
@RequestMapping(value = ArticleController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {
    static final String REST_URL = "/articles";
    private final String NULL_VALUE = "article must not be null";

    private final ArticleRepo articleRepo;

    public ArticleController(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @GetMapping("/{id}")
    public Article get(@PathVariable int id) {
        return articleRepo.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        boolean deleted = articleRepo.deleteById(id) != 0;
        checkNotFoundWithId(deleted, id);
    }

    @PostMapping
    @JsonView(View.Public.class)
    public ResponseEntity<Article> create(@RequestBody @Valid Article article) {
        Assert.notNull(article, NULL_VALUE);
        checkNew(article);
        article.setCreated(LocalDate.now());

        Article created = articleRepo.save(article);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Article article, @PathVariable int id) {
        assureIdConsistent(article, id);
        Assert.notNull(article, NULL_VALUE);
        Article articleFromDB = articleRepo.getById(id);
        BeanUtils.copyProperties(article, articleFromDB, "id", "created", "product");
        articleRepo.save(articleFromDB);
    }

    @GetMapping
    public List<Article> getAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "productId", required = false) Integer productId,
            @RequestParam(value = "order", required = false, defaultValue = "ID") ArticleOrder order) {
        Specification<Article> specification = ArticleSpecification.getSpecification(name, startDate, endDate, productId);
        return articleRepo.findAll(specification, Sort.by(order.getFieldName()));
    }
}
