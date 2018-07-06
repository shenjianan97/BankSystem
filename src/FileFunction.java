import java.io.*;

/** 
* ClassName: FileFunction
* Description: A collection of all file functions
* @author ShenJianan
*  
*/
public class FileFunction {
    /** 
    * Title: readFile
    * Description: Basic function to read a file
    * @param fileName file name
    * @return reading result
    */
    public static String readFile(String fileName) {
        String result = "";
        try {
            File file = new File(fileName);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "gbk");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                result = result + s;
            }
            bufferedReader.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /** 
    * Title: writeFile
    * Description: write the file by overwriting the original one
    * @param fileName file name
    * @param currentData current data
    */
    public static void writeFile(String fileName, String currentData) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(fileName);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(currentData.getBytes("gbk"));
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 
    * Title: chaseWrite
    * Description: Add the content to the end of the origianl file
    * @param filePath file path
    * @param content content
    */
    public static void chaseWrite(String filePath, String content) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            content += "\r\n";
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            System.out.println("Writing error" + e);
        }
    }
}