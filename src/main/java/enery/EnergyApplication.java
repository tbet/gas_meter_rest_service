package enery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class EnergyApplication {

	private static String basePath = "/git/gas_meter_rest_service/";
	private static String inputPath = "/input/";
	private static String compassInputLogFile = "/test_raw.log";

	public static void main(String[] args) throws IOException, InterruptedException {

		SpringApplication.run(EnergyApplication.class, args);

		basePath = System.getProperty("user.home") + basePath;
		JsonValues.getInstance().setConsumption(GasMeter.loadGasMeterFileValue(basePath + "currentGasValue.txt"));
		GasMeter.loadGasMeterId(basePath + "gasMeterId.txt");
		GasMeter.setTimestamp();

		Path path = Paths.get(basePath + inputPath);

		WatchService watchService
				= FileSystems.getDefault().newWatchService();

		path.register(
				watchService,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key;

		System.out.println("Checking for Compass log files in path: " + path);
		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.println(
						"Event kind:" + event.kind()
								+ ". File affected: " + event.context() + ".");
				Path filePath = Paths.get(path + compassInputLogFile);
				if (Files.exists(filePath)) {
					CompassLogFile.readLog(path + compassInputLogFile);
					GasMeter.writeCurrentGasMeterValue();
					watchService.poll(20, TimeUnit.SECONDS);
				} else {
					System.out.println("No File "+ path + compassInputLogFile +", do nothing");
				}
			}
			key.reset();
		}
	}
}
