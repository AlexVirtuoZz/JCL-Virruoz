package CommandLine;

import java.io.IOException;
import CommandLine.*;


public class PWD {

    public static void printWayDirectory() throws IOException {
        CommandLine.getPrintWriter().println(CommandLine.getCurrentDirectory());
    }

}
