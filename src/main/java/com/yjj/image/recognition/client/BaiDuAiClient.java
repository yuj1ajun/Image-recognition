package com.yjj.image.recognition.client;

import com.baidu.aip.ocr.AipOcr;

/**
 * @author yujiajun
 */
public class BaiDuAiClient {

    private static final String APP_ID = "25987880";

    private static final String API_KEY = "n3oZcZnevMWvKMyVQtARlTDD";

    private static final String SECRET_KEY = "GQBBhuzZNGK1dGfYpXs1FQcaEdIUcwO1";

    private static final AipOcr CLIENT = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

    public static AipOcr getClient() {
        return CLIENT;
    }
}
