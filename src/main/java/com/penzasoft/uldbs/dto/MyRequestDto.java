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
    private UUID managerUuid;
    private String managerName;
    private String managerEmail;
    private String managerPhone;
    private List<GoodRequest> goodRequestList;
    private List<StatusHistory> statusHistoryList;

    public MyRequestDto() {
    }

    public MyRequestDto(UUID requestUuid, UUID managerUuid, String managerName, String managerEmail, String managerPhone, List<GoodRequest> goodRequestList, List<StatusHistory> statusHistoryList) {
        this.requestUuid = requestUuid;
        this.managerUuid = managerUuid;
        this.managerName = managerName;
        this.managerEmail = managerEmail;
        this.managerPhone = managerPhone;
        this.goodRequestList = goodRequestList;
        this.statusHistoryList = statusHistoryList;
    }



    
    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }

    public UUID getManagerUuid() {
        return managerUuid;
    }

    public void setManagerUuid(UUID managerUuid) {
        this.managerUuid = managerUuid;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    
    
    
}
