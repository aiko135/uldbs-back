/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.model;

import com.penzasoft.uldbs.util.UuidJsonConverter;
import com.penzasoft.uldbs.util.UuidPgConverter;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

/**
 *
 * @author ktepin
 */
@Entity
@Table(name = "request", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r"),
    @NamedQuery(name = "Request.findByPaymentData", query = "SELECT r FROM Request r WHERE r.paymentData = :paymentData")})

public class Request extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
     @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    @JsonbTypeAdapter(UuidJsonConverter.class)
    @Convert("uuidConverter")
    private UUID uuid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "payment_data")
    private String paymentData;
    @JoinColumn(name = "client_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private User client;
    @JoinColumn(name = "manager_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private User manager;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
    private List<GoodRequest> goodRequestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
    private List<StatusHistory> statusHistoryList;

    public Request() {
    }

    public Request(UUID uuid) {
        this.uuid = uuid;
    }

    public Request(UUID uuid, String paymentData) {
        this.uuid = uuid;
        this.paymentData = paymentData;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(String paymentData) {
        this.paymentData = paymentData;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User clientUuid) {
        this.client = clientUuid;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User managerUuid) {
        this.manager = managerUuid;
    }

    @XmlTransient
    public List<GoodRequest> getGoodRequestList() {
        return goodRequestList;
    }

    public void setGoodRequestList(List<GoodRequest> goodRequestList) {
        this.goodRequestList = goodRequestList;
    }

    @XmlTransient
    public List<StatusHistory> getStatusHistoryList() {
        return statusHistoryList;
    }

    public void setStatusHistoryList(List<StatusHistory> statusHistoryList) {
        this.statusHistoryList = statusHistoryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.penzasoft.uldbs.model.Request[ uuid=" + uuid + " ]";
    }
    
}
