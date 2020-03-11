package bookmytrip.repository;

import java.util.List;
import java.util.stream.Collectors;

import bookmytrip.entity.Hotel;

public interface HotelRepository extends EntryRepository<Hotel> {

	default List<Hotel> filterByBreakfastIncl(List<Hotel> maybeHotels,
			                                  Boolean breakfastIncl, String city) {
		if (maybeHotels != null && breakfastIncl != null) {
			maybeHotels
			    .retainAll(findByCityAndBreakfastInclOrderByName(city, breakfastIncl));
		} else if (breakfastIncl != null) {
			maybeHotels = findByCityAndBreakfastInclOrderByName(city, breakfastIncl);
		}
		return maybeHotels;
	}

	private List<Hotel> findByCityAndBreakfastInclOrderByName(String city, boolean breakfastIncl) {
		return findByCity(city).stream()
				   .filter(h -> isMatchByBreakfastIncl(h))
				   .sorted((h1, h2) -> compareByName(h1, h2))
				   .collect(Collectors.toList());
	}

	private boolean isMatchByBreakfastIncl(Hotel hotel) {
		return hotel.getBreakfastIncl();
	}

	default List<Hotel> filterByStars(List<Hotel> maybeHotels, Integer stars, String city) {
		if (maybeHotels != null && stars != null) {
			maybeHotels
			    .retainAll(findByCityAndStarsOrderByName(city, stars));
		} else if (stars != null) {
			maybeHotels = findByCityAndStarsOrderByName(city, stars);
		}
		return maybeHotels;
	}

	private List<Hotel> findByCityAndStarsOrderByName(String city, int stars) {
		return findByCity(city).stream()
				   .filter(h -> isMatchByStars(h, stars))
				   .sorted((h1, h2) -> compareByName(h1, h2))
				   .collect(Collectors.toList());
	}

	private boolean isMatchByStars(Hotel hotel, int stars) {
		return hotel.getStars() == stars;
	}
}
