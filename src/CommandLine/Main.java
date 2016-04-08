package CommandLine;

import javax.swing.*;

public class Main extends JFrame{

    /*
            ***BEFORE RUNNING***
            * Make sure that client side is started and waiting for connection
            * Make sure that CLIENT_IP and CLIENT_PORT are correctly set
     */
    public static void main(String[] args) {
        //A frame for further improvements
        JFrame frame = new JFrame();
        frame.setVisible(true);
        CommandLine.startCommandLine();
    }

}
