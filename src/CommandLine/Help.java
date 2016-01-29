package CommandLine;

import CommandLine.*;


public class Help {

    private static String[] commands = {"dir", "cd [dir}", "pwd", "copy", "run", "exit", "hdd", "md"};
    public static void printHelp() {
        for (String s : commands) {
            CommandLine.getPrintWriter().println(s);
        }
    }
}
