import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserParser {

    private final static String inputFile = "src//main//resources//user.txt";
    private final static String outputFile = "src//main//resources//user.json";

    public void parseUserFile() {
        List<Person> personList = readPersonListFromFile();
        writeToJsonFile(personList);
    }

    private static List<Person> readPersonListFromFile() {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String firstLine = br.readLine();
            String[] attributes = firstLine.split(" ");
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                Person person = new Person();
                for (int i = 0; i < attributes.length; i++) {
                    switch (attributes[i]) {
                        case "name":
                            person.setName(values[i]);
                            break;
                        case "age":
                            person.setAge(Integer.parseInt(values[i]));
                            break;
                    }
                }
                personList.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personList;
    }

    private static void writeToJsonFile(List<Person> personList) {
        try (FileWriter writer = new FileWriter(outputFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(personList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Person {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
