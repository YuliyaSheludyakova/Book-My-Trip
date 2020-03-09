package bookmytrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {

	@Length(max = 500)
	private String comment;

	@ManyToOne
	@JoinColumn(name = "entry_id", nullable = false)
	private Entry entry;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Min(1)
	@Max(5)
	@Column(nullable = false)
	private Integer rating;

	@Length(max = 500)
	private String reviewerName;

	@NotBlank
	@Length(max = 500)
	private String reviewTitle;

	@NotBlank
	private String dateTime;
}


