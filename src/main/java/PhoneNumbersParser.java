import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PhoneNumbersParser {

    private File file;
    private final static String[] patterns = {"(xxx) xxx-xxxx",
    "xxx-xxx-xxxx"};

    PhoneNumbersParser(File file) {
        this.file = file;
    }

    PhoneNumbersParser(String filePath) {
        this(new File(filePath));
    }

    PhoneNumbersParser() {
        this(new File("src//main//resources//file.txt"));
    }

    public void parseNumbersInConsole() {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isValidPhoneNumber(line.trim())) {
                    System.out.println(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {

// We can use this pattern to solve this problem
//        String pattern = "(\\d{3}-){2}\\d{4}|\\(\\d{3}\\) \\d{3}-\\d{4}";
// but i will use another solution

        char[] incomingSequence = phoneNumber.toCharArray();
        boolean flag = true;
        for (String pattern : patterns) {
            char[] patternSequence = pattern.toCharArray();


            try {
                for (int i = 0; i < patternSequence.length; i++) {
                    if (patternSequence[i] == 'x' && !Character.isDigit(incomingSequence[i])) {
                        flag = false;
                        break;
                    }

                    if (patternSequence[i] != 'x' && patternSequence[i] != incomingSequence[i]) {
                        flag = false;
                        break;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                flag = false;
            }

            if (patternSequence.length != incomingSequence.length) {
                flag = false;;
            }

            if (flag) {
                return true;
            } else {
                flag = true;
            }
        }
        return false;
    }


}
