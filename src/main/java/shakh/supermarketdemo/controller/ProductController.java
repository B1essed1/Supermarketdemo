package shakh.supermarketdemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.Unload;
import shakh.supermarketdemo.exceptions.ProductNotFoundException;
import shakh.supermarketdemo.exceptions.UnloadNotFoundException;
import shakh.supermarketdemo.service.ProductService;
import shakh.supermarketdemo.service.UnloadService;

import java.net.URI;
import java.util.Set;


@RestController
@Slf4j
@RequestMapping("/api")
public class ProductController
{
    @Autowired
    private ProductService service;
    @Autowired
    private UnloadService unloadService;


    @GetMapping("/product/all")
    public ResponseEntity<Set<Product>> getAllproducts()
    {
        Set<Product> products =service.getAllProduct();
        if (products.isEmpty()) throw
                new ProductNotFoundException("########### Nothing found att all in Products ");

        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable("id") Long id)
    {
        Product product = service.findById(id);
        if (product ==  null)
            throw new ProductNotFoundException("id--->"+id);
        return new  ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product/save")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        Product product1 = service.save(product);

        if (product1 == null) throw new ProductNotFoundException("Something wrong with request ");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product1.getBarcode())
                .toUri();
        return ResponseEntity.created(location).build();
    }

      @GetMapping("/product/barcode/{barcode}")
        public ResponseEntity<Product> getFromBarcode(@PathVariable("barcode") int barcode)
        {
            Product product = service.findByBarcode(barcode);
            return new  ResponseEntity<>(product,HttpStatus.OK);
        }

    @PutMapping("product/update")
    public ResponseEntity<Product> updateAndSaveUnload(@RequestBody Product product)
    {
        Unload unloads =  unloadService.getUnloadsById(product.getId());
        Product takenProduct =  product;

        log.info("#####id--------------> "+ unloads.getId());
        log.info("#####product--------------> "+ takenProduct.getQuantity());
        log.info("#####unload--------------> "+ unloads.getAmount());
        log.info("##### unloadId------------>"+ unloads.getId());

        if (unloads == null) throw new UnloadNotFoundException("unload could not find by id in Update ");

        takenProduct.setQuantity(takenProduct.getQuantity() + unloads.getAmount());
        takenProduct.setLastUpdatedTime(unloads.getCreatedTime());
        takenProduct.setPriceOfBuy(unloads.getPriceOfBuy());

        log.info("####productquantity------------>" + takenProduct.getQuantity());

        service.saveUpdate(takenProduct);

        URI location  = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(takenProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
