package bookmytrip.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Entity
@Getter @Setter
public class City {
	
	@Id
	@NotBlank
	String name;
	
	@OneToMany(mappedBy = "city", cascade = CascadeType.MERGE)
	@JsonBackReference
	List<Entry> entries;
}
