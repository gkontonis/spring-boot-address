package at.bmlv.test.demo.rest.service;

import at.bmlv.test.demo.domain.Country;
import at.bmlv.test.demo.dto.CountryDTO;
import at.bmlv.test.demo.mapper.CountryMapper;
import at.bmlv.test.demo.repository.CountryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//business logik
@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    public CountryDTO create(CountryDTO countryDTO) {
        return countryMapper.toDTO(countryRepository.save(countryMapper.toEntity(countryDTO)));
    }

    public void update(CountryDTO countryDTO) {
        Country country = countryMapper.toEntity(countryDTO);
        countryRepository.update(
                country.getCountryName(),
                country.getCountryNameAbbreviation(),
                country.getId());
    }

    public List<CountryDTO> findAll(Pageable page) {
        return countryRepository.findAll().stream().map(countryMapper::toDTO).toList();
    }

    public Optional<CountryDTO> findById(Long id) {
        return countryRepository.findById(id).map(countryMapper::toDTO);
    }

    public List<CountryDTO> findBySearch(String search, Pageable page) {
        return countryRepository.findBySearch(search, page).stream().map(countryMapper::toDTO).toList();
    }

    public void deleteCountryByID(Long id) {
        countryRepository.deleteById(id);
    }


}
