/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.dto;

import com.penzasoft.uldbs.model.GoodRequest;
import com.penzasoft.uldbs.model.StatusHistory;
import com.penzasoft.uldbs.util.UuidJsonConverter;
import java.util.List;
import java.util.UUID;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 *
 * @author ktepin
 */
public class MyRequestDto {
    
    @JsonbTypeAdapter(UuidJsonConverter.class)
    private UUID requestUuid;
     @JsonbTypeAdapter(UuidJsonConverter.class)
    private UUID contactorUuid;
    private String contactorName;
    private String contactorEmail;
    private String contactorPhone;
    private List<GoodRequest> goodRequestList;
    private List<StatusHistory> statusHistoryList;

    public MyRequestDto() {
    }

    public MyRequestDto(UUID requestUuid, UUID contactorUuid, String contactorName, String contactorEmail, String contactorPhone, List<GoodRequest> goodRequestList, List<StatusHistory> statusHistoryList) {
        this.requestUuid = requestUuid;
        this.contactorUuid = contactorUuid;
        this.contactorName = contactorName;
        this.contactorEmail = contactorEmail;
        this.contactorPhone = contactorPhone;
        this.goodRequestList = goodRequestList;
        this.statusHistoryList = statusHistoryList;
    }

    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }

    public UUID getContactorUuid() {
        return contactorUuid;
    }

    public void setContactorUuid(UUID contactorUuid) {
        this.contactorUuid = contactorUuid;
    }

    public String getContactorName() {
        return contactorName;
    }

    public void setContactorName(String contactorName) {
        this.contactorName = contactorName;
    }

    public String getContactorEmail() {
        return contactorEmail;
    }

    public void setContactorEmail(String contactorEmail) {
        this.contactorEmail = contactorEmail;
    }

    public String getContactorPhone() {
        return contactorPhone;
    }

    public void setContactorPhone(String contactorPhone) {
        this.contactorPhone = contactorPhone;
    }

    public List<GoodRequest> getGoodRequestList() {
        return goodRequestList;
    }

    public void setGoodRequestList(List<GoodRequest> goodRequestList) {
        this.goodRequestList = goodRequestList;
    }

    public List<StatusHistory> getStatusHistoryList() {
        return statusHistoryList;
    }

    public void setStatusHistoryList(List<StatusHistory> statusHistoryList) {
        this.statusHistoryList = statusHistoryList;
    }
 
    
    
}
