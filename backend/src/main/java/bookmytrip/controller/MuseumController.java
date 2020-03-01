package bookmytrip.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import bookmytrip.entity.Museum;
import bookmytrip.repository.MuseumRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip/{city}/museen")
public class MuseumController {
	
	private final MuseumRepository museumRepo;
	
	@GetMapping
	public List<Museum> getAllByCity(@PathVariable String city) {
		return museumRepo.findByCityOrderByName(city);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(
			@PathVariable Long id,
			@PathVariable String city) {
		var maybeMuseums = Optional.of(
				museumRepo.findByCityAndId(city, id));
		return ResponseEntity.of(maybeMuseums);
	}
	
	@GetMapping("/suchen")
	public List<Museum> getByName(
			@PathVariable String city, 
			@RequestParam(required = false) String name) {
		return museumRepo.findByCityAndNameOrderByRating(city, name);
	}
	
	@GetMapping("/filter")
	public List<Museum> getByFilter(
			@PathVariable String city, 
			@RequestParam(required = false) String museumType, 
			@RequestParam(required = false) Integer priceLevel,
			@RequestParam(required = false) Integer rating) {		
		List<Museum> maybeMuseums = null;	
		maybeMuseums = museumRepo.filterByMuseumType(maybeMuseums, museumType, city);
		maybeMuseums = museumRepo.filterByPriceLevel(maybeMuseums, priceLevel, city);
		maybeMuseums = museumRepo.filterByRating(maybeMuseums, rating, city);
		return maybeMuseums;		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Museum create(
			@PathVariable String city,
			@RequestBody @Valid Museum museum) {
		museumRepo.setDefaults(museum, city, null);
		return museumRepo.save(museum);
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable Long id,
			@RequestBody @Valid Museum museum, 
			@PathVariable String city) {
		museumRepo.setDefaults(museum, city, id);
		if (museumRepo.existsByCityAndId(city, id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		museumRepo.save(museum);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(
			@PathVariable Long id,
			@PathVariable String city) {
		if (museumRepo.existsByCityAndId(city, id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		museumRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
