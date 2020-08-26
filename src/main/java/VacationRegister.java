import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class VacationRegister {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Registered vacations</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<H2>Registered Vacations</H2>\n" +
                        "<table border=\"5\" cellpadding=\"20 \" cellspacing=\"1 \">\n" +
                        "<tr>\n" +
                        "<th> Name </th>\n" +
                        "<th> Email </th>\n" +
                        "<th> Dates </th>\n" +
                        "</tr>\n");
        try {
            File myObj = new File("C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\registeredVacations.txt");
            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] lineData = line.split(",");

                String name = lineData[0];
                String email = lineData[1];
                String[] dates = Arrays.copyOfRange(lineData, 2, lineData.length);

                stringBuilder.append("<tr>"
                              + "<td>").append(name).append("</td>\n")
                        .append("<td>").append(email).append("</td>\n");

                for (String date : dates) {
                    if (date.toString().isEmpty()) {
                        stringBuilder.append("<td>").append("-").append("</td>\n");
                    } else {
                        stringBuilder.append("<td>").append(date.toString()).append("</td>\n");
                    }
                }
                stringBuilder.append("</tr>\n");
            }

            scanner.close();
            stringBuilder.append("</table>\n"
                            + "</body>\n"
                            + "</html>");

            File htmlTemplateFile = new File(
                    "C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\src\\main\\frontend\\index.html");
            FileUtils.writeStringToFile(htmlTemplateFile, stringBuilder.toString());
            FileUtils.readFileToString(htmlTemplateFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



