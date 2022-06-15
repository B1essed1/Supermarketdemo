package shakh.supermarketdemo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.Unload;
import shakh.supermarketdemo.dto.UnloadDto;
import shakh.supermarketdemo.exceptions.UnloadNotFoundException;
import shakh.supermarketdemo.service.ProductService;
import shakh.supermarketdemo.service.UnloadService;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UnloadController {

    private final ProductService productService;
    private final UnloadService unloadService;

    public UnloadController(ProductService productService, UnloadService unloadService) {
        this.productService = productService;
        this.unloadService = unloadService;
    }

    @GetMapping("/unload/{id}")
    public ResponseEntity<Unload> getUnload(@PathVariable("id") Long id) {
        Unload takenUnload = unloadService.getById(id);

        if (takenUnload == null)
            throw new UnloadNotFoundException("id--->" + id);
        return new ResponseEntity<>(takenUnload, HttpStatus.OK);
    }

    @GetMapping("/unload/all")
    public ResponseEntity<Set<Unload>> getAll() {
        Set<Unload> takeAllUnload = unloadService.getAll();
        if (takeAllUnload.isEmpty())
            throw new UnloadNotFoundException(" ##########  Unloads not found at all");

        return new ResponseEntity<>(takeAllUnload, HttpStatus.OK);
    }


    @PostMapping("/unload/save")
    public ResponseEntity<Unload> saveOnlyUnload(@RequestBody UnloadDto unloadDto) {
        Unload unloaded = unloadService.unloadProduct(unloadDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(unloaded.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
