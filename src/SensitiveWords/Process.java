package SensitiveWords;
import java.io.File;
import java.io.*;

public class Process {
    private String[] sensitive_words_buff = {"abc", "北京", "你娘", "领导","你妈"};
    private String exist_words = "";
    Process() {

    }

    Process(String fileName) {
        try {
            File file = new File(fileName);
            //read
            String newline;
            String read_buff = "";
            BufferedReader bufread = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"utf-8"));
            while ((newline = bufread.readLine()) != null) {
                //System.out.println(newline);
                newline = modify_words(newline);
                read_buff += newline;
            }
            //System.out.println("read buff is " + read_buff);
            bufread.close();

            //write
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(read_buff);
            bw.flush();
            bw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public String  modify_words(String sentence){
        for(int i = 0 ;i < sensitive_words_buff.length ;i++)
        {
            if(sentence.indexOf(sensitive_words_buff[i]) != -1){
                exist_words = exist_words + " " + sensitive_words_buff[i];
                sentence = sentence.replaceAll(sensitive_words_buff[i],"**");
                //System.out.println("modify " + sensitive_words_buff[i] + " to ** success!");
            }
        }
        return sentence;
    }
    public String getExist_words(){
        if(exist_words != "")
            return exist_words;
        else
            return "The file has no sensitive words\n";
    }

}
