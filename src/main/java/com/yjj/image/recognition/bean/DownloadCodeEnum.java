package com.yjj.image.recognition.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author yujiajun
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DownloadCodeEnum {
    // 任务未开始
    START(1, "任务未开始"),
    // 进行中
    RUNNING(2, "进行中"),
    // 完成
    FINISH(3, "完成");

    private int code;
    private String name;
}
