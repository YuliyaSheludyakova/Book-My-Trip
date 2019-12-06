package bookmytrip.Entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "museum_id")
public class Museum extends Entry{

	// TODO: add properties (Controller and Repository are already done)
	
	@NotNull
	@Column(nullable = false)
	private String type;
	
	@NotNull
	@Min(1) @Max(3)
	@Column(nullable = false)
	private Integer priceLevel;	
	
	
}
