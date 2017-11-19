package emg.demo.jiact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    public static String processFile(BufferedReaderProcessor processor, String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return processor.process(br);
        }
    }
}
