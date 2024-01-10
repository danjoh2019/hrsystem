package se.yrgo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class NameGenerator {
    private List<String> names;

    public NameGenerator() {
        try {
            Path path = Path.of("src/main/java/se/yrgo/names.txt");
            checkIfFileExists(path);
            names = Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("The list of employees couldn't be found");
        }
    }

    private void checkIfFileExists(Path path) throws IOException {
        if(!Files.exists(path)) {
            List<String> nameList = List.of(
                    "Michael Douglas",
                    "Robin Williams",
                    "Tom Hanks",
                    "Jodie Foster",
                    "Julia Roberts",
                    "Helen Mirren"
            );

            String nameString = String.join(System.lineSeparator(), nameList);

            Files.writeString(path, nameString);
        }
    }

    public List<String> getNames() {
        return names;
    }
}
