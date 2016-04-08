package CommandLine;

import java.io.File;
import java.io.IOException;

public class Run {

    public static void executeCommand(String[] command) throws IOException {
        String wantedFile = FileNameBuilder.buildFileName(command);

        //Checks, if a file's got correct format, then executes it
        if (new File(wantedFile).getName().endsWith(CommandLine.getExecutableFormat()))
            new ProcessBuilder(wantedFile).start();
        //If format is incorrect
        else CommandLine.getPrintWriter().println("Please choose executable file (.exe for Windows or .deb for Linux)");
    }
}
