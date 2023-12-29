package com.pos.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class SurveyResponse {
    private Integer id;
    private String code;
    private String name;
    private String question1;
    private String question2;
    private Integer typeOfQuestion1Id;
    private String typeOfQuestion1;
    private Integer typeOfQuestion2Id;
    private String typeOfQuestion2;
    private String optionQuestion1;
    private String optionQuestion2;
    private Date createdDate;
    private String mandatoryObject;
    private Integer completionRate;
    private Date startTime;
    private Date endTime;
}
