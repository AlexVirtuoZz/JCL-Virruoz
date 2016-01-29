package CommandLine;


import java.io.File;

public class FileNameBuilder {

    public static String buildFileName(String[] command) {
        String wantedDirectory = command[1];
        for (int i = 2; i < command.length; i++) {
            wantedDirectory = wantedDirectory + " " + command[i];
        }

        if (!wantedDirectory.startsWith(CommandLine.getStartedDirectory()))
            wantedDirectory = CommandLine.getCurrentDirectory() + CommandLine.getSystemWaySeparator() + wantedDirectory ;

        if (new File(wantedDirectory).canRead())
        return wantedDirectory;
        else {
            CommandLine.getPrintWriter().println("Can't find requested item");
            return "null";
        }
    }
}