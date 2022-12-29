package com.yjj.image.recognition.client;

import com.yjj.image.recognition.recognition.Recognition;

/**
 * @author yujiajun
 */
public class RecognitionClient {

    private static final Recognition RECOGNITION = new Recognition();

    public static Recognition getRecognition() {
        return RECOGNITION;
    }
}
