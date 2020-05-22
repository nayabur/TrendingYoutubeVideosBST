//https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser {
    public static VideoBST parse(String input, int category) {

        String csvFile = input;
        String line = "";
        VideoBST bst = new VideoBST(5);

        // String[] blank = {"", "", "", "", "", "", "", "", "", "", ""};
        // VideoNode previous = new VideoNode(blank);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] splits = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                /*
                System.out.print("Video [code= " + splits[0].trim() + ", ");
                System.out.print("date= " + splits[1].trim() + ", ");
                System.out.print("title= " + splits[2].trim() + ", ");
                System.out.print("category= " + splits[4].trim() + ", ");
                System.out.print("published= " + splits[5].trim() + "]");
                System.out.println(splits.length);
                 */

                if (Integer.parseInt(splits[4])== category){
                    VideoNode current = new VideoNode(splits);
                    bst.add(current);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bst;
    }
}

