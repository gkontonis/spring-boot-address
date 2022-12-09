package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Place;
import at.bmlv.test.demo.dto.PlaceDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceMapper implements EntityMapper<PlaceDTO, Place> {

    private final CountryMapper countryMapper;

    public PlaceMapper(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    @Override
    public Place toEntity(PlaceDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Place(dto.getId(), dto.getPlaceName(), dto.getState(), dto.getPostcode(), countryMapper.toEntity(dto.getCountry()));
    }

    @Override
    public PlaceDTO toDTO(Place entity) {
        if (entity == null) {
            return null;
        }
        return new PlaceDTO(entity.getId(), entity.getPlaceName(), entity.getState(), entity.getPostcode(), countryMapper.toDTO(entity.getCountry()));
    }

    @Override
    public List<PlaceDTO> toDTOList(List<Place> entities) {
        if (entities == null) {
            return List.of();
        }

        List<PlaceDTO> dtoList = new ArrayList<>();
        for (Place p : entities) {
            dtoList.add(toDTO(p));
        }
        return dtoList;
    }

    @Override
    public List<Place> toEntityList(List<PlaceDTO> dtoList) {
        if (dtoList == null) {
            return List.of();
        }
        List<Place> entityList = new ArrayList<>();
        return entityList;
    }
}
