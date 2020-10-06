package enery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class EnergyApplication {

	//private static String pathName = "/git/gas_meter_rest_service/";
	private static String fileName = "input/test_raw.log";

	public static void main(String[] args) throws IOException, InterruptedException {

		SpringApplication.run(EnergyApplication.class, args);

		//initial load of gas meter value


		WatchService watchService
				= FileSystems.getDefault().newWatchService();

		pathName = System.getProperty("user.home") + pathName;
		Path path = Paths.get(pathName);

		path.register(
				watchService,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key;

		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.println(
						"Event kind:" + event.kind()
								+ ". File affected: " + event.context() + ".");
				Path filePath = Paths.get(pathName + "/" + fileName);
				if (Files.exists(filePath)) {
					CompassLogFile.readLog(pathName + "/" + fileName);
					watchService.poll(20, TimeUnit.SECONDS);
				} else {
					System.out.println("No File "+pathName + "/" + fileName+", do nothing");
				}
			}
			key.reset();
		}
	}
}
