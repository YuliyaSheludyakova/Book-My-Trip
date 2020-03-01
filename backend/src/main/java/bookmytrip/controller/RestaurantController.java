package bookmytrip.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import bookmytrip.entity.Restaurant;
import bookmytrip.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip/{city}/restaurants")
public class RestaurantController {
	
	private final RestaurantRepository restaurantRepo;
	
	@GetMapping
	public List<Restaurant> getAll(@PathVariable String city) {
		return restaurantRepo.findByCityOrderByName(city);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(
			@PathVariable Long id,
			@PathVariable String city) {		
		var maybeRestaurant = Optional.of(
				restaurantRepo.findByCityAndId(city, id));
		return ResponseEntity.of(maybeRestaurant);
	}
		
	@GetMapping("/suchen")
	public List<Restaurant> getByName(
			@PathVariable String city, 
			@RequestParam(required = false) String name) {		
		return restaurantRepo.findByCityAndNameOrderByRating(city, name);
	}
	
	@GetMapping("/filter")
	public List<Restaurant> getByFilter(
			@PathVariable String city, 
			@RequestParam(required = false) String cuisine, 
			@RequestParam(required = false) Integer priceLevel,
			@RequestParam(required = false) Integer rating) {		
		List<Restaurant> maybeRestaurants = null;		
		maybeRestaurants = restaurantRepo.filterByCuisine(maybeRestaurants, cuisine, city);
		maybeRestaurants = restaurantRepo.filterByPriceLevel(maybeRestaurants, priceLevel, city);
		maybeRestaurants = restaurantRepo.filterByRating(maybeRestaurants, rating, city);		
		return maybeRestaurants;		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant create(
			@PathVariable String city,
			@RequestBody @Valid Restaurant restaurant) {
		restaurantRepo.setDefaults(restaurant, city, null);
		return restaurantRepo.save(restaurant);
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable Long id,
			@PathVariable String city, 
			@RequestBody @Valid Restaurant restaurant) {		
		restaurantRepo.setDefaults(restaurant, city, id);
		if (restaurantRepo.existsByCityAndId(city, id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		restaurantRepo.save(restaurant);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(
			@PathVariable Long id,
			@PathVariable String city) {		
		if (restaurantRepo.existsByCityAndId(city, id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		restaurantRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
