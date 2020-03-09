package bookmytrip.repository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import bookmytrip.entity.Entry;
import bookmytrip.entity.Review;

public interface EntryRepository<T extends Entry> extends JpaRepository<T, Long> {

	default List<T> findByCity(String city) {
		return findAll().stream()
				.filter(e -> isMatchByCity(e, city))
				.collect(Collectors.toList());
	}

	private boolean isMatchByCity(T entry, String city) {
		return entry.getCity().getName().equals(city);
	}

	default Optional<T> findByCityAndId(String city, Long id) {
		return findByCity(city).stream()
				.filter(e -> isMatchById(e, id))
				.findFirst();
	}

	private boolean isMatchById(T entry, Long id) {
		return entry.getId().equals(id);
	}

	default boolean existsByCityAndId(String city, Long id) {
		return !findByCityAndId(city, id).isEmpty();
	}

	default List<T> findByCityOrderByName(String city) {
		return findByCity(city).stream()
				.sorted((e1, e2) -> compareByName(e1, e2))
				.collect(Collectors.toList());
	}

	default int compareByName(T firstEntry, T secondEntry) {
		return firstEntry.getName()
				.compareTo(secondEntry.getName());
	}

	default List<T> findByCityAndNameOrderByRating(
			String city, String searchedName) {
		return findByCity(city).stream()
				.filter(e -> isMatchByName(e, searchedName))
				.sorted((e1, e2) -> compareByAvrgRating(e1, e2))
				.collect(Collectors.toList());
	}

	private boolean isMatchByName(T entry, String searchedName) {
		String entryName = entry.getName().toLowerCase();
		searchedName = searchedName.toLowerCase();
		return entryName.contains(searchedName) ||
				searchedName.contains(entryName);
	}

	private int compareByAvrgRating(T firstEntry, T secondEntry) {
		return firstEntry.getAvrgRating()
				.compareTo(secondEntry.getAvrgRating());
	}

	default List<T> filterByRating(List<T> maybeEntries, Integer rating, String city) {
		if (maybeEntries != null && rating != null) {
			maybeEntries.retainAll(findByCityAndRatingOrderByName(city, rating));
		} else if (rating != null) {
			maybeEntries = findByCityAndRatingOrderByName(city, rating);
		}
		return maybeEntries;
	}

	private List<T> findByCityAndRatingOrderByName(
			String city, int minRequiredAvrgRating) {
		return findByCity(city).stream()
				.filter(e -> isMinRequiredAvrgRating(e, minRequiredAvrgRating))
				.sorted((e1, e2) -> compareByName(e1, e2))
				.collect(Collectors.toList());
	}

	private boolean isMinRequiredAvrgRating(
			T entry, int minRequiredAvrgRating) {
		return entry.getAvrgRating() >= minRequiredAvrgRating;
	}

	default void setDefaults(T entry, String city, Long id) {
		entry.setId(id);
		entry.getCity().setName(city);

		if (id != null) {
			entry.setNumOfReviews(getNewNumOfReviews(city, id));
			entry.setAvrgRating(getNewAvrgRating(city, id));
		}
	}

	private Long getNewAvrgRating(String city, Long id) {
		T entry = findByCityAndId(city, id).get();
		return calculateAvrgRating(entry);
	}

	private Long calculateAvrgRating(T entry) {
		List<Review> reviewsList = entry.getReviews();
		//entry.setNumOfReviews(reviewsList.size());
		double avrgRating = reviewsList.stream()
				.map(review -> review.getRating())
				.mapToInt(rating -> rating)
				.average().orElse(0);
		return Math.round(avrgRating);
	}

	private int getNewNumOfReviews(String city, Long id) {
		T entry = findByCityAndId(city, id).get();
		return entry.getReviews().size();
	}
}
