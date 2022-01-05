package shakh.supermarketdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.Unload;
import shakh.supermarketdemo.exceptions.UnloadNotFoundException;
import shakh.supermarketdemo.repository.ProductRepository;
import shakh.supermarketdemo.service.ProductService;
import shakh.supermarketdemo.service.UnloadService;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UnloadController
{

    @Autowired
    private  ProductService service;
    private UnloadService unloadService;

    public UnloadController( UnloadService unloadService) {
        this.unloadService = unloadService;
    }

   /* @PostMapping("/unload")
    public ResponseEntity<Unload> save(@RequestBody Unload unload)
    {
        Unload unload2= unloadService.save(unload);

        LocalDateTime currentTime = LocalDateTime.now();
        List<Product> unload1 = unloadService.findProductById(unload2.getId());
        if (unload1.isEmpty()) throw new RuntimeException(" ####################### Product not found ");

        unload1.get(0).setQuantity(unload1.get(0).getQuantity()+unload.getAmount());
        unload1.get(0).setLastUpdatedTime(currentTime);


        // product id bazadan tekshrish
        // chek if amount > 0
        // product id from barcode

        //making return Uri
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(unload2.getId())
                        .toUri();

       return ResponseEntity.created(location).build();
    }
*/
    @GetMapping("/unload/{id}")
    public ResponseEntity<Unload> getUnload(@PathVariable("id") Long id)
    {
        Unload takenUnload = unloadService.getById(id);

        if(takenUnload==null)
            throw  new UnloadNotFoundException("id--->"+ id);
        return new  ResponseEntity<>(takenUnload, HttpStatus.OK);
    }

    @GetMapping("/unload/all")
    public ResponseEntity<Set<Unload>> getAll()
    {
        Set<Unload> takeAllUnload = unloadService.getAll();
        if (takeAllUnload.isEmpty())
            throw new UnloadNotFoundException(" ##########  Unloads not found at all" );

        return new ResponseEntity<>(takeAllUnload,HttpStatus.OK);
    }


    @PostMapping("/unload/save")
    public ResponseEntity<Unload> saveOnlyUnload(@RequestBody Unload unload)
    {
        Unload unloaded = unloadService.save(unload);
        Product p = service.getProductById(unloaded.getProduct().getId());
  
        p.setQuantity(unload.getAmount()+p.getQuantity());
        p.setPriceOfBuy(unloaded.getPriceOfBuy());
        service.saveUpdate(p);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(unloaded.getId()).toUri();
       return  ResponseEntity.created(location).build();
    }
}
