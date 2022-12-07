package at.bmlv.test.demo.rest.service;

import at.bmlv.test.demo.domain.Place;
import at.bmlv.test.demo.dto.CountryDTO;
import at.bmlv.test.demo.dto.PlaceDTO;
import at.bmlv.test.demo.mapper.PlaceMapper;
import at.bmlv.test.demo.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    private final PlaceMapper placeMapper;

    public PlaceService(PlaceRepository placeRepository, PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    public PlaceDTO create(PlaceDTO placeDTO) {
        return placeMapper.toDTO(placeRepository.save(placeMapper.toEntity(placeDTO)));
    }

    public void update(PlaceDTO placeDTO){
        Place place = placeMapper.toEntity(placeDTO);
        placeRepository.update(
                place.getPlaceName(),
                place.getState(),
                place.getPostcode(),
                place.getCountry(),
                place.getId()
        );
    }
    public List<PlaceDTO> findAll(Pageable page) {
        return placeRepository.findAll().stream().map(placeMapper::toDTO).toList();
    }

    public Optional<PlaceDTO> findById(Long id){
        return placeRepository.findById(id).map(placeMapper::toDTO);
    }
    public List<PlaceDTO> findBySearch(String search, Pageable page){
        return placeRepository.findBySearch(search, page).stream().map(placeMapper::toDTO).toList();
    }
    public void deletePlaceByID(Long id) {
        placeRepository.deleteById(id);
    }
}
