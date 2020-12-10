import java.io.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

public class Main {
    public static void main(String[] args) {
        new ProcessFiles(new FileStrategy(), "java").start(new String[]{"."});
    }

    private static class FileStrategy implements ProcessFiles.Strategy {

        private Date time;

        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2015, Calendar.JANUARY, 1);
            time = calendar.getTime();
        }

        @Override
        public void process(File file) {
            if (new Date(file.lastModified()).after(time)) {
                System.out.println(file);
            }

        }
    }
}