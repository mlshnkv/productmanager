package org.moloshnikov.productmanager.controller;

import org.moloshnikov.productmanager.controller.order.ProductOrder;
import org.moloshnikov.productmanager.model.Product;
import org.moloshnikov.productmanager.model.specification.ProductSpecification;
import org.moloshnikov.productmanager.repository.ProductRepo;
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
import java.util.List;

import static org.moloshnikov.productmanager.util.ValidationUtil.*;

@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    static final String REST_URL = "/products";
    private final String NULL_VALUE = "product must not be null";

    private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable int id) {
        return productRepo.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        boolean deleted = productRepo.deleteById(id) != 0;
        checkNotFoundWithId(deleted, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
        Assert.notNull(product, NULL_VALUE);
        checkNew(product);

        Product created = productRepo.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Product product, @PathVariable int id) {
        Assert.notNull(product, NULL_VALUE);
        assureIdConsistent(product, id);
        productRepo.save(product);
    }

    @GetMapping
    public List<Product> getAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(value = "order", required = false, defaultValue = "ID") ProductOrder order) {
        Specification<Product> specification = ProductSpecification.getSpecification(name, minPrice, maxPrice);
        return productRepo.findAll(specification, Sort.by(order.getFieldName()));
    }
}