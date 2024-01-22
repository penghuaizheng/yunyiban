package com.huashan.yebserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_sys_msg")
public class SysMsg{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "消息id", position = 2)
    private Integer mid;
    @ApiModelProperty(value = "0表示群发消息", position = 3)
    private Integer type;
    @ApiModelProperty(value = "这条消息是给谁的", position = 4)
    private Integer adminid;
    @ApiModelProperty(value = "0 未读 1 已读", position = 5)
    private Integer state;
    
    public SysMsg(SysMsg sysMsg) {
        if (Objects.nonNull(sysMsg)) {
            this.id=sysMsg.id;
            this.mid=sysMsg.mid;
            this.type=sysMsg.type;
            this.adminid=sysMsg.adminid;
            this.state=sysMsg.state;
        }
    }
}
