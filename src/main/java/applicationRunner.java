import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import Connections.DbConnect;
import org.apache.commons.io.FileUtils;

public class applicationRunner {

    public static void main(String[] args) {
        DbConnect dbconnect = new DbConnect();
        String queryPersoner = "SELECT person_id, navn, email FROM Personer";

        try {

            Connection con = dbconnect.getConnection();

            //query Personer
            PreparedStatement prstmPersoner = con.prepareStatement(queryPersoner);
            ResultSet resultSetPersoner = prstmPersoner.executeQuery();
            ResultSetMetaData resMetaPersoner = resultSetPersoner.getMetaData();


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
                            "<th> " + resMetaPersoner.getColumnName(1) + " </th>\n" +
                            "<th>" + resMetaPersoner.getColumnName(2) + "</th>\n" +
                            "<th>" + resMetaPersoner.getColumnName(3) + "</th>\n" +
                            //"<th colspan = \"25\">" + resMetaDatoer.getColumnName(2) + "</th>\n" +
                            "<th colspan = \"25\">dato</th>\n" +
                            "</tr>\n");

            int idQuery = 1;
            ArrayList<Integer> sizeOfPersons = new ArrayList<>();
            //boolean finished = false;

            //while (!finished) {
            String queryDatoer = "SELECT person_id, dato FROM datoer WHERE person_id = '" + idQuery + "' ";
            PreparedStatement prstmtDatoer = con.prepareStatement(queryDatoer);
            ResultSet resultSetDatoer = prstmtDatoer.executeQuery();

            ArrayList<String> dates = new ArrayList<>();
            while (resultSetDatoer.next()) {
                dates.add(resultSetDatoer.getString(2));
            }

            System.out.println("\n size of datesList: " + dates.size());

            for (String d : dates) {
                System.out.print( d + " , ");
            }

            while (resultSetPersoner.next()) {
                int personId = resultSetPersoner.getInt(1);
                sizeOfPersons.add(personId);
                String name = resultSetPersoner.getString(2);
                String email = resultSetPersoner.getString(3);
                sB.append("<tr>"
                        + "<td>").append(personId).append("</td>\n")
                        .append("<td>").append(name).append("</td>\n")
                        .append("<td>").append(email).append("</td>\n");

                sB.append("<td>");
                for (int i = 0; i < dates.size(); i++) {
                    if (personId == idQuery) {
                        sB.append(dates.get(i) + " | ");
                    }
                }
                sB.append("</td>\n");
                dates.clear();
                sizeOfPersons.clear();
            }
       /*         idQuery = idQuery + 1;
                if (idQuery > sizeOfPersons.size()){
                    finished = true;
                }*/
            //finished = true;
            //  }


            sB.append("</tr>\n" +
                    "</table>\n"
                    + "</body>\n"
                    + "</html>");

            File htmlTemplateFile = new File(
                    "C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\src\\main\\frontend\\index.html");
            FileUtils.writeStringToFile(htmlTemplateFile, sB.toString());
            FileUtils.readFileToString(htmlTemplateFile);

            //idQuery = 1;


        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}




