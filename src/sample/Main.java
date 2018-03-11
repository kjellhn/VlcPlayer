import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static java.awt.SystemColor.window;


public class Main extends Application {

    Stage window;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Gammekinoen VLC");

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        button = new Button("Lukk programmet");
        button.setAlignment(Pos.BOTTOM_CENTER);
        button.setOnAction(event -> closeProgram());

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 500, 500);
        window.setScene(scene);
        window.show();
    }

    private void closeProgram() {
        System.out.println("Programmet er lukket");
        Boolean answer = ConfirmBox.display("Title", "Vil du avslutte?");
        if (answer)
        window.close();
    }
}
