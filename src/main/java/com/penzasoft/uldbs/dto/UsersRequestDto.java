/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.dto;

import com.penzasoft.uldbs.model.Good;
import com.penzasoft.uldbs.util.UuidJsonConverter;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 *
 * @author ktepin
 */
public class UsersRequestDto implements Serializable {
    
    @JsonbTypeAdapter(UuidJsonConverter.class)
    private UUID userUuid;
    
    private String payment_data;
    private List<Good> goods;

    public UsersRequestDto() {
    }

    public UsersRequestDto(UUID user_uuid, String payment_data, List<Good> goods) {
        this.userUuid = user_uuid;
        this.payment_data = payment_data;
        this.goods = goods;
    }
    
    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID user_uuid) {
        this.userUuid = user_uuid;
    }

    public String getPaymentData() {
        return payment_data;
    }

    public void setPaymentData(String payment_data) {
        this.payment_data = payment_data;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }
    
}
