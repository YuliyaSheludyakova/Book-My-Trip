package bookmytrip.repository;

import java.util.List;
import java.util.stream.Collectors;

import bookmytrip.entity.Restaurant;

public interface RestaurantRepository extends EntryRepository<Restaurant> {

	default List<Restaurant> filterByPriceLevel(
			List<Restaurant> maybeRestaurants, Integer priceLevel, String city) {
		if (maybeRestaurants != null && priceLevel != null) {
			maybeRestaurants.retainAll(
					findByCityAndPriceLevelOrderByPriceLevel(city,priceLevel));
		} else if (priceLevel != null) {
			maybeRestaurants =
					findByCityAndPriceLevelOrderByPriceLevel(city, priceLevel);
		}
		return maybeRestaurants;
	}

	private List<Restaurant> findByCityAndPriceLevelOrderByPriceLevel(
			String city, int priceLevel) {
		return findByCity(city).stream()
				.filter(r -> isMatchByPriceLevel(r, priceLevel))
				.sorted((r1, r2) -> compareByPriceLevel(r1, r2))
				.collect(Collectors.toList());
	}

	private boolean isMatchByPriceLevel(Restaurant restaurant, int priceLevel) {
		return restaurant.getPriceLevel().equals(priceLevel);
	}

	private int compareByPriceLevel(
			Restaurant firstRestaurant, Restaurant secondRestaurant) {
		return firstRestaurant.getPriceLevel().
				compareTo(secondRestaurant.getPriceLevel());
	}

	default List<Restaurant> filterByCuisine(
			List<Restaurant> maybeRestaurants, String cuisine, String city) {
		if (maybeRestaurants != null && cuisine != null) {
			maybeRestaurants.retainAll(
					findByCityAndCuisineOrderByName(city, cuisine));
		} else if (cuisine != null) {
			maybeRestaurants = findByCityAndCuisineOrderByName(city, cuisine);
		}
		return maybeRestaurants;
	}

	private List<Restaurant> findByCityAndCuisineOrderByName(
			String city, String cuisine) {
		return findByCity(city).stream()
				.filter(r -> isMatchByCuisine(r, cuisine))
				.sorted((r1, r2) -> r1.getName().compareTo(r2.getName()))
				.collect(Collectors.toList());
	}

	private boolean isMatchByCuisine(Restaurant restaurant, String cuisine) {
		return restaurant.getCuisines().stream()
				.anyMatch(c -> c.getType().equals(cuisine));
	}
}