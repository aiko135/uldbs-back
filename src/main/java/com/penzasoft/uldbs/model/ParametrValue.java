/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.model;

import com.penzasoft.uldbs.util.UuidJsonConverter;
import com.penzasoft.uldbs.util.UuidPgConverter;
import java.io.Serializable;
import java.util.UUID;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

/**
 *
 * @author ktepin
 */
@Entity
@Table(name = "parametr_value", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametrValue.findAll", query = "SELECT p FROM ParametrValue p"),
    @NamedQuery(name = "ParametrValue.findByValue", query = "SELECT p FROM ParametrValue p WHERE p.value = :value")})
 
public class ParametrValue extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    @JsonbTypeAdapter(UuidJsonConverter.class)
    @Convert("uuidConverter")
    private UUID uuid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "value")
    private String value;
    @JoinColumn(name = "good_request_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private GoodRequest goodRequestUuid;
    @JoinColumn(name = "parametr_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Parametr parametrUuid;

    public ParametrValue() {
    }

    public ParametrValue(UUID uuid) {
        this.uuid = uuid;
    }

    public ParametrValue(UUID uuid, String value) {
        this.uuid = uuid;
        this.value = value;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getGoodRequestUuid() {
        return goodRequestUuid.getUuid();
    }

    public void setGoodRequestUuid(GoodRequest goodRequestUuid) {
        this.goodRequestUuid = goodRequestUuid;
    }

    public UUID getParametrUuid() {
        return parametrUuid.getUuid();
    }

    public void setParametrUuid(Parametr parametrUuid) {
        this.parametrUuid = parametrUuid;
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
        if (!(object instanceof ParametrValue)) {
            return false;
        }
        ParametrValue other = (ParametrValue) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.penzasoft.uldbs.model.ParametrValue[ uuid=" + uuid + " ]";
    }
    
}
