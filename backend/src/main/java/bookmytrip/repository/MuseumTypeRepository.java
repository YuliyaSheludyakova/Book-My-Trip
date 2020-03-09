package bookmytrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookmytrip.entity.MuseumType;

public interface MuseumTypeRepository extends JpaRepository<MuseumType, String> {}
