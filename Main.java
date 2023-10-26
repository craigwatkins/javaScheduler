import DAO.DBConnect;
import Utilities.Utilities;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;


public class Main extends Application {
    private Utilities utility = new Utilities();

    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/login.fxml"));
        stage.setTitle("Scheduler");
        stage.setScene(new Scene(root, 960, 600));
        stage.show();
    }
    @FXML
    public void openFXMLView(ActionEvent actionEvent) throws IOException {
        utility.openFXMLView(actionEvent);
    }

    public static void main(String[] args) throws SQLException {
        DBConnect.makeConnection();
        launch(args);
    }
    @Override
    public void stop() {
        // Called when the window is closed
        closeDatabaseConnection();
    }
    public void exitApplication(ActionEvent actionEvent) {
        // Called from the "Exit" button
        closeDatabaseConnection();
        System.exit(0);
    }

    private void closeDatabaseConnection() {
        try {
            Connection connection = DBConnect.getInstance().makeConnection();
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            // Handle any database-related exceptions while closing the connection
            e.printStackTrace();
        }
    }

}
