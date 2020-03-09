package bookmytrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookmytrip.entity.City;

public interface CityRepository extends JpaRepository<City, String> {}
