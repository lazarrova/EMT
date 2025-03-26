package mk.ukim.finki.emt.rent_room_application.web;


import mk.ukim.finki.emt.rent_room_application.model.Housing;
import mk.ukim.finki.emt.rent_room_application.model.dto.HousingDto;
import mk.ukim.finki.emt.rent_room_application.service.HousingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housings")
public class HousingController {

    private final HousingService housingService;

    public HousingController(HousingService housingService) {
        this.housingService = housingService;
    }

    @GetMapping
    public List<Housing> findAll() {
        return this.housingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Housing> findById(@PathVariable Long id) {
        return this.housingService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
//  @Operation(summary = "Adds a new book")
    public ResponseEntity<Housing> save(@RequestBody HousingDto accommodation) {
        return housingService.save(accommodation).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
//    @Operation(summary = "Updates a book record")
    public ResponseEntity<Housing> update(@PathVariable Long id, @RequestBody HousingDto accommodation) {
        return housingService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<Housing> markAsRented(@PathVariable Long id) {
        return housingService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    //@Operation(summary = "Deletes a book by its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (housingService.findById(id).isPresent()) {
            housingService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
