package bookmytrip.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Entity
@Getter @Setter
public class MuseumType {
	
	@Id
	@NotBlank
	private String type;	

	@ManyToMany(mappedBy = "museumTypes", cascade = CascadeType.MERGE)
	@JsonBackReference
	private Set<Museum> museums;
}
