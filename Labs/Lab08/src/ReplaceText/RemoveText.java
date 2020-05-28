package ReplaceText;

import java.io.*;
import java.util.Scanner;

public class RemoveText  {
    public static void main(String[] args) throws Exception {

        if (args.length !=2){
            System.out.println(
                    "Usage: java RemoveText text file");
            System.exit(1);
        }
        File file = new File(args[1]);
        if (!file.exists()) {
            System.out.println("Target file " + args[1] + " does not exist");
            System.exit(2);
        }

        BufferedReader reader = null;

        FileWriter writer = null;
        String oldContent = "";
        try {

            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(args[0], "");

            writer = new FileWriter(file);
            writer.write(newContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                reader.close();

                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
