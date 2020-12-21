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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

/**
 *
 * @author ktepin
 */
@Entity
@Table(name = "good_request", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GoodRequest.findAll", query = "SELECT g FROM GoodRequest g")})

public class GoodRequest extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    @JsonbTypeAdapter(UuidJsonConverter.class)
    @Convert("uuidConverter")
    private UUID uuid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goodRequestUuid")
    private List<ParametrValue> parametrValueList;
    @JoinColumn(name = "good_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Good goodUuid;
    @JoinColumn(name = "request_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Request requestUuid;

    public GoodRequest() {
    }

    public GoodRequest(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @XmlTransient
    public List<ParametrValue> getParametrValueList() {
        return parametrValueList;
    }

    public void setParametrValueList(List<ParametrValue> parametrValueList) {
        this.parametrValueList = parametrValueList;
    }

    public UUID getGoodUuid() {
        return goodUuid.getUuid();
    }

    public void setGoodUuid(Good goodUuid) {
        this.goodUuid = goodUuid;
    }

    public UUID getRequestUuid() {
        return requestUuid.getUuid();
    }

    public void setRequestUuid(Request requestUuid) {
        this.requestUuid = requestUuid;
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
        if (!(object instanceof GoodRequest)) {
            return false;
        }
        GoodRequest other = (GoodRequest) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.penzasoft.uldbs.model.GoodRequest[ uuid=" + uuid + " ]";
    }
    
}
