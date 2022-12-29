package com.yjj.image.recognition.recognition;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.google.common.collect.Maps;
import com.yjj.image.recognition.bean.DownloadCodeEnum;
import com.yjj.image.recognition.bean.DownloadResult;
import com.yjj.image.recognition.bean.ExcelBean;
import com.yjj.image.recognition.client.BaiDuAiClient;
import com.yjj.image.recognition.http.HttpClient;
import com.yjj.image.recognition.thread.BackgroundTask;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author yujiajun
 */
public class Recognition {

    private final BackgroundTask task = new BackgroundTask();

    private Thread thread = null;

    private final HttpClient client = new HttpClient();

    public JSONObject uploadFile(String filePath) {

        AipOcr aipOcr = BaiDuAiClient.getClient();

        return aipOcr.tableRecognitionAsync(filePath, Maps.newHashMap());
    }

    public void downloadFile(String requestId, String path) {

        AipOcr aipOcr = BaiDuAiClient.getClient();

        JSONObject result = aipOcr.tableResultGet(requestId, Maps.newHashMap());

        DownloadResult downloadResult = JSON.parseObject(result.toString(), DownloadResult.class);

        System.out.println(downloadResult);

        if (null != downloadResult.getResult()) {

            Map<String, Object> map = downloadResult.getResult();

            if (Integer.parseInt(map.get("ret_code").toString()) != DownloadCodeEnum.FINISH.getCode()) {

                task.add(ExcelBean.builder()
                        .requestId(requestId)
                        .path(path)
                        .build());

                startTask();

            } else {
                // 下载文件
                String url = map.get("result_data").toString();

                client.getExcel(url, path);
            }

        } else {

            task.add(ExcelBean.builder()
                    .requestId(requestId)
                    .path(path)
                    .build());

            startTask();
        }
    }

    private synchronized void startTask() {

        if (thread == null) {

            thread = new Thread(task);

            thread.start();

        } else {

            if (!thread.isAlive()) {

                thread = new Thread(task);

                thread.start();
            }
        }
    }
}
