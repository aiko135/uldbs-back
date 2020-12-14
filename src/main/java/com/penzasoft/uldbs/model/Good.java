/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.model;

import com.penzasoft.uldbs.util.UuidJsonConverter;
import com.penzasoft.uldbs.util.UuidPgConverter;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "good", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Good.findAll", query = "SELECT g FROM Good g"),
    @NamedQuery(name = "Good.findByName", query = "SELECT g FROM Good g WHERE g.name = :name"),
    @NamedQuery(name = "Good.findByPrice", query = "SELECT g FROM Good g WHERE g.price = :price"),
    @NamedQuery(name = "Good.findByDescr", query = "SELECT g FROM Good g WHERE g.descr = :descr"),
    @NamedQuery(name = "Good.findByImgPath", query = "SELECT g FROM Good g WHERE g.imgPath = :imgPath")})

public class Good extends AbstractEntity implements Serializable {

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
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descr")
    private String descr;
    @Size(max = 512)
    @Column(name = "img_path")
    private String imgPath;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goodUuid")
    private List<Feedback> feedbackList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goodUuid")
    private List<Parametr> parametrList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goodUuid")
    private List<GoodRequest> goodRequestList;
    @JoinColumn(name = "catalog_uuid", referencedColumnName = "uuid")
    @ManyToOne
    private Catalog catalogUuid;

    public Good() {
    }

    public Good(UUID uuid) {
        this.uuid = uuid;
    }

    public Good(UUID uuid, String name, BigDecimal price, String descr) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.descr = descr;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @XmlTransient
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @XmlTransient
    public List<Parametr> getParametrList() {
        return parametrList;
    }

    public void setParametrList(List<Parametr> parametrList) {
        this.parametrList = parametrList;
    }

    @XmlTransient
    public List<GoodRequest> getGoodRequestList() {
        return goodRequestList;
    }

    public void setGoodRequestList(List<GoodRequest> goodRequestList) {
        this.goodRequestList = goodRequestList;
    }

    public Catalog getCatalogUuid() {
        return catalogUuid;
    }

    public void setCatalogUuid(Catalog catalogUuid) {
        this.catalogUuid = catalogUuid;
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
        if (!(object instanceof Good)) {
            return false;
        }
        Good other = (Good) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.penzasoft.uldbs.model.Good[ uuid=" + uuid + " ]";
    }
    
}
