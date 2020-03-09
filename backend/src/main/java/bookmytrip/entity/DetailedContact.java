package bookmytrip.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
