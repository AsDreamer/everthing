package com.bittech.everthing.core.model;

import lombok.Data;

@Data
public class Condition {
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件类型
     */
    private String fileType;

    private Integer limit;
    /**
     * 检索结果的信息按depth排序规则
     */

    private Boolean orderByAsc;


}
