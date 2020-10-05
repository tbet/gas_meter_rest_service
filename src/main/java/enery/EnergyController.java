package enery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnergyController {

	@GetMapping("/energy")
	public JsonValues energyReading() {
		return JsonValues.getInstance();
	}
}
