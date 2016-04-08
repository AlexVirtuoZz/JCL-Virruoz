package CommandLine;

import java.io.IOException;
import CommandLine.*;


public class PWD {

    public static void printWayDirectory() throws IOException {
        //Shows current directory
        CommandLine.getPrintWriter().println(CommandLine.getCurrentDirectory());
    }

}
