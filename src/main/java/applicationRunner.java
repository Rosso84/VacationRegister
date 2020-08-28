import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Connections.DbConnect;
import org.apache.commons.io.FileUtils;

public class applicationRunner {

    public static void main(String[] args) {
        DbConnect dbconnect = new DbConnect();
        String query = "select personer.person_id, personer.navn, personer.email, datoer.dato\n" +
                "from personer\n" +
                "inner join datoer ON personer.person_id=datoer.person_id; ";

        try {

            Connection con = dbconnect.getConnection();
            PreparedStatement prstmt = con.prepareStatement(query);
            ResultSet res = prstmt.executeQuery();
            ResultSetMetaData resMeta = res.getMetaData();


            String pers_idCol = resMeta.getColumnName(1);
            String nameCol = resMeta.getColumnName(2);
            String emailCol = resMeta.getColumnName(3);
            String dateCol = resMeta.getColumnName(4);

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
                            "<table border=\"5\" cellpadding=\"10 \" cellspacing=\"0 \">\n" +
                            "<tr>\n" +
                            "<th> " + pers_idCol + " </th>\n" +
                            "<th>" + nameCol + "</th>\n" +
                            "<th>" + emailCol + "</th>\n" +
                            "<th colspan = \"25\">" + dateCol + "</th>\n" +
                            "</tr>\n");

            /*while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                String email = res.getString(3);
                String date = res.getString(4);
                sB.append("<tr>"
                        + "<td>").append(id).append("</td>\n")
                        .append("<td>").append(name).append("</td>\n")
                        .append("<td>").append(email).append("</td>\n")
                        .append("<td>").append(date).append("</td>\n");
            }*/

            // removes duplicates of email and ids from 'INNER JOIN query'
            HashSet<Integer> idSet = new HashSet<>();
            HashSet<String> emailSet = new HashSet<>();
            HashSet<String> nameSet = new HashSet<>();

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                String email = res.getString(3);

                idSet.add(id);
                emailSet.add(email);
                nameSet.add(name);
            }

            List<Integer> iDlist = new ArrayList<>(idSet);
            List<String> nameList = new ArrayList<>(nameSet);
            List<String> mailList = new ArrayList<>(emailSet);

            for (int i : iDlist) {
                sB.append("<tr>")
                        .append("<td>").append(i).append("</td>\n");
                        //.append("<td>").append(n).append("</td>\n");  //<------------funker

            }

            for (String n : nameList) {
                sB.append("<td>").append(n).append("</td>\n"); //<----------funker ikke
            }

            System.out.println(nameList);


            sB.append("</tr>\n" +
                    "</table>\n"
                    + "</body>\n"
                    + "</html>");

            File htmlTemplateFile = new File(
                    "C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\src\\main\\frontend\\index.html");
            FileUtils.writeStringToFile(htmlTemplateFile, sB.toString());
            FileUtils.readFileToString(htmlTemplateFile);


        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}




