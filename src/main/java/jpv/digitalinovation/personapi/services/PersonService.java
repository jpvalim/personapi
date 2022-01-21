package jpv.digitalinovation.personapi.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpv.digitalinovation.personapi.dto.MessageResponseDTO;
import jpv.digitalinovation.personapi.dto.PersonDTO;
import jpv.digitalinovation.personapi.entities.Person;
import jpv.digitalinovation.personapi.exceptions.PersonNotFoundException;
import jpv.digitalinovation.personapi.mapper.PersonMapper;
import jpv.digitalinovation.personapi.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	public MessageResponseDTO createPerson(PersonDTO personDTO){
		
		Person personToSave = personMapper.toModel(personDTO);
		Person savedPerson = personRepository.save(personToSave);
		
		
		return createMessageResponse(savedPerson.getId());
				
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream().map(personMapper :: toDTO).collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		return personMapper.toDTO(person);
	}

	public void deleteById(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);
	
	}
	
	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return  personRepository.findById(id).orElseThrow(()->new PersonNotFoundException(id));
	}

	public MessageResponseDTO updateById(Long id, @Valid PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
		
		Person personToUpdate = personMapper.toModel(personDTO);
		Person updatedPerson = personRepository.save(personToUpdate);
		
		
		return createMessageResponse(updatedPerson.getId());
	}

	private MessageResponseDTO createMessageResponse(Long id) {
		return MessageResponseDTO.builder()
				.message("Updated person with ID " + id)
				.build();
	}
	
	
}
