package com.yjj.image.recognition.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author yujiajun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadResult {

    @JSONField(name = "log_id")
    private String logId;

    private Map<String, Object> result;

    @JSONField(name = "error_code")
    private String errorCode;

    @JSONField(name = "error_msg")
    private String errorMsg;
}
