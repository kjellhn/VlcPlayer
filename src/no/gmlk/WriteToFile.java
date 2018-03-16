package no.gmlk;

import java.io.*;

/**
 * Created by kno on 15.03.2018.
 */


public class WriteToFile {


    private static final String FILE_NAME = "src/resources/test.bat";
    private static final String START_VLC = "start VLC ";
    private static String pathToMovie = "C:\\Users\\kjell\\Documents\\Gammelkinoen ";
    private static final String VLC_COMMANDS = "--fullscreen --loop --video-on-top --no-video-deco --no-spu --qt-fullscreen-screennumber=1";
    private static String numScreen = "1";


    public void main(String[] args) throws IOException, InterruptedException {


        writeToFile();

        runVlc();
    }

    public static void writeToFile() throws IOException {
        String newLine = System.lineSeparator();
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)));
            out.print(START_VLC + pathToMovie + VLC_COMMANDS + numScreen + newLine);

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
