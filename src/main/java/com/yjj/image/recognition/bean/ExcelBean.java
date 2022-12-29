package com.yjj.image.recognition.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yujiajun
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelBean {

    private String requestId;

    private String path;
}
