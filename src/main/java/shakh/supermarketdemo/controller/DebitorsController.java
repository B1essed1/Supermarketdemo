package shakh.supermarketdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.Debitors;
import shakh.supermarketdemo.dto.PersonVisualisationDto;
import shakh.supermarketdemo.service.DebitorService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/debitors/")
public class DebitorsController {

    private final DebitorService debitorService;

    public DebitorsController(DebitorService debitorService) {
        this.debitorService = debitorService;
    }

    @PostMapping("save")
    public ResponseEntity<Debitors> addDebitors(@RequestBody PersonVisualisationDto debitors) {
        Debitors debitor = debitorService.save(debitors);
        if (debitors == null) throw new RuntimeException("cannot save debitors");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(debitor.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Debitors> getDebitorsById(@PathVariable("id") Long id) {
        Debitors debitor = debitorService.getDebitorById(id);
        return ResponseEntity.ok(debitor);
    }

    @GetMapping("get/all")
    public ResponseEntity<List<PersonVisualisationDto>> getAllDebitors() {
        List<PersonVisualisationDto> debitors = debitorService.getAllDebitors();
        return ResponseEntity.ok(debitors);
    }
}
