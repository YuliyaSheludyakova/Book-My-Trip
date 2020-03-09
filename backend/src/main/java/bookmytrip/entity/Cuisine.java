package bookmytrip.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cuisine {

	@Id
	@NotBlank
	private String type;

	@ManyToMany(mappedBy = "cuisines", cascade = CascadeType.MERGE)
	@JsonBackReference
	private Set<Restaurant> restaurants;
}
