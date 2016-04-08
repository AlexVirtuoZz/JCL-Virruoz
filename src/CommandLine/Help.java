package CommandLine;


public class Help {

    //Shows all available commands
    private static String[] commands = {
            "dir - shows everything that current folder containts",
            "cd [dir] - changes current directory for a specified one",
            "pwd - shows current directory",
            "copy [file] - downloads specified file ",
            "run [file] - executes specified program (only executable files available)",
            "executable format is " + CommandLine.getExecutableFormat(),
            "exit - closes the program",
            "hdd [HDD] - changes current HDD for a specified one",
            "md [dir] - creates a specified directory",
            "ip - shows server's IP address"};
    public static void printHelp() {
        for (String s : commands) {
            CommandLine.getPrintWriter().println(s);
        }
    }
}
