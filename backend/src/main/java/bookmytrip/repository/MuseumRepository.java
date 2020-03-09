package bookmytrip.repository;

import java.util.List;
import java.util.stream.Collectors;

import bookmytrip.entity.Museum;

public interface MuseumRepository extends EntryRepository<Museum> {

	default List<Museum> filterByPriceLevel(
			List<Museum> maybeMuseums, Integer priceLevel, String city) {
		if (maybeMuseums != null && priceLevel != null) {
			maybeMuseums.retainAll(
					findByCityAndPriceLevelOrderByPriceLevel(city,priceLevel));
		} else if (priceLevel != null) {
			maybeMuseums =
					findByCityAndPriceLevelOrderByPriceLevel(city, priceLevel);
		}
		return maybeMuseums;
	}

	private List<Museum> findByCityAndPriceLevelOrderByPriceLevel(
			String city, int priceLevel) {
		return findByCity(city).stream()
				.filter(m -> isMatchByPriceLevel(m, priceLevel))
				.sorted((m1, m2) -> compareByPriceLevel(m1, m2))
				.collect(Collectors.toList());
	}

	private boolean isMatchByPriceLevel(Museum museum, int priceLevel) {
		return museum.getPriceLevel().equals(priceLevel);
	}

	private int compareByPriceLevel(Museum firstMuseum, Museum secondMuseum) {
		return firstMuseum.getPriceLevel().
				compareTo(secondMuseum.getPriceLevel());
	}

	default List<Museum> filterByMuseumType(
			List<Museum> maybeMuseums, String museumType, String city) {
		if (maybeMuseums != null && museumType != null) {
			maybeMuseums.retainAll(findByCityAndMuseumTypeOrderByName(city, museumType));
		} else if (museumType != null) {
			maybeMuseums = findByCityAndMuseumTypeOrderByName(city, museumType);
		}
		return maybeMuseums;
	}

	private List<Museum> findByCityAndMuseumTypeOrderByName(String city, String museumType) {
		return findByCity(city).stream()
				.filter(m -> isMatchByMuseumType(m, museumType))
				.sorted((m1, m2) -> compareByName(m1, m2))
				.collect(Collectors.toList());
	}

	private boolean isMatchByMuseumType(Museum museum, String museumType) {
		return museum.getMuseumTypes().stream()
				.anyMatch(mt -> mt.getType().equals(museumType));
	}
}
