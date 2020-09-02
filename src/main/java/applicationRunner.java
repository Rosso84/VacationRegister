import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**ved bruk av 2d arrrays */

import Connections.DbConnect;
import org.apache.commons.io.FileUtils;

public class applicationRunner {

    static ArrayList<Person> personList = new ArrayList<>();


    public static void main(String[] args) {
        DbConnect dbconnect = new DbConnect();

        String queryPersoner = "SELECT person_id, navn, email FROM Personer";
        String queryDatoer = "SELECT person_id, dato FROM datoer";

        try {
            Connection con = dbconnect.getConnection();
            //query Personer
            PreparedStatement prstmPersons = con.prepareStatement(queryPersoner);
            ResultSet resultSetPersons = prstmPersons.executeQuery();
            ResultSetMetaData resMetaPersons = resultSetPersons.getMetaData();

            while (resultSetPersons.next()) {
                int id = resultSetPersons.getInt(1);
                String name = resultSetPersons.getString(2);
                String email = resultSetPersons.getString(3);
                setPerson(id, name, email);
            }

            printAllPerons();

            //query Datoer
            PreparedStatement prstmtDates = con.prepareStatement(queryDatoer);
            ResultSet resultSetDates = prstmtDates.executeQuery();
            ResultSetMetaData resMetaDates = resultSetDates.getMetaData();

            while (resultSetDates.next()) {
                int foreignKey = resultSetDates.getInt(1);
                String foreignKeyString = String.valueOf(foreignKey);
                String date = resultSetDates.getString(2);
                addDates(foreignKey, date);
            }

            printAllPeronsDates();

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
                            "<th> " + resMetaPersons.getColumnName(1) + " </th>\n" +
                            "<th>" + resMetaPersons.getColumnName(2) + "</th>\n" +
                            "<th>" + resMetaPersons.getColumnName(3) + "</th>\n" +
                            "<th colspan = \"25\">" + resMetaDates.getColumnName(2) + "</th>\n" +
                            "</tr>\n");

            for (int i = 0; i < personList.size(); i++) {
                Person p = personList.get(i);
                sB.append("<tr>" +
                        "<td>" + p.getId() + "</td>" +
                        "<td>" + p.getName() + "</td>" +
                        "<td>" + p.getEmail() + "</td>" +
                        "<td>" + p.getDates() + "</td>" +
                        "</tr>\n");
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

    static boolean personExists(int id) {
        for (int i = 0; i < personList.size(); i++) {
            Person p = personList.get(i);
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }

    static void setPerson(int id, String name, String email) {
        if (!personExists(id)) {
            personList.add(new Person(id, name, email, new ArrayList<>()));
            System.out.println("added Person with name: " + name + ", id: " + id);
        }
    }

    static void addDates(int foreignKey, String date) {
        for (int i = 0; i < personList.size(); i++) {
            Person p = personList.get(i);
            int primaryId = p.getId();
            if (foreignKey == primaryId) {
                p.getDates().add(date);
            } else {
                System.out.println("Date with foreignKey " + foreignKey + " does not exixts");
            }
        }
    }

    static void printAllPerons() {
        for (int i = 0; i < personList.size(); i++) {
            Person p = personList.get(i);
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getEmail() + " | " + p.getDates());
        }
    }

    static void printAllPeronsDates() {
        for (int i = 0; i < personList.size(); i++) {
            Person p = personList.get(i);
            List<String> dates = p.getDates();

                System.out.println(p.getId() + " | " + p.getName() + " | " + p.getEmail() + " | " + p.getDates());
        }
    }


}