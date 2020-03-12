package bookmytrip.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bookmytrip.entity.Museum;
import bookmytrip.repository.MuseumRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip/{city}/museen")
public class MuseumController {

	private final MuseumRepository museumRepo;

	// TODO: city must be changed into a parameter later!
	@GetMapping
	public List<Museum> getByCity(@PathVariable String city) {
		return museumRepo.findByCityOrderByName(city);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id,
			                         @PathVariable String city) {
		var maybeMuseum = museumRepo.findByCityAndId(city, id);
		return ResponseEntity.of(maybeMuseum);
	}

	@GetMapping("/suchen")
	public List<Museum> getByName(@PathVariable String city,
			                      @RequestParam(required = false) String name) {
		return museumRepo.findByCityAndNameOrderByRating(city, name);
	}

	// TODO: filter by museum types must be implemented
	@GetMapping("/filter")
	public List<Museum> getByFilter(@PathVariable String city, MuseumSpecification museumSpec) {
		return museumRepo.filterBySpecification(city, museumSpec);
	}

//	public List<Museum> getByFilter(@PathVariable String city,
//			                        @RequestParam(required = false) String museumType,
//			                        @RequestParam(required = false) Integer priceLevel,
//			                        @RequestParam(required = false) Integer rating) {
//		List<Museum> maybeMuseums = null;
//		maybeMuseums = museumRepo.filterByMuseumType(maybeMuseums, museumType, city);
//		maybeMuseums = museumRepo.filterByPriceLevel(maybeMuseums, priceLevel, city);
//		maybeMuseums = museumRepo.filterByRating(maybeMuseums, rating, city);
//		return maybeMuseums;
//	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Museum create(@PathVariable String city,
			             @RequestBody @Valid Museum museum) {
		return museumRepo.saveWithDefaults(museum, city, null);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String city, @PathVariable Long id,
			                        @RequestBody @Valid Museum museum) {
		boolean exists = museumRepo.existsByCityAndId(city, id);
		if (exists) {
			museumRepo.saveWithDefaults(museum, city, id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id,
			                        @PathVariable String city) {
		boolean exists = museumRepo.existsByCityAndId(city, id);
		if (exists) {
			museumRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
