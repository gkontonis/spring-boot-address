package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.PlaceDTO;
import at.bmlv.test.demo.rest.service.PlaceService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PlaceController {
    private static final String ENDPOINT = "/place";
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @Transactional
    @PostMapping(value = "/place")
    public ResponseEntity<PlaceDTO> createPlace(@RequestBody PlaceDTO placeDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI(ENDPOINT)).body(placeService.create(placeDTO));
    }

    @Transactional
    @PutMapping(value = "/place/{id}")
    public ResponseEntity<Void> updatePlace(@PathVariable Long id, @RequestBody PlaceDTO placeDTO) {
        placeService.update(placeDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/place")
    public ResponseEntity<List<PlaceDTO>> findAllPlaces(@RequestParam(required = false) String search, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(placeService.findBySearch(search, PageRequest.of(page, size)));
        }
        return ResponseEntity.ok(placeService.findAll(PageRequest.of(page, size)));
    }


    @GetMapping(value = "/place/{id}")
    public ResponseEntity<PlaceDTO> findPlaceById(@PathVariable Long id) {
        return ResponseEntity.of(placeService.findById(id));
    }

    @Transactional
    @DeleteMapping(value = "/place/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeService.deletePlaceByID(id);
        return ResponseEntity.ok().build();
    }
}
