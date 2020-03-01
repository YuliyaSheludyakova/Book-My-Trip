package bookmytrip.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import bookmytrip.entity.Hotel;
import bookmytrip.repository.HotelRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip/{city}/hotels")
public class HotelController {
	
	private final HotelRepository hotelRepo;
	
	@GetMapping
	public List<Hotel> getAllByCity(@PathVariable String city) {
		return hotelRepo.findByCityOrderByName(city);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(
			@PathVariable Long id,
			@PathVariable String city) {
		var maybeHotel = Optional.of(
				hotelRepo.findByCityAndId(city, id));
		return ResponseEntity.of(maybeHotel);
	}
	
	@GetMapping("/suchen")
	public List<Hotel> getByName(
			@PathVariable String city, 
			@RequestParam(required = false) String name) {
		return hotelRepo.findByCityAndNameOrderByRating(city, name);
	}
	
	@GetMapping("/filter")
	public List<Hotel> getByFilter(
			@PathVariable String city, 
			@RequestParam(required = false) Boolean breakfastIncl, 
			@RequestParam(required = false) Integer stars,
			@RequestParam(required = false) Integer rating) {		
		List<Hotel> maybeHotels = null;		
		maybeHotels = hotelRepo.filterByBreakfastIncl(maybeHotels, breakfastIncl, city);
		maybeHotels = hotelRepo.filterByStars(maybeHotels, stars, city);
		maybeHotels = hotelRepo.filterByRating(maybeHotels, rating, city);		
		return maybeHotels;		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Hotel create(
			@PathVariable String city,
			@RequestBody @Valid Hotel hotel) {
		hotelRepo.setDefaults(hotel, city, null);
		return hotelRepo.save(hotel);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable Long id,
			@RequestBody @Valid Hotel hotel, 
			@PathVariable String city) {
		hotelRepo.setDefaults(hotel, city, id);
		if (hotelRepo.existsByCityAndId(city, id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		hotelRepo.save(hotel);
		return new ResponseEntity<>(HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(
			@PathVariable Long id,
			@PathVariable String city) {
		if (hotelRepo.existsByCityAndId(city, id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		hotelRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
