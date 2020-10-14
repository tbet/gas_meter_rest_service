package enery;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

public class GasMeter {

    private static String gasMeterIdFile;
    private static String gasMeterFile;

    public static Double loadGasMeterFileValue(String path) {
        gasMeterFile = path;
        System.out.println("Load of current gas meter value is read from file: " + gasMeterFile);
        Path gasMeterPath = Paths.get(gasMeterFile);
        String[] strArray = new String[0];
        Double value = null;
        try {
            strArray = Files.lines(gasMeterPath)
                    .map(s -> s.split(","))
                    .findFirst()
                    .get();
        } catch (IOException e) {
            System.out.println("Not able to read file" + e);
        }
        if (strArray.length == 0) {
            System.out.println("No gas meter value in file availible");
        } else {
            String valueAsString = strArray[0];
            System.out.println("Gas meter value in file is: " +  valueAsString);
            value = Double.parseDouble(valueAsString);
        }
        return value;
    }

    public static void loadGasMeterId(String path) {
        gasMeterIdFile = path;
        System.out.println("Inital load of gas meter Id is read from file: " + gasMeterIdFile);
        Path gasMeterPath = Paths.get(gasMeterIdFile);
        String[] strArray = new String[0];
        try {
            strArray = Files.lines(gasMeterPath)
                    .map(s -> s.split(","))
                    .findFirst()
                    .get();
        } catch (IOException e) {
            System.out.println("Not able to read file" + e);
        }
        if (strArray.length == 0) {
            System.out.println("No inital gas meter id in file availible");
        } else {
            String gasMeterId = strArray[0];
            System.out.println("Inital gas meter id in file is: " +  gasMeterId);
            JsonValues.getInstance().setServerID(gasMeterId);
        }
    }

    public static void writeCurrentGasMeterValue () {
        double currentValue = JsonValues.getInstance().getConsumption();
        double valueInFile = loadGasMeterFileValue(gasMeterFile);
        if (currentValue != valueInFile) {
            setTimestamp();
            System.out.println("Write current gas meter value of ["+currentValue+"] to file: " + gasMeterFile);
            try (PrintStream out = new PrintStream(new FileOutputStream(gasMeterFile))) {
                out.print(currentValue);
                out.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JsonValues.getInstance().setTimestamp(timestamp.toInstant());
    }
}

