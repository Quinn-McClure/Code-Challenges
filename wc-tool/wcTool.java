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


    public static void main(String[] args) {
        //if there is command line arguments
        if (args.length > 0) {
            wcTool tool = new wcTool();
            for(int i = 0; i < args.length; i ++) {
                if(args[i].equals("-c")) {
                    try {
                        tool.processBytes(args[i + 1]);
                        System.out.println(tool.bytes);
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
