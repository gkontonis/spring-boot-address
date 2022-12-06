package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.PlaceDTO;
import at.bmlv.test.demo.rest.service.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping(value = "/place")
    public ResponseEntity<List<PlaceDTO>> findAllPlaces() {
        return ResponseEntity.ok(placeService.findAll()); }


    @DeleteMapping(value = "/place/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeService.deletePlaceByID(id);
        return ResponseEntity.ok().build();
    }
}
