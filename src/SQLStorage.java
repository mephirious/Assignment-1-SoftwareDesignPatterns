import java.sql.*;

public class SQLStorage extends Storage {

    private Connection connection;

    public SQLStorage() {
        try {
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String user = "root";
            String password = "password";

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
        }
    }

    @Override
    public void add(Person person) {
        String query = "INSERT INTO Person (id, fullName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getFullName());
            preparedStatement.setInt(3, person.getAge());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Person added to the database successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
        }
    }

    @Override
    public void getAll() {
        String query = "SELECT * FROM Person";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Error: list is empty");
                return;
            }

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("fullName");
                int age = resultSet.getInt("age");
                System.out.println(new Person(id, fullName, age).showInfo());
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
        }
    }

    @Override
    public void getOne(int id) {
        String query = "SELECT * FROM Person WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Error: No person found with id " + id);
                return;
            }

            while (resultSet.next()) {
                int personId = resultSet.getInt("id");
                String fullName = resultSet.getString("fullName");
                int age = resultSet.getInt("age");
                System.out.println(new Person(personId, fullName, age).showInfo());
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
        }
    }
}


