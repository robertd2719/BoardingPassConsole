package mainRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterTest {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("src/resources/outfile.txt");
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println("Hello Darkness my old friend");
        printWriter.close();
        writer.close();
    }
}
