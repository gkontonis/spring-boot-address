package at.bmlv.test.demo.rest.service;

import at.bmlv.test.demo.dto.AddressDTO;
import at.bmlv.test.demo.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

   public List<AddressDTO> findAll(){
        return addressRepository.findAll().stream().map(
                entity -> new AddressDTO(entity.getId(),entity.getStreetName(), entity.getHouseNumber(), entity.getFlatNumber())
        ).toList();
   }

    public void deleteByAddressID(Long id){addressRepository.deleteById(id);}
}
