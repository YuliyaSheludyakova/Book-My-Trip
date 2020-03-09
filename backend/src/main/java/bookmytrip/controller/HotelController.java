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

import bookmytrip.entity.Hotel;
import bookmytrip.repository.HotelRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip/{city}/hotels")
public class HotelController {

	private final HotelRepository hotelRepo;

	@GetMapping
	public List<Hotel> getByCity(@PathVariable String city) {
		return hotelRepo.findByCityOrderByName(city);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id,
			                         @PathVariable String city) {
		var maybeHotel = hotelRepo.findByCityAndId(city, id);
		return ResponseEntity.of(maybeHotel);
	}

	@GetMapping("/suchen")
	public List<Hotel> getByName(@PathVariable String city,
			                     @RequestParam(required = false) String name) {
		return hotelRepo.findByCityAndNameOrderByRating(city, name);
	}

	@GetMapping("/filter")
	public List<Hotel> getByFilter(@PathVariable String city,
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
	public Hotel create(@PathVariable String city,
			            @RequestBody @Valid Hotel hotel) {
		hotelRepo.setDefaults(hotel, city, null);
		return hotelRepo.save(hotel);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,
			                        @RequestBody @Valid Hotel hotel,
			                        @PathVariable String city) {
		hotelRepo.setDefaults(hotel, city, id);
		if (hotelRepo.existsByCityAndId(city, id)) {
			hotelRepo.save(hotel);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id,
			                        @PathVariable String city) {
		if (hotelRepo.existsByCityAndId(city, id)) {
			hotelRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
