package jpv.digitalinovation.personapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jpv.digitalinovation.personapi.dto.MessageResponseDTO;
import jpv.digitalinovation.personapi.dto.PersonDTO;
import jpv.digitalinovation.personapi.services.PersonService;

@RestController
@RequestMapping ("/api/v1/people")
public class PersonController {

	
	
	@Autowired 
	private PersonService personService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
		
		return personService.createPerson(personDTO);
				
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<PersonDTO> listAll(){
		return personService.listAll();
	}

}
