package CommandLine;


import java.io.File;

public class FileNameBuilder {

    public static String buildFileName(String[] command) {
        //Unites obtained command
        String wantedDirectory = command[1];
        for (int i = 2; i < command.length; i++) {
            wantedDirectory = wantedDirectory + " " + command[i];
        }

        //If a command doesn't consist full way to file - this method appends it
        if (!wantedDirectory.startsWith(CommandLine.getStartDirectory()))
            wantedDirectory = CommandLine.getCurrentDirectory() + CommandLine.getSystemWaySeparator() + wantedDirectory ;

        //Returns ready command for the further processing
        if (new File(wantedDirectory).canRead())
        return wantedDirectory;
        else {
            CommandLine.getPrintWriter().println("Can't find requested item");
            return "null";
        }
    }
}