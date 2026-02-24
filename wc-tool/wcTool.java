import java.io.*;
import java.util.*;

public class wcTool {
    public long bytes;
    public long lines;
    public long words;


    public void processBytes(String fileName) throws IOException{
        try (FileInputStream fis = new FileInputStream((fileName))){
            long byteCount = 0;
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                byteCount += bytesRead;
            }

            this.bytes = (int) byteCount;
        }
    }

    //FIXME - new line character doesnt register as new line
    public void processLines(String fileName) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            long lineCount = 0;
            String line;

            while((line = br.readLine()) != null) {
                lineCount++;
            }

            this.lines = (int) lineCount;
        }
    }

    public void processWords(String fileName) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            long wordCount = 0;
            String line;

            while((line = br.readLine()) != null) {
                String[] wordsInLine = line.split("\\s+");
                int count = wordsInLine.length;
                wordCount += count;
            }

            this.words = (int) wordCount;
        }
    }


    public static void main(String[] args) {
        //if there is command line arguments
        if (args.length > 0) {
            wcTool tool = new wcTool();
            for(int i = 0; i < args.length; i ++) {
                if(args[i].equals("-c")) { //number of bytes
                    try {
                        tool.processBytes(args[i + 1]);
                        System.out.println(tool.bytes);
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                }
                else if(args[i].equals("-l")) { //number of lines
                    try {
                        tool.processLines(args[i + 1]);
                        System.out.println(tool.lines);
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                }
                else if(args[i].equals("-w")) { //number of words
                    try {
                        tool.processWords(args[i + 1]);
                        System.out.println(tool.words);
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                }
            }
        }
        else {
            System.out.println("There is no input, try again");
        }
    }
}
