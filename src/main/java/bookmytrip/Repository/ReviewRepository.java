package bookmytrip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookmytrip.Entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}