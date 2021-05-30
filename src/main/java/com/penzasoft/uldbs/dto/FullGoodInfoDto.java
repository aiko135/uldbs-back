/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.dto;

import com.penzasoft.uldbs.model.Feedback;
import com.penzasoft.uldbs.model.Good;
import java.util.List;

/**
 *
 * @author ktepin
 */
public class FullGoodInfoDto {
    private Good good;
    private List<Feedback> feedbacks;

    public FullGoodInfoDto() {
    }

    public FullGoodInfoDto(Good good, List<Feedback> feedbacks) {
        this.good = good;
        this.feedbacks = feedbacks;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    
    
}
