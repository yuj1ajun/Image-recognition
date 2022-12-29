package com.yjj.image.recognition.Frame;

import com.alibaba.fastjson.JSON;
import com.yjj.image.recognition.bean.UploadResult;
import com.yjj.image.recognition.client.RecognitionClient;
import com.yjj.image.recognition.recognition.Recognition;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @author yujiajun
 */
public class FileChooserFrame extends JFrame {
    private final JFileChooser chooser;
    private final Recognition recognition = RecognitionClient.getRecognition();
    public FileChooserFrame() {
        //调用父类构造函数
        super("图片转EXCEL");
        //得到容器
        Container contentPane = getContentPane();
        //设置布局管理器为Flowlayout
        contentPane.setLayout(new FlowLayout());
        //初始化文件选择器
        chooser = new JFileChooser();
        JButton button = new JButton("选择文件");
        contentPane.add(button);
        button.addActionListener(event -> {
            // 移除文件过滤器
            chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
            chooser.addChoosableFileFilter(new PhotoFilter());
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (null != file) {
                // 上传文件
                String path = file.getPath();
                JSONObject result = recognition.uploadFile(path);
                System.out.println(result);
                UploadResult uploadResult = JSON.parseObject(result.toString(), UploadResult.class);
                if (null != uploadResult.getResult()) {
                    String requestId = uploadResult.getResult().get(0).get("request_id");
                    recognition.downloadFile(requestId, path);
                }
            }
        });

        //设置窗口大小
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        //设置窗口可见
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
