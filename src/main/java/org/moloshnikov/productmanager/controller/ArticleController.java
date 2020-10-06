package org.moloshnikov.productmanager.controller;

import org.moloshnikov.productmanager.model.Article;
import org.moloshnikov.productmanager.model.specification.ArticleSpecification;
import org.moloshnikov.productmanager.repository.ArticleRepo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ArticleController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {
    static final String REST_URL = "/articles";

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
        articleRepo.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody Article article) {
        Article created = articleRepo.save(article);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Article article, @PathVariable int id) {
        Article updated = articleRepo.save(article);
    }

    @GetMapping
    public List<Article> getAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "order", required = false, defaultValue = "ID") ArticleOrder order) {
        Specification<Article> specification = ArticleSpecification.getSpecification(name, startDate, endDate);

        return articleRepo.findAll(specification, Sort.by(order.getFieldName()));
    }
}
