package no.gmlk;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public final class Main extends Application {

    Stage window;
    Button btnClose;
    String fileName;
    String path;
    final FileChooser fileChooser = new FileChooser();


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        window = stage;
        window.setTitle("Gammekinoen VLC");
        construct();

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

//        btnClose = new Button("Lukk programmet");
//        btnClose.setAlignment(Pos.BOTTOM_RIGHT);
//        btnClose.setOnAction(event -> closeProgram());

    }

    public void construct() {
        final Button opnbtn1 = new Button("Skjerm 1");
        final Button opnbtn2 = new Button("Skjerm 2");
        final Button opnbtn3 = new Button("Skjerm 3");
        final Button opnbtn4 = new Button("Skjerm 4");
        final Button opnbtn5 = new Button("Skjerm 5");
        final Button opnbtn6 = new Button("Skjerm 6");
        final Button opnbtn7 = new Button("Skjerm 7");
        final Button opnbtn8 = new Button("Skjerm 8");
        final Label lbl1 = new Label("1");
        final Label lbl2 = new Label("2");
        final Label lbl3 = new Label("3");
        final Label lbl4 = new Label("4");
        final Label lbl5 = new Label("5");
        final Label lbl6 = new Label("6");
        final Label lbl7 = new Label("7");

        GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(opnbtn1, 0, 0);
        GridPane.setConstraints(opnbtn2, 0, 1);
        GridPane.setConstraints(opnbtn3, 0, 2);
        GridPane.setConstraints(opnbtn4, 0, 3);
        GridPane.setConstraints(opnbtn5, 0, 4);
        GridPane.setConstraints(opnbtn6, 0, 5);
        GridPane.setConstraints(opnbtn7, 0, 6);
        GridPane.setConstraints(opnbtn8, 0, 7);
        GridPane.setConstraints(lbl1, 1, 0);
        GridPane.setConstraints(lbl2, 1, 1);
        GridPane.setConstraints(lbl3, 1, 2);
        GridPane.setConstraints(lbl4, 1, 3);
        GridPane.setConstraints(lbl5, 1, 4);
        GridPane.setConstraints(lbl6, 1, 5);
        GridPane.setConstraints(lbl7, 1, 6);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(
                opnbtn1, opnbtn2, opnbtn3, opnbtn4, opnbtn5, opnbtn6, opnbtn7,
                lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7);

        opnbtn1.setOnAction(
                e -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn1, fileChooser);
                    lbl1.setText(fileName);
                    try {
                        WriteToFile.sendToFile(path);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                });

        opnbtn2.setOnAction(
                e -> {
//                    configureFileChooser(fileChooser);
                    handle(opnbtn2, fileChooser);
                    System.out.println(fileName);
                    lbl2.setText(fileName);
                });

        opnbtn3.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn3, fileChooser);
                    lbl3.setText(fileName);
                });

        opnbtn4.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn4, fileChooser);
                    lbl4.setText(fileName);
                });

        opnbtn5.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn5, fileChooser);
                    lbl5.setText(fileName);
                });

        opnbtn5.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn5, fileChooser);
                    lbl5.setText(fileName);
                });

        opnbtn6.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn6, fileChooser);
                    lbl6.setText(fileName);
                });

        opnbtn7.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn7, fileChooser);
                    lbl7.setText(fileName);
                });

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        window.setScene(new Scene(rootGroup));
        window.show();


    }

    private void closeProgram() {
        System.out.println("Programmet er lukket");
        Boolean answer = ConfirmBox.display("Title", "Vil du avslutte?");
        if (answer)
            window.close();
    }

    private static void configureFileChooser(final FileChooser fileChooser) {

        fileChooser.setTitle("Velg en film");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Film", "*.mkv", "*.mp4", "*.MOV"));


    }

    public String handle(Button button, final FileChooser fileChooser) {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            file.getAbsolutePath();
            fileName = file.getName();
            path = file.getAbsolutePath();

            System.out.println("PATH: " + file.getAbsolutePath());


        }
        return fileName;
    }
}



