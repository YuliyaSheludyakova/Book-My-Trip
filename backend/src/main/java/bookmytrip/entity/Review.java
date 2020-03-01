package bookmytrip.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import lombok.*;

@Entity
@Getter @Setter
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
	@Min(1) @Max(5)
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

 
