package bookmytrip.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookmytrip.repository.CityRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip")
public class CityController {

	private final CityRepository cityRepo;

	@GetMapping
	public List<String> getAll() {
		return cityRepo.findAll().stream()
			       .map(c -> c.getName())
			       .collect(Collectors.toList());
	}
}
