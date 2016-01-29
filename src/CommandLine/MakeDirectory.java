package CommandLine;

import CommandLine.*;
import java.io.File;


public class MakeDirectory {

    public static void makeDirectory(String[] command) {
        String wantedDirectory = FileNameBuilder.buildFileName(command);

        if (!new File(wantedDirectory).exists()) {
           boolean dir = new File(wantedDirectory).mkdir();
        } else CommandLine.getPrintWriter().println("Can't create a directory");

    }
}
