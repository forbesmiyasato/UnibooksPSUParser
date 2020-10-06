
package unibooks;

import java.io.*;
import java.util.*;

public class PSUParser {

    void parseCourses() {
        try {
            String currentDir = System.getProperty("user.dir") + "\\src\\unibooks\\";
            File input = new File(currentDir + "\\courses.txt");
            File output = new File(currentDir+ "\\output.txt");
            System.out.println(output.exists());
            Scanner reader = new Scanner(input);
            FileWriter writer = new FileWriter(output);
            BufferedWriter myWriter = new BufferedWriter(writer);
            int department_id = 0; //modify this value every time you add courses for a department
            int school_id = 2; //PSU's school id in the db is 2
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
                int count = 0;
                StringBuilder outputLine = new StringBuilder();
                if (line.contains("Research") || line.contains("Internship")
                || line.contains("Special Studies") || line.contains("Seminar") || line.contains("Special Projects")) {
                    System.out.println(line);
                    continue;
                }
                while (stringTokenizer.hasMoreTokens()) {
                    if (count == 0) {
                        outputLine.append("('").append(stringTokenizer.nextToken()).append(" ");
                    } else if (count == 1) {
                        outputLine.append(stringTokenizer.nextToken() + "', '");
                    } else {
                        outputLine.append(stringTokenizer.nextToken() + (stringTokenizer.hasMoreTokens() ? " " : ""));
                    }
                    count ++;
                }
                outputLine.append("', " + department_id + ", " + school_id + "),");
                String outputString = outputLine.toString();
                myWriter.write(outputString);
                myWriter.newLine();
                System.out.println(outputString);
            }
            myWriter.write("('Other', 'Other', " + department_id + ", " + school_id + "),");
            System.out.println("('Other', 'Other', " + department_id + ", " + school_id + "),");
            myWriter.flush();
            myWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
