package at.bmlv.test.demo.rest.service;

import at.bmlv.test.demo.dto.PlaceDTO;
import at.bmlv.test.demo.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {this.placeRepository = placeRepository; }

    public List<PlaceDTO> findAll(){
        return placeRepository.findAll().stream().map(
                entity -> new PlaceDTO(entity.getId(), entity.getPlaceName(), entity.getState(), entity.getPostcode())
        ).toList();
    }

    public void deletePlaceByID(Long id) {
        placeRepository.deleteById(id);
    }
}
