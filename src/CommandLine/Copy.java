package CommandLine;


import java.io.*;
import java.net.Socket;
import java.nio.file.Files;


public class Copy {

    public static void copy(String[] command, String currentDirectory) throws IOException {
        String fileToCopyName = FileNameBuilder.buildFileName(command);

        //Определяем файл для копирования
        File fileToCopy = new File(fileToCopyName);
            //В зависимости, файл это или папка, выбираем метод
            if (fileToCopy.isDirectory())
                copyDirectory(fileToCopy, "");
             else
                copyFile(fileToCopy, "");
        }


    private static void copyFile(File fileToCopy, String fileDirectory) throws IOException {

        CommandLine.getPrintWriter().println("Copying...");
        Socket uploadSocket = new Socket(CommandLine.getClientIp(), CommandLine.getClientPort());
        new DataOutputStream(uploadSocket.getOutputStream()).writeUTF(fileToCopy.getName());
        new DataOutputStream(uploadSocket.getOutputStream()).writeUTF(fileDirectory);
        new DataOutputStream(uploadSocket.getOutputStream()).writeBoolean(fileToCopy.isDirectory());
        if (!fileToCopy.isDirectory()) {
            Files.copy(fileToCopy.toPath(), uploadSocket.getOutputStream());
        }
        CommandLine.getPrintWriter().println("Successful.");
        uploadSocket.close();
    }

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
