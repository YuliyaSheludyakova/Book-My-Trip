package bookmytrip.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import bookmytrip.entity.Entry;
import bookmytrip.repository.EntryRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-my-trip")
public class EntryController {	
	
	private final EntryRepository<Entry> entryRepository;
	
	@GetMapping("/{city}")
	public List<Entry> getAllByCity(@PathVariable String city) {
		return entryRepository.findByCity(city);
	}	
}
