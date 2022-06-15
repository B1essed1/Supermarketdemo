package shakh.supermarketdemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.Category;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.dto.ProductAddDto;
import shakh.supermarketdemo.dto.ProductVisualisationDto;
import shakh.supermarketdemo.exceptions.AlreadyExistException;
import shakh.supermarketdemo.exceptions.ProductNotFoundException;
import shakh.supermarketdemo.service.CategoryService;
import shakh.supermarketdemo.service.ProductService;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Set;


@RestController
@Slf4j
@RequestMapping("/api")
public class ProductController {

    private final ProductService service;
    private final CategoryService categoryService;

    public ProductController(ProductService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }


    @GetMapping("/product/all")
    public ResponseEntity<List<ProductVisualisationDto>> getAllProducts() {
        List<ProductVisualisationDto> products = service.getAllProduct();
        if (products.isEmpty()) throw
                new ProductNotFoundException(" Nothing found att all in Products ");

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable("id") Long id) {
        Product product = service.getProductById(id);
        if (product == null)
            return (ResponseEntity<Product>) ResponseEntity.notFound();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product/save")
    public ResponseEntity<Product> addProduct(@RequestBody ProductAddDto prod) {
        var isExist = service.checkProductByBarcode(prod.getBarcode());

        if (!isExist) {

            Product product = new Product();
            product.setAmount(prod.getAmount());
            product.setProductName(prod.getProductName());
            product.setBarcode(prod.getBarcode());
            product.setCreatedTime(new Date());
            product.setIsActive(true);
            product.setMeasureType(prod.getMeasureType());
            Category category = new Category() ;
            category.setCategory(prod.getCategory());

            categoryService.save(category);
            product.setCategories(category);

            service.save(product);

            if (product == null) throw new ProductNotFoundException("Something wrong with request ");

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(product.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else throw new AlreadyExistException("mahsulot tizimga avval kiritilgan," +
                " yangi mahsulot qo'shish uchun qo'shish funksiyasidan foydalaning ");
    }

    @GetMapping("/product/barcode/{barcode}")
    public ResponseEntity<ProductVisualisationDto> getFromBarcode(@PathVariable("barcode") int barcode) {
        ProductVisualisationDto product = service.findByBarcode(barcode);
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
        List<ProductVisualisationDto> productsList = service.getProductByFewAndAlert();
        return ResponseEntity.ok(productsList);
    }

}
