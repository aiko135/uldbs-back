/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.model;

import com.penzasoft.uldbs.util.UuidJsonConverter;
import com.penzasoft.uldbs.util.UuidPgConverter;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "status_history",  schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusHistory.findAll", query = "SELECT s FROM StatusHistory s"),
    @NamedQuery(name = "StatusHistory.findBySetupTimestamp", query = "SELECT s FROM StatusHistory s WHERE s.setupTimestamp = :setupTimestamp"),
    @NamedQuery(name = "StatusHistory.findByComment", query = "SELECT s FROM StatusHistory s WHERE s.comment = :comment")})
public class StatusHistory extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
     @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    @JsonbTypeAdapter(UuidJsonConverter.class)
    @Convert("uuidConverter")
    private UUID uuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "setup_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date setupTimestamp;
    @Size(max = 2147483647)
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "request_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Request requestUuid;
    @JoinColumn(name = "status_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Status statusUuid;

    public StatusHistory() {
    }

    public StatusHistory(UUID uuid) {
        this.uuid = uuid;
    }

    public StatusHistory(UUID uuid, Date setupTimestamp) {
        this.uuid = uuid;
        this.setupTimestamp = setupTimestamp;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getSetupTimestamp() {
        return setupTimestamp;
    }

    public void setSetupTimestamp(Date setupTimestamp) {
        this.setupTimestamp = setupTimestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRequestUuid() {
        return requestUuid.getUuid().toString();
    }

    public void setRequestUuid(Request requestUuid) {
        this.requestUuid = requestUuid;
    }

    public Status getStatusUuid() {
        return statusUuid;
    }

    public void setStatusUuid(Status statusUuid) {
        this.statusUuid = statusUuid;
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
        if (!(object instanceof StatusHistory)) {
            return false;
        }
        StatusHistory other = (StatusHistory) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.penzasoft.uldbs.model.StatusHistory[ uuid=" + uuid + " ]";
    }
    
}
