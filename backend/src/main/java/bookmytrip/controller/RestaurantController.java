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
	public ResponseEntity<?> getById(@PathVariable Long id,
			                         @PathVariable String city) {
		var maybeRestaurant = restaurantRepo.findByCityAndId(city, id);
		return ResponseEntity.of(maybeRestaurant);
	}

	@GetMapping("/suchen")
	public List<Restaurant> getByName(@PathVariable String city,
			                          @RequestParam(required = false) String name) {
		return restaurantRepo.findByCityAndNameOrderByRating(city, name);
	}

	@GetMapping("/filter")
	public List<Restaurant> getByFilter(@PathVariable String city,
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
	public Restaurant create(@PathVariable String city,
			                 @RequestBody @Valid Restaurant restaurant) {
		restaurantRepo.setDefaults(restaurant, city, null);
		return restaurantRepo.save(restaurant);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,
			                        @PathVariable String city,
			                        @RequestBody @Valid Restaurant restaurant) {
		restaurantRepo.setDefaults(restaurant, city, id);
		if (restaurantRepo.existsByCityAndId(city, id)) {
			restaurantRepo.save(restaurant);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id,
			                        @PathVariable String city) {
		if (restaurantRepo.existsByCityAndId(city, id)) {
			restaurantRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
