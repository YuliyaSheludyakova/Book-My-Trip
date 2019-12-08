package bookmytrip.Controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bookmytrip.Entity.Hotel;
import bookmytrip.Repository.HotelRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {
	
	// TODO: create tests
	
	private final HotelRepository hotelRepo; // Changed the name to a shorter one
	
	@GetMapping
	public List<Hotel> index(){
		return hotelRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Optional<Hotel> maybeHotel = hotelRepo.findById(id);
		return ResponseEntity.of(maybeHotel);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Hotel create(@RequestBody @Valid Hotel hotel) {
		hotel.setId(null);
		return hotelRepo.save(hotel);
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Hotel hotel) {
		hotel.setId(null);		
		if (!hotelRepo.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		hotelRepo.save(hotel);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {		
		if (!hotelRepo.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		hotelRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}