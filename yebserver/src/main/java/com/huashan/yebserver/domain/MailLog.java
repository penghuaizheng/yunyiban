package com.huashan.yebserver.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_mail_log")
public class MailLog implements Serializable{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "消息id", position = 1)
    private String msgid;
    @ApiModelProperty(value = "接收员工id", position = 2)
    private Integer eid;
    @ApiModelProperty(value = "状态（0:消息投递中 1:投递成功 2:投递失败）", position = 3)
    private Integer status;
    @ApiModelProperty(value = "路由键", position = 4)
    private String routekey;
    @ApiModelProperty(value = "交换机", position = 5)
    private String exchange;
    @ApiModelProperty(value = "重试次数", position = 6)
    private Integer count;
    @ApiModelProperty(value = "重试时间", position = 7)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime trytime;
    @ApiModelProperty(value = "创建时间", position = 8)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;
    @ApiModelProperty(value = "更新时间", position = 9)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatetime;
    
    public MailLog(MailLog mailLog) {
        if (Objects.nonNull(mailLog)) {
            this.msgid=mailLog.msgid;
            this.eid=mailLog.eid;
            this.status=mailLog.status;
            this.routekey=mailLog.routekey;
            this.exchange=mailLog.exchange;
            this.count=mailLog.count;
            this.trytime=mailLog.trytime;
            this.createtime=mailLog.createtime;
            this.updatetime=mailLog.updatetime;
        }
    }
}
