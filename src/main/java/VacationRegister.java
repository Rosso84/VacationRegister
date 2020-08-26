import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

public class VacationRegister {

    private static String[] dates;
    private static String name;
    private static String email;


    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(
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
            //File myObj = new File("C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\registeredVacations.txt");
            File myObj = new File("C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\vacation_short.csv    ");
            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] lineData = line.split(",");

                name = lineData[0];
                email = lineData[1];
                dates = Arrays.copyOfRange(lineData, 2, lineData.length);


                sb.append("<tr>"
                              + "<td>").append(name).append("</td>\n")
                        .append("<td>").append(email).append("</td>\n");

                for (String date : dates) {
                        sb.append("<td>").append(date).append("</td>\n");
                }
                sb.append("</tr>\n");
            }
            scanner.close();
            sb.append("</table>\n"
                            + "</body>\n"
                            + "</html>");

            File htmlTemplateFile = new File(
                    "C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\src\\main\\frontend\\index.html");
            FileUtils.writeStringToFile(htmlTemplateFile, sb.toString());
            FileUtils.readFileToString(htmlTemplateFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



