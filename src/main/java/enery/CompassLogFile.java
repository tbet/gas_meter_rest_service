package enery;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CompassLogFile  {

    public static void readLog (String fileName) throws IOException {
        System.out.println("--- start readLog from: " + fileName + " ---");
        moveLogFile(fileName);
        Stream<String> stream = Files.lines(Path.of(fileName + "tmp"));
        stream.forEach(i -> {
            try {
                logToBean(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("--- end readLog ---");
    }

    public static void logToBean(String log) throws IOException {
        CompassBean compassBean = null;

        //inputString.replaceAll(filter.stream().map(Object::toString).collect(Collectors.joining("", "[^", "]")), "");
        log = log.replaceAll("\\(", "");
        log = log.replaceAll("\\)", "");
        log = log.replaceAll("\\'", "");
        log = log.replaceAll(" ", "");

        List<String> logList = split(log);
        if (null != logList && logList.size() == 4 && !"None".equalsIgnoreCase(logList.get(1)) && !"0".equalsIgnoreCase(logList.get(1)) && !"0".equalsIgnoreCase(logList.get(2)) && !"0".equalsIgnoreCase(logList.get(3))) {
            convertToCompassBean(logList);
        } else {
            System.out.println("Ungueltig: " + logList);
        }
    }

    public static void convertToCompassBean(List<String> logList) throws IOException {
        CompassBean compassBean = new CompassBean();
        LocalDateTime localDateTime = LocalDateTime.parse(logList.get(0), DateTimeFormatter.ISO_DATE_TIME);
        compassBean.setLocalDateTime(localDateTime);
        compassBean.setX(Integer.parseInt(logList.get(1)));
        compassBean.setY(Integer.parseInt(logList.get(2)));
        compassBean.setZ(Integer.parseInt(logList.get(3)));
        CompassToCounter.calculate(compassBean);
    }

    public static List<String> split(String str){
        return Stream.of(str.split(","))
                .map (elem -> new String(elem))
                .collect(Collectors.toList());
    }

    public static void moveLogFile(String fileName) {
        Path f = Paths.get(fileName);
        Path rF = Paths.get(fileName + "tmp");
        try {
            Files.move(f, rF, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File was successfully renamed");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to rename file");
        }
    }

}
