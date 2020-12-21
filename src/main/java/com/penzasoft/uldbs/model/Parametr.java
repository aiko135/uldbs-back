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
@Table(name = "parametr", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametr.findAll", query = "SELECT p FROM Parametr p"),
    @NamedQuery(name = "Parametr.findByQuestion", query = "SELECT p FROM Parametr p WHERE p.question = :question"),
    @NamedQuery(name = "Parametr.findByName", query = "SELECT p FROM Parametr p WHERE p.name = :name")})

public class Parametr extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    @JsonbTypeAdapter(UuidJsonConverter.class)
    @Convert("uuidConverter")
    private UUID uuid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "question")
    private String question;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "good_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Good goodUuid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametrUuid")
    private List<ParametrValue> parametrValueList;

    public Parametr() {
    }

    public Parametr(UUID uuid) {
        this.uuid = uuid;
    }

    public Parametr(UUID uuid, String question, String name) {
        this.uuid = uuid;
        this.question = question;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getGoodUuid() {
        return goodUuid.getUuid();
    }

    public void setGoodUuid(Good goodUuid) {
        this.goodUuid = goodUuid;
    }

    @XmlTransient
    public List<ParametrValue> getParametrValueList() {
        return parametrValueList;
    }

    public void setParametrValueList(List<ParametrValue> parametrValueList) {
        this.parametrValueList = parametrValueList;
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
        if (!(object instanceof Parametr)) {
            return false;
        }
        Parametr other = (Parametr) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.penzasoft.uldbs.model.Parametr[ uuid=" + uuid + " ]";
    }
    
}
