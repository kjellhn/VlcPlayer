package no.gmlk;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;


public final class Main extends Application {

    Stage window;
    Button btnClose;
    String filePath;
    String path;
    String screenNumber;
    private static final String FILE_NAME = "src/no/gmlk/resources/test.bat";
    private static final String START_VLC = "start VLC ";
    private static final String VLC_COMMANDS = " --fullscreen --loop --video-on-top --no-video-deco --no-spu --qt-fullscreen-screennumber= ";
    final FileChooser fileChooser = new FileChooser();
    private ArrayList arrayList = new ArrayList();
    final Button start = new Button("Start");
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
    final Label lbl8 = new Label("7");


//    public static void main(String[] args) {
//        Application.launch(args);
//    }

    @Override
    public void start(final Stage stage) throws Exception {
        window = stage;
        window.setTitle("Gammekinoen VLC");
        window.setMinWidth(500);


        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        opnbtn1.setId("1");
        opnbtn2.setId("2");
        opnbtn3.setId("3");
        opnbtn4.setId("4");
        opnbtn5.setId("5");
        opnbtn6.setId("6");
        opnbtn7.setId("7");
        opnbtn8.setId("8");

        GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(opnbtn1, 0, 0);
        GridPane.setConstraints(opnbtn2, 0, 1);
        GridPane.setConstraints(opnbtn3, 0, 2);
        GridPane.setConstraints(opnbtn4, 0, 3);
        GridPane.setConstraints(opnbtn5, 0, 4);
        GridPane.setConstraints(opnbtn6, 0, 5);
        GridPane.setConstraints(opnbtn7, 0, 6);
        GridPane.setConstraints(opnbtn8, 0, 7);
        GridPane.setConstraints(start, 0, 8);
        GridPane.setConstraints(lbl1, 1, 0);
        GridPane.setConstraints(lbl2, 1, 1);
        GridPane.setConstraints(lbl3, 1, 2);
        GridPane.setConstraints(lbl4, 1, 3);
        GridPane.setConstraints(lbl5, 1, 4);
        GridPane.setConstraints(lbl6, 1, 5);
        GridPane.setConstraints(lbl7, 1, 6);
        GridPane.setConstraints(lbl8, 1, 7);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(
                start,
                opnbtn1, opnbtn2, opnbtn3, opnbtn4, opnbtn5, opnbtn6, opnbtn7, opnbtn8,
                lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8);

        start.setOnAction(
                event -> {

                    buildFile();
                    try {
                        sendToFile(arrayList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        opnbtn1.setOnAction(
                e -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn1, fileChooser);
                    lbl1.setText(filePath);
                    lbl1.setId("1");
                    System.out.println("LABEL 1 SIN ID ER: " + lbl1.getId());

                });

        opnbtn2.setOnAction(
                e -> {
//                    configureFileChooser(fileChooser);
                    handle(opnbtn2, fileChooser);
                    System.out.println(filePath);
                    lbl2.setText(filePath);
                    lbl2.setId("2");
                });

        opnbtn3.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn3, fileChooser);
                    lbl3.setText(filePath);
                    lbl3.setId("3");
                });

        opnbtn4.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn4, fileChooser);
                    lbl4.setText(filePath);
                });

        opnbtn5.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn5, fileChooser);
                    lbl5.setText(filePath);
                    System.out.println("LABEL 5 SIN ID ER: " + lbl5.getId());
                });

        opnbtn6.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn6, fileChooser);
                    lbl6.setText(filePath);
                });

        opnbtn7.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn7, fileChooser);
                    lbl7.setText(filePath);
                });
        opnbtn8.setOnAction(
                event -> {
                    configureFileChooser(fileChooser);
                    handle(opnbtn8, fileChooser);
                    lbl8.setText(filePath);
                });

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        window.setScene(new Scene(rootGroup));
        window.show();

//        btnClose = new Button("Lukk programmet");
//        btnClose.setAlignment(Pos.BOTTOM_RIGHT);
//        btnClose.setOnAction(event -> closeProgram());

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
            filePath = file.getAbsolutePath();
        }
        return filePath;
    }

    public void buildFile() {
        arrayList.clear();
        String newLine = System.lineSeparator();
        if (lbl1.getId() != null)
            arrayList.add(START_VLC + lbl1.getText() + VLC_COMMANDS + opnbtn1.getId() + newLine);
        if (lbl2.getId() != null)
            arrayList.add(START_VLC + lbl2.getText() + VLC_COMMANDS + opnbtn2.getId() + newLine);
        if (lbl3.getId() != null)
            arrayList.add(START_VLC + lbl3.getText() + VLC_COMMANDS + opnbtn3.getId() + newLine);
        if (lbl4.getId() != null)
            arrayList.add(START_VLC + lbl4.getText() + VLC_COMMANDS + opnbtn4.getId() + newLine);
        if (lbl5.getId() != null)
            arrayList.add(START_VLC + lbl5.getText() + VLC_COMMANDS + opnbtn5.getId() + newLine);
        if (lbl6.getId() != null)
            arrayList.add(START_VLC + lbl6.getText() + VLC_COMMANDS + opnbtn6.getId() + newLine);
        if (lbl7.getId() != null)
            arrayList.add(START_VLC + lbl7.getText() + VLC_COMMANDS + opnbtn7.getId() + newLine);
        if (lbl8.getId() != null)
            arrayList.add(START_VLC + lbl8.getText() + VLC_COMMANDS + opnbtn8.getId() + newLine);

        else {
            arrayList.remove(Character.valueOf('[') );
            arrayList.remove(Character.valueOf(']') );
            arrayList.remove(Character.valueOf(',') );
        }

        System.out.println(arrayList);

    }

    public static void sendToFile(ArrayList arrayList) throws IOException {

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)));
//            if (FILE_NAME.getBytes()== null){
//                out.print("");
//            }else
            out.write(String.valueOf(arrayList));

        } catch (FileNotFoundException nf) {
            System.err.println(nf);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    public static void runVlc() throws IOException, InterruptedException {
//        PrintWriter out = new PrintWriter("test.bat");
//        FileOutputStream outputStream = new FileOutputStream("");

        try {
            Process p = Runtime.getRuntime().exec("src/resources/test.bat");
            p.waitFor();

        } catch (FileNotFoundException fnf) {
            System.err.println(fnf.getMessage());
            //Validate the case the process is being stopped by some external situation
        }
    }
}



