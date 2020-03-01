package bookmytrip.entity;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Entry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "entry", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Review> reviews = new ArrayList<Review>();
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "city", nullable = false)
	private City city;
	
	@Embedded
	private DetailedContact detailedContact;
	
	@NotNull
	@Column(nullable = false)
	private Long avrgRating = (long) 0;
	
	@NotNull
	@Column(nullable = false)
	private Integer numOfReviews = 0;
}
