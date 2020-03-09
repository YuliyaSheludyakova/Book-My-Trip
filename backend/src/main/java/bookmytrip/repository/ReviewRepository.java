package bookmytrip.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import bookmytrip.entity.Hotel;
import bookmytrip.entity.Museum;
import bookmytrip.entity.Restaurant;
import bookmytrip.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	default List<Review> findAllByCityAndEntryId(
			String city, String entries, Long entryId) {
		return findAll().stream()
				.filter(r -> isMatchByCity(r, city))
				.filter(r -> entryTypeMatches(entries, r))
				.filter(r -> isMatchByEntryId(r, entryId))
				//.sorted((r1, r2) -> compareById(r1, r2))
				.collect(Collectors.toList());
	}

	private boolean isMatchByCity(Review review, String city) {
		return review.getEntry().getCity().getName().equals(city);
	}

	private boolean entryTypeMatches(String entries, Review review) {
		switch (entries) {
		case "restaurants":
			return review.getEntry() instanceof Restaurant;
		case "hotels":
			return review.getEntry() instanceof Hotel;
		case "museen":
			return review.getEntry() instanceof Museum;
		default:
			return false;
		}
	}

	private boolean isMatchByEntryId(Review review, Long id) {
		return review.getEntry().getId().equals(id);
	}

	//	private int compareById(Review firstReview, Review secondReview) {
	//		return firstReview.getId().compareTo(secondReview.getId());
	//	}

	default List<Review> filterByRating(
			String city, String entries, Long entryId, Integer minRequiredRating) {
		return findAllByCityAndEntryId(city, entries, entryId).stream()
				.filter(r -> isMinRequiredRating(r, minRequiredRating))
				//.sorted((r1, r2) -> compareById(r1, r2))
				.collect(Collectors.toList());
	}

	private boolean isMinRequiredRating(Review review, int minRequiredRating) {
		return review.getRating() >= minRequiredRating;
	}

	default Optional<Review> findByCityAndEntryIdAndId(
			String city, String entries, Long entryId, Long id) {
		return findAllByCityAndEntryId(city, entries, entryId).stream()
				.filter(r -> isMatchById(r, id))
				.findFirst();
	}

	private boolean isMatchById(Review review, Long id) {
		return review.getId().equals(id);
	}

	default boolean existsByCityAndEntryIdAndId(
			String city, String entries, Long entryId, Long id) {
		return !findByCityAndEntryIdAndId(city, entries, entryId, id).isEmpty();
	}
}
