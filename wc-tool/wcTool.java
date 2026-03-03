import java.io.*;
import java.util.*;

public class wcTool {
    public long bytes;
    public long lines;
    public long words;
    public long characters;


    public void processBytes(String fileName) throws IOException{
        try (FileInputStream fis = new FileInputStream((fileName))){
            processBytes(fis);
        }
    }

    public void processBytes(InputStream in) throws IOException { //filename not provided
        long byteCount = 0;
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                byteCount += bytesRead;
            }

            this.bytes = (int) byteCount;
    }

    public void processLines(String fileName) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            processLines(br);
        }
    }

    public void processLines(Reader r) throws IOException { //filename not provided
        BufferedReader br = (r instanceof BufferedReader)
            ? (BufferedReader) r
            : new BufferedReader(r);

        long lineCount = 0;
        String line;

        while((line = br.readLine()) != null) {
            lineCount++;
        }

        this.lines = (int) lineCount;
    }

    public void processWords(String fileName) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            processWords(br);
        }
    }

    public void processWords(Reader r) throws IOException { //filename not provided
        BufferedReader br = (r instanceof BufferedReader)
        ? (BufferedReader) r
        : new BufferedReader(r);

        int wordCount = 0;
            String line;

            while((line = br.readLine()) != null) {
                line = line.trim();
                if(line.isEmpty()) {
                    continue;
                }
                String[] wordsInLine = line.split("\\s+");
                int count = wordsInLine.length;
                wordCount += count;
            }

            this.words = (int) wordCount;

    }

    public void processChars(String fileName) throws IOException{
        try (FileReader fr = new FileReader(fileName)){
            processChars(fr);
        }
    }

    public void processChars(Reader r) throws IOException { //filename not provided

        long charCount = 0;
            int ch = 0;
            while(r.read() != -1) {
                charCount++;
            }

            this.characters = (int) charCount;
    }


    public static void main(String[] args) {
        //if there is command line arguments
        if (args.length > 0) {
            wcTool tool = new wcTool();
            for(int i = 0; i < args.length; i ++) {
                if(args[i].equals("-c")) { //number of bytes
                    try {
                        if ( i + 1 < args.length && !args[i + 1].startsWith("-")) { //filename is after flag
                            tool.processBytes(args[i + 1]);
                        }
                        else { //no filename, read from stdin
                            tool.processBytes(System.in);
                        }
                        System.out.println(tool.bytes);
                        i++;
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                }
                else if(args[i].equals("-l")) { //number of lines
                    try {
                        if ( i + 1 < args.length && !args[i + 1].startsWith("-")) { //filename is after flag
                            tool.processLines(args[i + 1]);
                        }
                        else { //no filename, read from stdin
                            tool.processLines(new InputStreamReader(System.in));
                        }
                        System.out.println(tool.lines);
                        i++;
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                }
                else if(args[i].equals("-w")) { //number of words
                    try {
                        if ( i + 1 < args.length && !args[i + 1].startsWith("-")) { //filename is after flag
                            tool.processWords(args[i + 1]);
                        }
                        else { //no filename, read from stdin
                            tool.processWords(new InputStreamReader(System.in));
                        }
                        System.out.println(tool.words);
                        i++;
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                }
                else if(args[i].equals("-m")) { //number of characters
                    try {
                        if ( i + 1 < args.length && !args[i + 1].startsWith("-")) { //filename is after flag
                            tool.processChars(args[i + 1]);
                        }
                        else { //no filename, read from stdin
                            tool.processChars(new InputStreamReader(System.in));
                        }
                        System.out.println(tool.characters);
                        i++;
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                }
                else { //no options
                    try {
                        tool.processBytes(args[i]);
                        tool.processLines(args[i]);
                        tool.processWords(args[i]);
                        System.out.println(tool.lines + " " + tool.words + " " + tool.bytes);
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
