package com.yjj.image.recognition.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yujiajun
 */
public class HttpClient {

    private CloseableHttpClient client = HttpClients.createDefault();

    public void getExcel(String url, String path) {

        InputStream stream = null;

        FileOutputStream outputStream = null;

        try {

            HttpGet get = new HttpGet(url);

            CloseableHttpResponse response = client.execute(get);

            stream = response.getEntity().getContent();

            String name = path.substring(0, path.lastIndexOf(".")) + ".xls";

            outputStream = new FileOutputStream(name);

            byte[] bytes = new byte[1024];

            int index;

            while ((index = stream.read(bytes)) != -1) {

                outputStream.write(bytes, 0, index);
            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (stream != null) {
                    stream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
