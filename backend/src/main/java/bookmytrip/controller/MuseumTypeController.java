package bookmytrip.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookmytrip.repository.MuseumTypeRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip/museum-types")
public class MuseumTypeController {

	private final MuseumTypeRepository museumTypeRepo;

	@GetMapping
	public List<String> getAll() {
		return museumTypeRepo.findAll().stream()
	               .map(m -> m.getType())
				   .collect(Collectors.toList());
	}
}
