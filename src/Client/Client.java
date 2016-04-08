package Client;

import java.net.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.Scanner;


public class Client {

    private static ServerSocket serverSocket;
    private static Socket commandLineSocket;
    private static PrintWriter commandLinePrintWriter;
    private static PrintWriter localPrintWriter = new PrintWriter(System.out, true);
    private static Scanner commandLineInputScanner;
    private static Scanner keyboardScanner = new Scanner(System.in);
    private static final String SYSTEM_WAY_SEPARATOR = System.getProperty("file.separator");
    private static File destinationDirectory = new File("Downloads");
    private static final int PORT = 7442;

    //A thread for commands sending
    private static Thread outputTread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (commandLineSocket.isConnected()) {
                String output = keyboardScanner.nextLine();
                commandLinePrintWriter.println(output);
            }
        }
    });
    //A thread for result reading
    private static Thread inputThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (commandLineSocket.isConnected()) {
                    String input = commandLineInputScanner.nextLine();
                    localPrintWriter.println(input);
                    if (input.equals("Copying...")) {
                        readFile();
                    }
            }
        }
    });

    public static void startClient() {
        try {
            //Command Line connection
            serverSocket = new ServerSocket(PORT);
            commandLineSocket = serverSocket.accept();
            commandLineInputScanner = new Scanner(commandLineSocket.getInputStream());
            commandLinePrintWriter = new PrintWriter(commandLineSocket.getOutputStream(), true);
            //Creating a directory for downloaded files
            if (!destinationDirectory.canRead()) {
                destinationDirectory.mkdir();
            }
            //Running a thread for result reading
            inputThread.start();
            //Running a thread for commands sending
            outputTread.start();
            System.out.println("Waiting for JCL");
        } catch (IOException e) {
            localPrintWriter.println("Server offline.");
        }

    }

    //A method for receiving files, directories or an entire tree of directories
    private static void readFile() {
        try {
            //Opening a socket for file receiving
            Socket downloadsSocket = serverSocket.accept();
            //Obtaining file's name
            String fileName = new DataInputStream(downloadsSocket.getInputStream()).readUTF();
            //Obtaining file's directory name
            String directoryName = new DataInputStream(downloadsSocket.getInputStream()).readUTF();
            //Checking, if a file or a directory
            boolean isDirectory = new DataInputStream(downloadsSocket.getInputStream()).readBoolean();
            //Receiving a file
            if (isDirectory) {
                new File(destinationDirectory.getName() + SYSTEM_WAY_SEPARATOR + directoryName
                        + SYSTEM_WAY_SEPARATOR + fileName).mkdir();
            } else if (directoryName.equals("")) {
                Files.copy(downloadsSocket.getInputStream(), new File(destinationDirectory.getName()
                        + SYSTEM_WAY_SEPARATOR + fileName).toPath());
            } else {
                Files.copy(downloadsSocket.getInputStream(), new File(destinationDirectory.getName()
                        + SYSTEM_WAY_SEPARATOR + directoryName + SYSTEM_WAY_SEPARATOR
                        + fileName).toPath());
            }
            downloadsSocket.close();
        } catch (FileAlreadyExistsException e) {
            localPrintWriter.println("File already exist.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
