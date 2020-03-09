package bookmytrip.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "restaurant_id")
public class Restaurant extends Entry {

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "restaurant_to_cuisine",
			   joinColumns = @JoinColumn(name = "restaurant_id"),
			   inverseJoinColumns = @JoinColumn(name = "cuisine_id"))
	private Set<Cuisine> cuisines;

	@NotNull
	@Min(1)
	@Max(3)
	@Column(nullable = false)
	private Integer priceLevel;
}
