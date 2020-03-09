package bookmytrip.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookmytrip.entity.Entry;
import bookmytrip.repository.EntryRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip")
public class EntryController {

	private final EntryRepository<Entry> entryRepository;

	@GetMapping("/{city}")
	public List<Entry> getByCity(@PathVariable String city) {
		return entryRepository.findByCity(city);
	}
}
