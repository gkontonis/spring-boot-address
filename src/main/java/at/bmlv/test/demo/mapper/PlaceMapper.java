package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Place;
import at.bmlv.test.demo.dto.PlaceDTO;

import java.util.List;

public class PlaceMapper implements EntityMapper<PlaceDTO, Place> {

    private final CountryMapper countryMapper;

    public PlaceMapper(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    @Override
    public Place toEntity(PlaceDTO dto) {
        return new Place(dto.getId(), dto.getPlaceName(), dto.getState(), dto.getPostcode(), countryMapper.toEntity(dto.getCountry()));
    }

    @Override
    public PlaceDTO toDTO(Place entity) {
        return null;
    }

    @Override
    public List<PlaceDTO> toDTOList(List<Place> entities) {
        return null;
    }

    @Override
    public List<Place> toEntityList(List<PlaceDTO> dtoList) {
        return null;
    }
}
