import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static java.lang.System.exit;

public class HandbookClientApp extends Application {
    private static final Logger log = LogManager.getLogger(HandbookClientApp.class);

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(HandbookClientApp.class.getResource("form.fxml"));
            primaryStage.setTitle("J2EE Handbook");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        }
        catch (Exception exception) {
            log.fatal(exception.getMessage());
            exit(1);
        }
    }
}
