package CommandLine;

import java.io.File;
import java.io.IOException;


public class CD {

    public static void changeDirectory(String[] command) throws IOException {
        String wantedDirectory = FileNameBuilder.buildFileName(command);

        //���������� ���� �� ����� ��������� �������� �����
        String test[] = wantedDirectory.split(":");
        if (test.length > 2) {
            CommandLine.getPrintWriter().println("Change your HDD first");
        }else
        //�������� ������� ����������
            CommandLine.setCurrentDirectory(wantedDirectory);
    }
}


