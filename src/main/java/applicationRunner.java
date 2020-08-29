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
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();
        HashMap<Integer, String[][]> idAndDateMap = new HashMap<>();

        String[][] dates = new String[40][40];
        dates[0][0] = "1"; //id 1
        dates[0][1] = "Da1";
        dates[0][2] = "Da2";

        dates[1][0] = "2"; //id 2
        dates[1][1] = "guri1";
        dates[1][2] = "gur2";
        dates[1][3] = "gur3";
        dates[1][4] = "guri4";

        dates[2][0] = "3"; //id 3
        dates[2][1] = "jato1";
        dates[2][2] = "jato2";
        dates[2][3] = "jato3";
        dates[2][4] = "jato4";
        dates[2][5] = "jato5";
        dates[2][6] = "jato6";

        dates[3][0] = "4"; //id 4
        dates[3][1] = "kato1";
        dates[3][2] = "kato2";
        dates[3][3] = "kato3";

        dates[4][0] = "5";
        dates[4][1] = "mato1";

        idAndDateMap.put(1, dates);
/*
        int cols = 5; // 5 ids
        int rows = dates.length;

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if(dates[i][j] == null){ break;}
                for (int n=0; n<ids.size(); n++){
                    String id = ids.get(n).toString();
                    if(dates[i][0].equals(id)) {
                        System.out.print(dates[i][j + 1] + " ");
                    }
                }
            }
            System.out.println();
        }*/


        String queryPersoner = "SELECT person_id, navn, email FROM Personer";
        String queryDatoer = "SELECT person_id, dato FROM datoer";

        try {

            Connection con = dbconnect.getConnection();

            //query Personer
            PreparedStatement prstmPersoner = con.prepareStatement(queryPersoner);
            ResultSet resultSetPersoner = prstmPersoner.executeQuery();
            ResultSetMetaData resMetaPersoner = resultSetPersoner.getMetaData();

            while (resultSetPersoner.next()) {
                int id = resultSetPersoner.getInt(1);
                String name = resultSetPersoner.getString(2);
                String email = resultSetPersoner.getString(3);
                ids.add(id);
                names.add(name);
                emails.add(email);
            }

            //query Datoer
            PreparedStatement prstmtDatoer = con.prepareStatement(queryDatoer);
            ResultSet resultSetDatoer = prstmtDatoer.executeQuery();
            ResultSetMetaData resMetaDato = resultSetDatoer.getMetaData();


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
                            "<th colspan = \"25\">" + resMetaDato.getColumnName(2) + "</th>\n" +
                            "</tr>\n");
            for (int i = 0; i < ids.size(); i++) {
                String id = ids.get(i).toString();
                sB.append("<tr>" +
                        "<td>" + id + "</td>" +
                        "<td>" + names.get(i) + "</td>" +
                        "<td>" + emails.get(i) + "</td>");

                int cols = ids.size();
                int rows = dates.length;

                for (int k = 0; k < cols; k++) {
                    for (int j = 0; j < rows; j++) {
                        if(dates[k][j] == null){ break;}
                            if(dates[k][0].equals(id)) {
                                sB.append("<td>"+ dates[k][j] + "</td>");
                            }
                    }
                    System.out.println();
                }
                sB.append("</tr>\n");
            }
            sB.append("</table>\n"
                    + "</body>\n"
                    + "</html>");

            File htmlTemplateFile = new File(
                    "C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\src\\main\\frontend\\index.html");
            FileUtils.writeStringToFile(htmlTemplateFile, sB.toString());
            FileUtils.readFileToString(htmlTemplateFile);


        } catch (IOException | SQLException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }

    }
}






