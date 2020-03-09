package bookmytrip.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookmytrip.repository.CuisineRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip/cuisines")
public class CuisineController {

	private final CuisineRepository cuisineRepo;

	@GetMapping
	public List<String> getAll() {
		return cuisineRepo.findAll().stream()
			       .map(c -> c.getType())
				   .collect(Collectors.toList());
	}
}
