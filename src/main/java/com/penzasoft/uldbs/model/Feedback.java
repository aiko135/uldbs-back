/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.penzasoft.uldbs.util.UuidJsonConverter;
import com.penzasoft.uldbs.util.UuidPgConverter;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.json.bind.annotation.JsonbTransient;
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
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

/**
 *
 * @author ktepin
 */
@Entity
@Table(name = "feedback", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByGrade", query = "SELECT f FROM Feedback f WHERE f.grade = :grade"),
    @NamedQuery(name = "Feedback.findByFeedback", query = "SELECT f FROM Feedback f WHERE f.feedback = :feedback"),
    @NamedQuery(name = "Feedback.findByTimestamp", query = "SELECT f FROM Feedback f WHERE f.timestamp = :timestamp")})
public class Feedback extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    @JsonbTypeAdapter(UuidJsonConverter.class)
    @Convert("uuidConverter")
    private UUID uuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grade")
    private short grade;
    @Size(max = 2147483647)
    @Column(name = "feedback")
    private String feedback;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "good_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Good good;
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private User user;

    public Feedback() {
    }

    public Feedback(UUID uuid) {
        this.uuid = uuid;
    }

    public Feedback(UUID uuid, short grade, Date timestamp) {
        this.uuid = uuid;
        this.grade = grade;
        this.timestamp = timestamp;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public short getGrade() {
        return grade;
    }

    public void setGrade(short grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getGood() {
         if(good== null)
            return "null";
        else
            return good.getUuid().toString();
    }

    public void setGood(Good goodUuid) {
        this.good = goodUuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userUuid) {
        this.user = userUuid;
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
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.penzasoft.uldbs.model.Feedback[ uuid=" + uuid + " ]";
    }
    
}
