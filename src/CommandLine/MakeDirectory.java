package CommandLine;

import java.io.File;


public class MakeDirectory {

    public static void makeDirectory(String[] command) {
        String wantedDirectory = FileNameBuilder.buildFileName(command);

        //Creates a new directory, if it doesn't exist
        if (!new File(wantedDirectory).exists()) {
           boolean dir = new File(wantedDirectory).mkdir();
        } else CommandLine.getPrintWriter().println("Can't create a directory");

    }
}
