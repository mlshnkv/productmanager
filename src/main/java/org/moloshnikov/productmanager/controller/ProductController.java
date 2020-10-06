package org.moloshnikov.productmanager.controller;

import org.moloshnikov.productmanager.model.Product;
import org.moloshnikov.productmanager.model.specification.ProductSpecification;
import org.moloshnikov.productmanager.repository.ProductRepo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    static final String REST_URL = "/products";

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
        productRepo.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product created = productRepo.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Product product, @PathVariable int id) {
        Product updated = productRepo.save(product);
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
