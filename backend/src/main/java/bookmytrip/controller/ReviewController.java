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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bookmytrip.entity.Review;
import bookmytrip.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip/{city}/{entries}/{entryId}/bewertungen")
public class ReviewController {

	private final ReviewRepository reviewRepo;

	@GetMapping
	public List<Review> getByEntryId(@PathVariable String city,
			                         @PathVariable String entries,
			                         @PathVariable Long entryId) {
	    return reviewRepo.findBy(city, entries, entryId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable String city,
			                         @PathVariable String entries,
			                         @PathVariable Long entryId,
			                         @PathVariable Long id) {
		var maybeReview = reviewRepo.findBy(city, entries, entryId, id);
		return ResponseEntity.of(maybeReview);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Review create(@RequestBody @Valid Review review) {
		return reviewRepo.saveWithDefaults(review, null);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String city,
			                        @PathVariable String entries,
			                        @PathVariable Long entryId,
			                        @PathVariable Long id,
			                        @RequestBody Review review) {
		boolean exists = reviewRepo.existsBy(city, entries, entryId, id);
		if (exists) {
			reviewRepo.saveWithDefaults(review, id);
			return new ResponseEntity<>(review, HttpStatus.OK);
		}
		return new ResponseEntity<>(review, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String city,
			                        @PathVariable String entries,
			                        @PathVariable Long entryId,
			                        @PathVariable Long id) {
		boolean exists = reviewRepo.existsBy(city, entries, entryId, id);
		if (exists) {
			reviewRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
