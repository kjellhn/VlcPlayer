package no.gmlk;

import java.io.IOException;

public class ButtonHandler {

    void buttonHandler() {

    }

    public static void runVlc(ProcessBuilder processBuilder) throws InterruptedException {

        try {
            Process p = new ProcessBuilder("src/resources/test.bat").start();
            p.waitFor();
            long pid = p.pid();
            System.out.println(pid);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            //Validate the case the process is being stopped by some external situation
        }
    }

    public static void killVlc(String appName) {
        ProcessHandle
                .allProcesses()
                .filter(process -> isApplication(appName, process))
                .forEach(process ->
                        process.info().command().ifPresent(command ->
                                closeAndLog(process, command)));
    }
    static void closeAndLog(ProcessHandle process, String command) {
        String status = process.destroyForcibly() ? " Success!" : " Failed";
        System.out.println("Killing ... " + command + status);
    }
    static boolean isApplication(final String appName, final ProcessHandle process) {
        return process.info().command().filter(command ->
                command.contains(appName)).isPresent();
    }

}
