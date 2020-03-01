package bookmytrip.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@Getter @Setter
@PrimaryKeyJoinColumn(name = "restaurant_id")
public class Restaurant extends Entry {
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
			name = "restaurant_to_cuisine",
			joinColumns = @JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "cuisine_id"))
	private Set<Cuisine> cuisines;
	
	@NotNull
	@Min(1) @Max(3)
	@Column(nullable = false)
	private Integer priceLevel;	
}
