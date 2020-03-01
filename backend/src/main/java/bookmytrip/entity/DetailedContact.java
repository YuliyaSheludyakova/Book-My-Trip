package bookmytrip.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Getter @Setter
@Embeddable
public class DetailedContact {
	
	@NotBlank
	private String phoneNumber;
	
	@NotBlank
	@NotNull
	@Column(nullable = false)
	private String streetName;
	
	@NotNull
	@Column(nullable = false)
	private int houseNumber;
	
	@NotNull
	@Column(nullable = false)
	private int postalCode;
}
