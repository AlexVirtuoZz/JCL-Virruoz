package CommandLine;


import java.io.*;
import java.net.Socket;
import java.nio.file.Files;


public class Copy {

    public static void copy(String[] command, String currentDirectory) throws IOException {
        String fileToCopyName = FileNameBuilder.buildFileName(command);

        //Defines a file or a directory for sending
        File fileToCopy = new File(fileToCopyName);
            //Defines if a file or a directory
            if (fileToCopy.isDirectory())
                copyDirectory(fileToCopy, "");
             else
                copyFile(fileToCopy, "");
        }

    //Method for file sending
    private static void copyFile(File fileToCopy, String fileDirectory) throws IOException {
        //Sends a mark to start copy method on a client side
        CommandLine.getPrintWriter().println("Copying...");
        //Creates a temporary socket
        Socket uploadSocket = new Socket(CommandLine.getClientIp(), CommandLine.getClientPort());
        //Sends all information about a file
        new DataOutputStream(uploadSocket.getOutputStream()).writeUTF(fileToCopy.getName());
        new DataOutputStream(uploadSocket.getOutputStream()).writeUTF(fileDirectory);
        new DataOutputStream(uploadSocket.getOutputStream()).writeBoolean(fileToCopy.isDirectory());
        if (!fileToCopy.isDirectory()) {
            Files.copy(fileToCopy.toPath(), uploadSocket.getOutputStream());
        }
        //If downloading succeeded
        CommandLine.getPrintWriter().println("Successful.");
        uploadSocket.close();
    }

    //Method for directory sending
    private static void copyDirectory(File directory, String dirWay) throws IOException {
        copyFile(directory, dirWay);
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                copyDirectory(file, dirWay + directory.getName() + "/");
            } else {
                copyFile(file, dirWay + directory.getName() + "/");
            }
        }
    }
}
