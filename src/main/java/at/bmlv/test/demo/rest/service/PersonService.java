package at.bmlv.test.demo.rest.service;

import at.bmlv.test.demo.domain.Person;
import at.bmlv.test.demo.dto.PersonDTO;
import at.bmlv.test.demo.mapper.PersonMapper;
import at.bmlv.test.demo.repository.PersonRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public void update(PersonDTO personDTO){
        Person person = personMapper.toEntity(personDTO);
        personRepository.update(
                person.getFirstName(),
                person.getLastName(),
                person.getGender(),
                person.getUuid()
        );
    }

    public List<PersonDTO> findAll(Pageable page) {
        return personRepository.findAll().stream().map(personMapper::toDTO).toList();
    }
    public Optional<PersonDTO> findById(UUID uuid) {
        return personRepository.findById(uuid).map(personMapper::toDTO);
    }

    public List<PersonDTO> findBySearch(String search, Pageable page) {
        return personRepository.findBySearch(search, page).stream().map(personMapper::toDTO).toList();
    }

    public void deletePersonByUUID(UUID uuid) {
        personRepository.deleteById(uuid);
    }
}
