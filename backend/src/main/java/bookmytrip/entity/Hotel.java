package bookmytrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "hotel_id")
public class Hotel extends Entry {

	@NotNull
	@Min(1)
	@Max(5)
	@Column(nullable = false)
	private Integer stars;

	@NotNull
	@Column(nullable = false)
	private Boolean breakfastIncl;
}
