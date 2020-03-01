package bookmytrip.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import bookmytrip.entity.*;
import bookmytrip.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip/{city}/{entries}/{entryId}/bewertungen")
public class ReviewController {

	private final ReviewRepository reviewRepo;

	@GetMapping
	public List<Review> getAllByEntry(
			@PathVariable String city,
			@PathVariable String entries,
			@PathVariable Long entryId,
			@RequestParam(required = false) Integer rating) {		
		if (rating != null) {
			return reviewRepo.filterByRating(city, entries, entryId, rating);
		} else {
			return reviewRepo.findAllByCityAndEntryId(city, entries, entryId);
		}	
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> showById(
			@PathVariable String city,
			@PathVariable String entries,
			@PathVariable Long entryId,
			@PathVariable Long id) {
		return ResponseEntity.of(
				reviewRepo.
				findByCityAndEntryIdAndId(city, entries, entryId, id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Review create(@RequestBody @Valid Review review) {		
		review.setId(null);
		return reviewRepo.save(review);		
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable String city,
			@PathVariable String entries,
			@PathVariable Long entryId,
			@PathVariable Long id,
			@RequestBody Review review) {
		review.setId(id);
		if (reviewRepo.existsByCityAndEntryIdAndId(city, entries, entryId, id)) {
			return new ResponseEntity<>(review, HttpStatus.NOT_FOUND);
		}
		reviewRepo.save(review);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(
			@PathVariable String city,
			@PathVariable String entries,
			@PathVariable Long entryId,
			@PathVariable Long id) {
		if (reviewRepo.existsByCityAndEntryIdAndId(city, entries, entryId, id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		reviewRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);		
	}
}
