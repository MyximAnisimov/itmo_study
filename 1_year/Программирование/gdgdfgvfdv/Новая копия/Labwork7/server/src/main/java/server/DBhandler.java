package server;

import common.collections.Coordinates;
import common.collections.Country;
import common.collections.Location;
import common.collections.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;

public class DBhandler {
    private static String url="jdbc:postgresql://localhost:5433/studs";
    private static String user="s379791";
    private static String password="mK509Zk9bkpmQA7N";
    private static Connection connection;

        private CollectionManager collMan;
        static{
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }}


        private Statement statement = connection.createStatement();

    public DBhandler() throws SQLException {
    }

    public int checkUser(String username, String password) {
        int num_result=-1;
        try {
            String query = "select * from users where username=\'"+username+"\'";

            var res = statement.executeQuery(query);
            if (res.next()) {
                String pass = res.getString("password");
                if (!pass.equals(password)) {
                    num_result = -1;
                } else {res.getInt("id");
                num_result=1;}
            }
            return num_result;
        } catch (SQLException e) {
            return -1;
        }
    }

        private int setUser(String username, String password) {
            try {
                String query = "insert into users (username, password) values ('$username', '$password') returning id";
                var res = statement.executeQuery(query);
                res.getLong("id");
            } catch (SQLException e) {
                System.out.println("Ошибка");
                return -1;
            }
           return 1;
        }

        public Long addElement(Person person, Long user_id) throws SQLException{
            try {
                var query = coordinatesInsertQuery(person.getCoordinates());
                var res = statement.executeQuery(query);
                var coordinates_id = res.getLong("id");
                var location = person.getLocation();
               int location_id;
                if (location != null) {
                    query = locationInsertQuery(location);
                    res = statement.executeQuery(query);
                    location_id = res.getInt("id");
                }
                var nationality = person.getNationality();
//                query = countryInsertQuery(nationality);
//                res = statement.executeQuery(query);
//                var nationality_id = res.getInt("id");
                query = "insert into collection_elements (name,coordinates_id, creation_date, " +
                        "height, birthday, passportID, nationality, location, user_id) values " +
                        "('${person.getName()}',$coordinates_id, TO_TIMESTAMP('${person.getCreationDate()}','dd-mm-yyyy HH24:MI:ss'), " +
                        "'${person.getHeight()}','${person.getBirthday()', '${person.getPassportID()', $nationality_id, '${person.getLocation()}') returning id";
                res = statement.executeQuery(query);
                var ans = res.getLong("id");
                query = "update coordinates set id = $ans where id=$coordinates_id";
                executeUpdate(query);
                query = "update location set id = $ans where id=$location_id";
                executeUpdate(query);
                query = "update nationality set id = $ans where id=$nationality_id";
                executeUpdate(query);
                return ans;
            } catch (SQLException e) {
                System.out.println("Ошибка");
            }
            return null;
        }

        public boolean removeElement(int id, int user_id) {
            try {
                var query = "select user_id from collection_elements where id=$id";
                var res = statement.executeQuery(query);
                if (res.getLong("user_id") != user_id)
                    return false;
                else {
                    query = "delete from collection_elements where id=$id";
                    executeUpdate(query);
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        public boolean clearCollection(int user_id) {
            try {
                var query = "delete from collection_elements where user_id=$user_id";
                statement.executeUpdate(query);
                return true;
            } catch (SQLException e) {
                System.out.println("Ошибка");
               return false;
            }
        }
        public boolean updateElement(Person person, int user_id) throws SQLException{
            try {
                var query = "select * from collection_elements where id=${element.getId()}";
                var res = statement.executeQuery(query);
                query = coordinatesInsertQuery(person.getCoordinates());
                res = statement.executeQuery(query);
                var coordinates_id = res.getLong("id");
                var location = person.getLocation();
                int location_id;
                if (location != null) {
                    query = locationInsertQuery(location);
                    res = statement.executeQuery(query);
                    location_id = res.getInt("id");
                }
//                var nationality = person.getNationality();
//                query = countryInsertQuery(nationality);
//                res = statement.executeQuery(query);
//                var nationality_id = res.getInt("id");
                if (res.getLong("user_id") != user_id)
                    return false;

                else {
                    query = "update coordinates set " +
                            "x=${person.getCoordinates().getX()}, " +
                            "y=${person.getCoordinates().getY()} " +
                            "where id=${res.getInt(coordinates_id)};\n";
                    query += "update location set " +
                            "x=${element.getLocation().getX()}, " +
                            "y=${element.getLocation().getY()}, " +
                            "z=${element.getLocation().getZ()}, " +
                            "name=${element.getLocation().getName()} " +
                            "where id=${res.getLong(location_id)};\n";
                    if (location != null) {
                        query += "update locations set " +
                                "x=${from.getX()}, " +
                                "y=${from.getY()}, " +
                                "z=${from.getZ()}, " +
                                "name=${from.getName()} " +
                                "where id=${res.getLong(location_id)};";
                    } else {
                        query += "delete from locations where id=${res.getLong(location_id)};";
                    }
//                    if (nationality_id == 0){
//                        query += "update country set " +
//                                "name=${from.getName()} " +
//                                "where id=${res.getLong(nationality_id)};";
//                }
//                    else {
//                        query+= "delete from country where id=${res.getLong(nationality_id)};";
//                    }
                    statement.executeUpdate(query);
                    query = "update collection_elements set " +
                            "name=${person.getName()}, " +
                            "coordinates_id=${if (coordintes == null) null else res.getLong(coordinates_id)} " +
                            "creationDate=${person.getCreationDate()}, " +
                            "height=${person.getHeight()}, " +
                            "birthday=${person.getBirthday()}, " +
                            "passportID=${person.getPassportID()}, " +
                            "nationality_id=${if (nationality == null) null else res.getLong(nationality_id)} " +
                            "location_id=${if (locations == null) null else res.getLong(location_id)} " +
                            "where id=${element.getId()};";
                    statement.executeUpdate(query);
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Ошибка");
               return false;
            }
        }
        private void executeUpdate(String s)throws SQLException
        { statement.executeUpdate(s); }
        private ResultSet executeQuery(String s, boolean withNext) throws SQLException {
            var res = statement.executeQuery(s);
            if (withNext) res.next();
            return res;
        }
        private String coordinatesInsertQuery(Coordinates coordinates){
    return "insert into coordinates (x, y) values ("+coordinates.getX() +", "+coordinates.getY()+") returning id";}
        private String locationInsertQuery(Location location) throws SQLException {
    return "insert into locations (x, y, z, name) values ("+location.getX()+", "+location.getY()+", "+location.getZ()+", "+location.getName()+") returning id";}
private String countryInsertQuery(Country country){
            return "insert into country (name) values ("+country.name()+"+ returning id";
}
    private Coordinates downloadCurrentCoordinates() throws SQLException {
        String query = "select * from collection_elements";
        var res = connection.createStatement().executeQuery(query);
        if(res.next()){
        var coordinates_id = res.getInt("coordinates_id");
        String query1 = "select * from coordinates where id ="+ coordinates_id+"";
        ResultSet res1;

res1 = connection.createStatement().executeQuery(query1);
if(res1.next()){

            return new Coordinates(
res1.getDouble("x"),
                res1.getInt("y")
            );}}
        return null;
    }
    private Location downloadCurrentLocation() throws SQLException {
        String query = "select * from collection_elements";
        var res = connection.createStatement().executeQuery(query);
        if(res.next()){
        var location_id = res.getString("location_id");
        String query1 = "select * from locations where id ="+ location_id+"";
        ResultSet res1;
        res1 = connection.createStatement().executeQuery(query1);
        if(res1.next()){
        return new Location(
                res1.getLong("x"),
                res1.getInt("y"),
                res1.getFloat("z"),
                res1.getString("name")
        );}}
        return null;
    }

        public Deque<Person> downloadCurrentCollection() throws SQLException {
            String query = "select * from collection_elements";
            var res = connection.createStatement().executeQuery(query);
            var ans = new ArrayDeque<Person>();
            ans.clear();
            while (res.next()) {
                var user_id = res.getLong("user_id");
                var location_id = res.getInt("location_id");
                var res1 = connection.createStatement().executeQuery(query);
                Coordinates coordinates;
                Country nationality;
                Location location;
if(res1.next()){
                ans.add(new Person(
                 res.getInt("id"),
                    res.getString("name"),
                      downloadCurrentCoordinates(),
                        LocalDate.now(),
                    res.getInt("height"),
                        res.getDate("creationdate"),
                    res.getString("passportid"),
                         res.getInt("nationality_id"),
                    downloadCurrentLocation(),
                  res1.getInt("user_id")));
                }}

            return ans;}
        }


