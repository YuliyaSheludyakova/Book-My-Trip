package bookmytrip.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import bookmytrip.entity.Entry;
import bookmytrip.entity.Hotel;
import bookmytrip.entity.Museum;
import bookmytrip.entity.Restaurant;
import bookmytrip.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	default List<Review> findBy(String city, String entriesType, Long entryId) {
		return findAll().stream()
				   .filter(r -> isMatchByCity(r, city))
				   .filter(r -> isMatchByEntriesType(r, entriesType))
				   .filter(r -> isMatchByEntryId(r, entryId))
				   .collect(Collectors.toList());
	}

	default Optional<Review> findBy(String city, String entriesType, Long entryId, Long reviewId) {
		return findBy(city, entriesType, entryId).stream()
				   .filter(r -> isMatchByReviewId(r, reviewId))
				   .findFirst();
	}

	private boolean isMatchByCity(Review review, String city) {
		Entry entry = review.getEntry();
		String actualCity = entry.getCity().getName();
		return actualCity.equals(city);
	}

	private boolean isMatchByEntriesType(Review review, String entriesType) {
		switch (entriesType) {
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

	private boolean isMatchByEntryId(Review review, Long entryId) {
		Entry entry = review.getEntry();
		Long actualEntryId = entry.getId();
		return actualEntryId.equals(entryId);
	}

	private boolean isMatchByReviewId(Review review, Long reviewId) {
		return review.getId().equals(reviewId);
	}

	default boolean existsBy(String city, String entriesType, Long entryId, Long reviewId) {
		return !findBy(city, entriesType, entryId, reviewId).isEmpty();
	}

	default Review saveWithDefaults(Review review, Long id) {
		review.setId(id);
		return save(review);
	}
}
