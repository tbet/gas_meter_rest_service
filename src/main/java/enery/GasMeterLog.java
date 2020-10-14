package enery;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GasMeterLog {

    private static DecimalFormat df = new DecimalFormat("0.00");
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:hh:mm:ss");
    private static final String fileName = "/git/gas_meter_rest_service/output/gasmeter.log";

    public static void write(LocalDateTime dateTime, double counter) {
        String realfileName = System.getProperty("user.home") + fileName;
        Path path = Paths.get(realfileName);
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(realfileName, true));
            output.append(dateTime.format(formatter) + ";" + df.format(counter) + "\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
