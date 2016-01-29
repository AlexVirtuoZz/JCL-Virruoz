package CommandLine;

import java.io.File;
import java.io.IOException;


public class CD {

    public static void changeDirectory(String[] command) throws IOException {
        String wantedDirectory = FileNameBuilder.buildFileName(command);

        //Обезопасим себя от ввода неверного жесткого диска
        String test[] = wantedDirectory.split(":");
        if (test.length > 2) {
            CommandLine.getPrintWriter().println("Change your HDD first");
        }else
        //Изменяем текущую директорию
            CommandLine.setCurrentDirectory(wantedDirectory);
    }
}


