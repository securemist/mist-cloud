package com.mist.cloud.infrastructure.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.util.Date;

/**
 * @Author: securemist
 * @Datetime: 2023/7/19 10:11
 * @Description:
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@ApiModel("文件夹实体类")
public class Folder {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    private String name;

    private Date modifyTime;

    private Date createTime;

    private Integer deleted;

    private Date deletedTime;

}
