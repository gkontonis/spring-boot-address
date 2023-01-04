package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Country;
import at.bmlv.test.demo.dto.CountryDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CountryMapper implements EntityMapper<CountryDTO, Country> {

    //CountryMapper countryMapper;
    @Override
    public Country toEntity(CountryDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Country(dto.getId(), dto.getCountryName(), dto.getCountryNameAbbreviation());
    }

    @Override
    public CountryDTO toDTO(Country entity) {
        if (entity == null) {
            return null;
        }
        return new CountryDTO(entity.getId(), entity.getCountryName(), entity.getCountryNameAbbreviation());
    }

    @Override
    public List<CountryDTO> toDTOList(List<Country> entities) {
        if (entities == null) {
            return List.of();
        }
        List<CountryDTO> dtoList = new ArrayList<>();
        for (Country country : entities) {
            dtoList.add(toDTO(country));
        }
        return dtoList;
    }

    @Override
    public List<Country> toEntityList(List<CountryDTO> dtoList) {
        if (dtoList == null) {
            return List.of();
        }
        List<Country> countryList = new ArrayList<>();
        for (CountryDTO countryDTO : dtoList) {
            countryList.add(toEntity(countryDTO));
        }
        return countryList;
    }
}
