package no.gmlk;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by kno on 06.03.2018.
 */
public class ConfirmBox {
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMaxWidth(110);
        window.setMaxHeight(150);
        window.setAlwaysOnTop(true);
        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Ja");
        Button noButton = new Button("Nei");

        yesButton.setOnAction(event -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(event -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        layout.setMinWidth(110);
        layout.setMinHeight(150);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }

}
