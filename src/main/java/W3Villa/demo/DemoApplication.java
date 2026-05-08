package W3Villa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/demo")
public class DemoApplication {

	HashMap<String, Integer> dictionary = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

//	Add Word
	@PostMapping("/add/{word}")
	public String addWord(@PathVariable String word) {
		word = word.toLowerCase();

		if(dictionary.containsKey(word)){
			return "already exists";
		}

		dictionary.put(word,1);
		return "Word Added";
	}


//	Search Word
	@GetMapping("/search/{word}")
	public String searchWord(@PathVariable String word) {

		word = word.toLowerCase();
		if(dictionary.containsKey(word)) {
			dictionary.put(word, dictionary.get(word) + 1);
			return "FOUND";
		}
		return "NOT FOUND";
	}

//	Prefix Based Suggestions
	@GetMapping("/suggest")
	public List<String> suggestWords(
			@RequestParam String prefix,
			@RequestParam int k) {

		prefix = prefix.toLowerCase();

		List<String> result = new ArrayList<>();

		for (String word : dictionary.keySet()) {

			if (word.startsWith(prefix)) {
				result.add(word);
			}
		}
		Collections.sort(result, (a, b) -> {

			int freqCompare = dictionary.get(b) - dictionary.get(a);

			if (freqCompare != 0) {
				return freqCompare;
			}
			return a.compareTo(b);
		});

		int limit = Math.min(k, result.size());
		return result.subList(0, limit);
	}
}
