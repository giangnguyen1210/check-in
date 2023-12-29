package com.pos.dto.response;
import lombok.Data;

import java.util.Date;

@Data
public class AuditModel {
    private String userId;
    private Date createTime=new Date();
    private String creator;
    private Date editTime;
    private String editor;
}
