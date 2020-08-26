import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

public class VacationRegister {

    public static void main(String[] args) {
        readFromFileAndPopulateTable("C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\vacation_short.csv");
    }

    static String[] readFromFileAndPopulateTable(String filePath){
        String[] allDataFromFile = new String[1000]; //just for testing..
        StringBuilder sB = new StringBuilder();
        sB.append(
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Registered vacations</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<H2>Registered Vacations</H2>\n" +
                        "<table border=\"5\" cellpadding=\"10 \" cellspacing=\"1 \">\n" +
                        "<tr>\n" +
                        "<th> Name </th>\n" +
                        "<th> Email </th>\n" +
                        "<th colspan = \"25\"> Dates </th>\n" +
                        "</tr>\n");

        try {
            File objectFile = new File(filePath);
            Scanner sc = new Scanner(objectFile);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] lineData = line.split(",");
                allDataFromFile = line.split(",");

                String name = lineData[0];
                String email = lineData[1];
                String[] dates = Arrays.copyOfRange(lineData, 2, lineData.length);

                sB.append("<tr>"
                        + "<td>").append(name).append("</td>\n")
                        .append("<td>").append(email).append("</td>\n");

                for (String date : dates) {
                    sB.append("<td>").append(date).append("</td>\n");
                }
                sB.append("</tr>\n");
            }
            sc.close();
            sB.append("</table>\n"
                    + "</body>\n"
                    + "</html>");

            File htmlTemplateFile = new File(
                    "C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\src\\main\\frontend\\index.html");
            FileUtils.writeStringToFile(htmlTemplateFile, sB.toString());
            FileUtils.readFileToString(htmlTemplateFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allDataFromFile;
    }
}



