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
import java.util.List;
import java.util.UUID;
import javax.json.bind.annotation.JsonbTransient;
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
@Table(name = "chat", schema = "public")
@NamedQueries({@NamedQuery(name = "Chat.findAll", query = "SELECT c FROM Chat c")})
public class Chat extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    @JsonbTypeAdapter(UuidJsonConverter.class)
    @Convert("uuidConverter")
    private UUID uuid;
    @JoinColumn(name = "client_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private User client;
    @JoinColumn(name = "manager_uuid", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private User manager;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private List<Message> messageList;

    public Chat() {
    }

    public Chat(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    @JsonbTransient
    @JsonIgnore
    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
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
        if (!(object instanceof Chat)) {
            return false;
        }
        Chat other = (Chat) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.penzasoft.uldbs.model.Chat[ uuid=" + uuid + " ]";
    }
    
}
