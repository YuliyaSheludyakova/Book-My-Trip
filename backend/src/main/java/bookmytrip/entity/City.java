package bookmytrip.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class City {

	@Id
	@NotBlank
	String name;

	@OneToMany(mappedBy = "city", cascade = CascadeType.MERGE)
	@JsonBackReference
	List<Entry> entries;
}
