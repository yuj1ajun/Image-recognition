package com.yjj.image.recognition.thread;

import com.yjj.image.recognition.bean.ExcelBean;
import com.yjj.image.recognition.client.RecognitionClient;
import com.yjj.image.recognition.recognition.Recognition;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yujiajun
 */
public class BackgroundTask implements Runnable {

    private final List<ExcelBean> list = Collections.synchronizedList(new ArrayList<>());

    private Recognition recognition;

    @SneakyThrows
    @Override
    public void run() {

        while (!list.isEmpty()) {

            for (int i = 0; i < list.size(); i++) {

                ExcelBean bean = list.get(i);

                if (recognition == null) {

                    recognition = RecognitionClient.getRecognition();
                }

                Thread.sleep(2000);

                recognition.downloadFile(bean.getRequestId(), bean.getPath());

                list.remove(i);

                i--;

            }
        }
    }

    public void add(ExcelBean bean) {
        list.add(bean);
    }
}
