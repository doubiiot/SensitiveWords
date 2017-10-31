package SensitiveWords;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import SensitiveWords.Process;

public class SensitiveWords {

    //定义该图像中所需要的组件的引用
    private Frame frame;
    private Button chose_bt , start_bt,clear_bt;
    private FileDialog openDia;
    private TextField file_line;
    private TextArea remind_line;
    private String file_name,text = "";
    private Process file_op;


    SensitiveWords(){
        init();
    }

    public void init(){

        frame = new Frame("Sensitive words detection");
        frame.setBounds(300,100,500,300);
        frame.setBackground(Color.GRAY);
        frame.setLayout(null);  //采用流式布局

        openDia = new FileDialog(frame, "打开", FileDialog.LOAD);

        file_line = new TextField();
        remind_line = new TextArea();
        //file_line.setEditable(false);
        file_line.setFont(new Font("宋体",Font.BOLD,18));
        remind_line.setFont(new Font("宋体",Font.BOLD,16));
        remind_line.setEditable(false);

        chose_bt = new Button("chose");
        start_bt = new Button("start");
        clear_bt = new Button("clear");
        //将组件添加到 frame中
        frame.add(chose_bt);
        frame.add(file_line);
        frame.add(remind_line);
        frame.add(start_bt);
        frame.add(clear_bt);

        chose_bt.setBounds(340,50,70,35);
        start_bt.setBounds(415,50,70,35);
        file_line.setBounds(30,50,300,30);
        remind_line.setBounds(30,120,300,150);
        clear_bt.setBounds(340,235,70,35);

        //加载一下窗体上的事件.
        myEvent();
        //显示窗体
        frame.setVisible(true);
    }
    private void myEvent(){

        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加一个活动监听
        chose_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDia.setVisible(true);
                String dirpath = openDia.getDirectory();//获取打开文件路径并保存到字符串中。
                String fileName = openDia.getFile();//获取打开文件名称并保存到字符串中

                if (dirpath == null || fileName == null)
                    file_name = " ";
                else
                    file_name = dirpath + fileName;

                //判断文件路径长度
                if(file_name.length() > 40){
                    file_line.setFont(new Font("宋体",Font.BOLD,10));
                }
                else if(file_name.length() > 25 &&  file_name.length() <= 40 ){
                    file_line.setFont(new Font("宋体",Font.BOLD,15));
                }

                file_line.setText(file_name);
            }
        });
        start_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(file_name == null){
                    remind_line.append("please slect file!\n");
                }
                else{
                    if(file_name.endsWith("txt")) {
                        remind_line.append("start！ please wait..\n");
                        file_op = new Process(file_name);
                        if (file_op.getExist_words().startsWith("The"))
                            remind_line.append(file_op.getExist_words());
                        else {
                            remind_line.append("modify success!\n");
                            remind_line.append("processed words : " + file_op.getExist_words());
                        }
                    }
                    else{
                        remind_line.append("please chose txt file !\n");
                    }
                }
            }
        });

        clear_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remind_line.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SensitiveWords f = new SensitiveWords();
    }
}