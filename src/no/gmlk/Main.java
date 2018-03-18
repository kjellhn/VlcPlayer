package no.gmlk;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public final class Main extends Application {

    Stage window;
    Button btnClose;
    String filePath;
    String path;
    String screenNumber;
    private static final String FILE_NAME = "src/resources/test.bat";
    private static final String START_VLC = "start VLC ";
    private static final String VLC_COMMANDS = " --fullscreen --loop --video-on-top --no-video-deco --no-spu --qt-fullscreen-screennumber= ";

    final FileChooser fileChooser = new FileChooser();
    private ArrayList list = new ArrayList();


    final Menu menu = new Menu("Options");
    MenuBar menuBar = new MenuBar();

    final Button start = new Button("Start");
    final Button opnbtn1 = new Button("Skjerm 1");
    final Button opnbtn2 = new Button("Skjerm 2");
    final Button opnbtn3 = new Button("Skjerm 3");
    final Button opnbtn4 = new Button("Skjerm 4");
    final Button opnbtn5 = new Button("Skjerm 5");
    final Button opnbtn6 = new Button("Skjerm 6");
    final Button opnbtn7 = new Button("Skjerm 7");
    final Button opnbtn8 = new Button("Skjerm 8");
    final Label lbl1 = new Label("");
    final Label lbl2 = new Label("");
    final Label lbl3 = new Label("");
    final Label lbl4 = new Label("");
    final Label lbl5 = new Label("");
    final Label lbl6 = new Label("");
    final Label lbl7 = new Label("");
    final Label lbl8 = new Label("");
    final Button remove1 = new Button("Slett");
    final Button remove2 = new Button("Slett");
    final Button remove3 = new Button("Slett");
    final Button remove4 = new Button("Slett");
    final Button remove5 = new Button("Slett");
    final Button remove6 = new Button("Slett");
    final Button remove7 = new Button("Slett");
    final Button remove8 = new Button("Slett");


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
//        Scene scene = new Scene(borderPane, 300, 250, Color.WHITE);

        menuBar.prefWidthProperty().bind(stage.widthProperty());
        borderPane.setTop(menuBar);

        window = stage;
        window.setTitle("Gammekinoen VLC");
        window.setMinWidth(800);
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        // File menu - new, save, exit
        Menu fileMenu = new Menu("File");
        MenuItem openMenuItem = new MenuItem("Åpne");
        openMenuItem.setOnAction(event -> {
            openFile(openMenuItem, fileChooser);
            readFromFile(filePath);
        });
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().addAll(openMenuItem, saveMenuItem,
                new SeparatorMenuItem(), exitMenuItem);
        menuBar.getMenus().addAll(fileMenu);


        opnbtn1.setId("1");
        opnbtn2.setId("2");
        opnbtn3.setId("3");
        opnbtn4.setId("4");
        opnbtn5.setId("5");
        opnbtn6.setId("6");
        opnbtn7.setId("7");
        opnbtn8.setId("8");
        remove1.setVisible(false);
        remove2.setVisible(false);
        remove3.setVisible(false);
        remove4.setVisible(false);
        remove5.setVisible(false);
        remove6.setVisible(false);
        remove7.setVisible(false);
        remove8.setVisible(false);

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
        GridPane.setConstraints(remove1, 2, 0);
        GridPane.setConstraints(remove2, 2, 1);
        GridPane.setConstraints(remove3, 2, 2);
        GridPane.setConstraints(remove4, 2, 3);
        GridPane.setConstraints(remove5, 2, 4);
        GridPane.setConstraints(remove6, 2, 5);
        GridPane.setConstraints(remove7, 2, 6);
        GridPane.setConstraints(remove8, 2, 7);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);

        inputGridPane.getChildren().addAll(
                start,
                opnbtn1, opnbtn2, opnbtn3, opnbtn4, opnbtn5, opnbtn6, opnbtn7, opnbtn8,
                lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8,
                remove1, remove2, remove3, remove4, remove5, remove6, remove7, remove8);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(menuBar, inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        window.setScene(new Scene(rootGroup));
        window.show();

        start.setOnAction(
                event -> {
                    buildFile();
                    try {
                        sendToFile(list);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        runVlc();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        opnbtn1.setOnAction(
                e -> {

                    handle(opnbtn1, fileChooser);
                    lbl1.setText(filePath);
                    lbl1.setId("1");
                    remove1.setVisible(true);
                });

        opnbtn2.setOnAction(
                e -> {
                    handle(opnbtn2, fileChooser);
                    System.out.println(filePath);
                    lbl2.setText(filePath);
                    lbl2.setId("2");
                    remove2.setVisible(true);

                });

        opnbtn3.setOnAction(
                event -> {
                    handle(opnbtn3, fileChooser);
                    lbl3.setText(filePath);
                    lbl3.setId("3");
                    remove3.setVisible(true);

                });

        opnbtn4.setOnAction(
                e -> {
                    handle(opnbtn4, fileChooser);
                    lbl4.setText(filePath);
                    lbl4.setId("4");
                    remove4.setVisible(true);

                });

        opnbtn5.setOnAction(
                e -> {
                    handle(opnbtn5, fileChooser);
                    lbl5.setText(filePath);
                    lbl4.setId("5");
                    remove2.setVisible(true);
                });

        opnbtn6.setOnAction(
                e -> {
                    handle(opnbtn6, fileChooser);
                    lbl6.setText(filePath);
                    lbl4.setId("6");
                    remove6.setVisible(true);
                });

        opnbtn7.setOnAction(
                e -> {
                    handle(opnbtn7, fileChooser);
                    lbl7.setText(filePath);
                    lbl4.setId("7");
                    remove7.setVisible(true);
                });

        opnbtn8.setOnAction(
                e -> {
                    handle(opnbtn8, fileChooser);
                    lbl8.setText(filePath);
                    lbl4.setId("8");
                    remove8.setVisible(true);
                });

        remove1.setOnAction(
                event -> {
                    lbl1.setText("");
                    lbl1.setId(null);
                    remove1.setVisible(false);
                });
        remove2.setOnAction(
                event -> {
                    lbl2.setText("");
                    lbl2.setId(null);
                    remove2.setVisible(false);
                });
        remove3.setOnAction(
                event -> {
                    lbl3.setText("");
                    lbl3.setId(null);
                    remove3.setVisible(false);
                });
        remove4.setOnAction(
                event -> {
                    lbl4.setText("");
                    lbl4.setId(null);
                    remove4.setVisible(false);
                });
        remove5.setOnAction(
                event -> {
                    lbl5.setText("");
                    lbl5.setId(null);
                    remove5.setVisible(false);
                });
        remove6.setOnAction(
                event -> {
                    lbl6.setText("");
                    lbl6.setId(null);
                    remove6.setVisible(false);
                });
        remove7.setOnAction(
                event -> {
                    lbl7.setText("");
                    lbl7.setId(null);
                    remove7.setVisible(false);
                });
        remove8.setOnAction(
                event -> {
                    lbl8.setText("");
                    lbl8.setId(null);
                    remove8.setVisible(false);
                });


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

    private static void configureMovieFileChooser(final FileChooser fileChooser) {

        fileChooser.setTitle("Velg en film");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Film", "*.mkv", "*.mp4", "*.MOV"));


    }

    private static void configureBatFileChooser(final FileChooser fileChooser) {

        fileChooser.setTitle("Velg fil");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".bat", "*.bat"));


    }

    public String openFile(MenuItem menuItem, final FileChooser fileChooser) {
        configureBatFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            file.getAbsolutePath();
            filePath = file.getAbsolutePath();
        }
        return filePath;
    }

    public String handle(Button button, final FileChooser fileChooser) {
        configureMovieFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            file.getAbsolutePath();
            filePath = file.getAbsolutePath();
        }
        return filePath;
    }

    public void buildFile() {

        if (!list.isEmpty())
            list.clear();

        String newLine = System.lineSeparator();

        if (lbl1.getId() != null)
            list.add(START_VLC + lbl1.getText() + VLC_COMMANDS + opnbtn1.getId() + newLine);
        if (lbl2.getId() != null)
            list.add(START_VLC + lbl2.getText() + VLC_COMMANDS + opnbtn2.getId() + newLine);
        if (lbl3.getId() != null)
            list.add(START_VLC + lbl3.getText() + VLC_COMMANDS + opnbtn3.getId() + newLine);
        if (lbl4.getId() != null)
            list.add(START_VLC + lbl4.getText() + VLC_COMMANDS + opnbtn4.getId() + newLine);
        if (lbl5.getId() != null)
            list.add(START_VLC + lbl5.getText() + VLC_COMMANDS + opnbtn5.getId() + newLine);
        if (lbl6.getId() != null)
            list.add(START_VLC + lbl6.getText() + VLC_COMMANDS + opnbtn6.getId() + newLine);
        if (lbl7.getId() != null)
            list.add(START_VLC + lbl7.getText() + VLC_COMMANDS + opnbtn7.getId() + newLine);
        if (lbl8.getId() != null)
            list.add(START_VLC + lbl8.getText() + VLC_COMMANDS + opnbtn8.getId());


        System.out.println(list);

    }

    public static void sendToFile(List list) throws IOException {

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)));
//            if (FILE_NAME.getBytes()== null){
//                out.print("");
//            }else
            out.write(String.valueOf(list).replaceAll("[\\[\\]\\,]", ""));

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

    public static void readFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        List<String> fileList = new ArrayList<>();
        String strLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while (strLine != null)
            {
                strLine = br.readLine();
                sb.append(strLine);
                sb.append(System.lineSeparator());
                strLine = br.readLine();
                if (strLine==null)
                    break;
                fileList.add(strLine);
                sendToFile(fileList);
            }
        } catch (FileNotFoundException fnf) {
            System.err.println("Fant ikke fil");
        }catch (IOException ioe){
            System.err.println("Kan ikke lese filen");
        }


    }
}



