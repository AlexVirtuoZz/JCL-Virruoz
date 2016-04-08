package CommandLine;

import java.io.File;


public class ChangeHDD {
    public static void changeHard(String hdd) {
        //Changing your current HDD, if it's available
        if (new File(hdd).canRead()) {
            CommandLine.setCurrentDirectory(hdd);
            CommandLine.setStartDirectory(hdd);
            CommandLine.getPrintWriter().println("New HDD's been set");
        }
    }
}
