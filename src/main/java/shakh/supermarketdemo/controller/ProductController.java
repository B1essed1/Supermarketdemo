package shakh.supermarketdemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.exceptions.AlreadyExistException;
import shakh.supermarketdemo.exceptions.ProductNotFoundException;
import shakh.supermarketdemo.service.ProductService;

import java.net.URI;
import java.util.List;
import java.util.Set;


@RestController
@Slf4j
@RequestMapping("/api")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    @GetMapping("/product/all")
    public ResponseEntity<Set<Product>> getAllProducts() {
        Set<Product> products = service.getAllProduct();
        if (products.isEmpty()) throw
                new ProductNotFoundException("########### Nothing found att all in Products ");

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable("id") Long id) {
        Product product = service.getProductById(id);
        if (product == null)
            throw new ProductNotFoundException("id--->" + id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product/save")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        var isExist = service.checkProductByBarcode(product.getBarcode());

        if (!isExist) {
            Product product1 = service.save(product);

            if (product1 == null) throw new ProductNotFoundException("Something wrong with request ");

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(product1.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else throw new AlreadyExistException("maxsulot allaqachon bazada bor," +
                " yana qoshish un qoshish funksiyasidan foydalaning ");
    }

    @GetMapping("/product/barcode/{barcode}")
    public ResponseEntity<Product> getFromBarcode(@PathVariable("barcode") int barcode) {
        Product product = service.findByBarcode(barcode);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Long> hardDeleteById(@PathVariable("id") Long id) {
        service.hardDeleteProductById(id);

        Product isDeleted = service.findById(id);

        if (isDeleted == null) return ResponseEntity.ok(id);

        else throw new RuntimeException("Not deleted");
    }

    @Scheduled(cron= "0 15 8,13,20 * * *")
    @GetMapping("product/alert")
    public ResponseEntity<?> alertFewProducts(){
        List<Product> productsList = service.getProductByFewAndAlert();
        return ResponseEntity.ok(productsList);
    }

}
