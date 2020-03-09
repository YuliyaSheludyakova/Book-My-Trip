package bookmytrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookmytrip.entity.Cuisine;

public interface CuisineRepository extends JpaRepository<Cuisine, String> {}
