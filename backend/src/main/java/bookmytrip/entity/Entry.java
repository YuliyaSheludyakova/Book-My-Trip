package bookmytrip.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
