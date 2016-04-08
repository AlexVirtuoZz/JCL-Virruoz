package CommandLine;

import java.io.IOException;


public class CD {

    public static void changeDirectory(String[] command) throws IOException {
        String wantedDirectory = FileNameBuilder.buildFileName(command);

        //Checking, if you are on a right HDD
        String test[] = wantedDirectory.split(":");
        if (test.length > 2) {
            CommandLine.getPrintWriter().println("Change your HDD first");
        }else
        //Changing your current directory
            CommandLine.setCurrentDirectory(wantedDirectory);
    }
}


