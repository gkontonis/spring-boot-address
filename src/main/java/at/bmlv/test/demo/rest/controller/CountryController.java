package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.CountryDTO;
import at.bmlv.test.demo.mapper.CountryMapper;
import at.bmlv.test.demo.rest.service.CountryService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CountryController {

    private static final String ENDPOINT = "/country";
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(value = "/country")
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI(ENDPOINT)).body(countryService.create(countryDTO));
    }

    @Transactional
    @PutMapping(value = "/country/{id}")
    public ResponseEntity<Void> updateCountry(@PathVariable Long id, @RequestBody CountryDTO countryDTO) {
        countryService.update(countryDTO);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/country")
    public List<CountryDTO> findAllCountries(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "50", required = false) int size
    ) {
        if (search != null && !search.isBlank()){
            return countryService.findBySearch(search, PageRequest.of(page, size));
        }
            return countryService.findAll(PageRequest.of(page, size));
    }

    @GetMapping(value = "/country/{id}")
    public ResponseEntity<CountryDTO> findCountryById(@PathVariable Long id){
        return ResponseEntity.of(
                countryService.findById(id)
        );
    }

    @DeleteMapping(value = "/country/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountryByID(id);
        return ResponseEntity.ok().build();
    }
}
