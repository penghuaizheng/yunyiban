package com.huashan.yebserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_sys_msg_content")
public class SysMsgContent{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "标题", position = 2)
    private String title;
    @ApiModelProperty(value = "内容", position = 3)
    private String message;
    @ApiModelProperty(value = "创建时间", position = 4)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdate;
    
    public SysMsgContent(SysMsgContent sysMsgContent) {
        if (Objects.nonNull(sysMsgContent)) {
            this.id=sysMsgContent.id;
            this.title=sysMsgContent.title;
            this.message=sysMsgContent.message;
            this.createdate=sysMsgContent.createdate;
        }
    }
}
