package bookmytrip.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@Getter @Setter
@PrimaryKeyJoinColumn(name = "hotel_Id")
public class Hotel extends Entry {

	@NotNull
	@Min(1) @Max(5)
	@Column(nullable = false)
	private Integer stars;
		
	@NotNull
	@Column(nullable = false)
	private Boolean breakfastIncl;
}
