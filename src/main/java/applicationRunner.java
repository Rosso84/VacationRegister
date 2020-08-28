import java.io.File;
import java.io.IOException;
import java.sql.*;

import Connections.DbConnect;
import org.apache.commons.io.FileUtils;

public class applicationRunner {

    public static void main(String[] args) {
        DbConnect dbconnect = new DbConnect();
        String queryDatoer = "SELECT person_id, dato FROM datoer  ";
        String queryPersoner = "SELECT person_id, navn, email FROM Personer ";

        try {

            Connection con = dbconnect.getConnection();

            //query Personer
            PreparedStatement prstmPersoner = con.prepareStatement(queryPersoner);
            ResultSet resultSetPersoner = prstmPersoner.executeQuery();
            ResultSetMetaData resMetaPersoner = resultSetPersoner.getMetaData();

            //query Datoer
            PreparedStatement prstmtDatoer = con.prepareStatement(queryDatoer);
            ResultSet resultSetDatoer = prstmtDatoer.executeQuery();
            ResultSetMetaData resMetaDatoer = resultSetDatoer.getMetaData();


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
                            "<th colspan = \"25\">" + resMetaDatoer.getColumnName(2) + "</th>\n" +
                            "</tr>\n");

            while (resultSetPersoner.next()) {
                int id = resultSetPersoner.getInt(1);
                String name = resultSetPersoner.getString(2);
                String email = resultSetPersoner.getString(3);
                sB.append("<tr>"
                        + "<td>").append(id).append("</td>\n")
                        .append("<td>").append(name).append("</td>\n")
                        .append("<td>").append(email).append("</td>\n");
                //while ()
            }

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




