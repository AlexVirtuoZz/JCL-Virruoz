package CommandLine;

import CommandLine.CommandLine;

import java.io.File;


public class ChangeHDD {
    public static void changeHard(String a) {
        //��� ����� �������� ����� �������� ��������� ��������� ����������
        if (new File(a).canRead()) {
            CommandLine.setCurrentDirectory(a);
            CommandLine.setStartedDirectory(a);
            CommandLine.getPrintWriter().println("New HDD's been set");
        }
    }
}
