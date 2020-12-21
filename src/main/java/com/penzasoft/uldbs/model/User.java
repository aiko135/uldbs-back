/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.penzasoft.uldbs.util.UuidJsonConverter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Convert;

/**
 *
 * @author ktepin
 */
@Entity
@Table(name = "user", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPass", query = "SELECT u FROM User u WHERE u.pass = :pass"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByBirthDate", query = "SELECT u FROM User u WHERE u.birthDate = :birthDate"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone")})

public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    @JsonbTypeAdapter(UuidJsonConverter.class)
    @Convert("uuidConverter")
    private UUID uuid;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "pass")
    private String pass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "role")
    private short role;
    @Size(max = 64)
    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 11)
    @Column(name = "phone")
    private String phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userUuid")
    private List<Feedback> feedbackList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientUuid")
    private List<Request> requestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "managerUuid")
    private List<Request> requestList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientUuid")
    private List<Chat> chatList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "managerUuid")
    private List<Chat> chatList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userUuid")
    private List<Message> messageList;

    public User() {
    }

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public User(UUID uuid, String email, String pass, short role) {
        this.uuid = uuid;
        this.email = email;
        this.pass = pass;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public short getRole() {
        return role;
    }

    public void setRole(short role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonbTransient
    @JsonIgnore
    @XmlTransient
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @JsonbTransient
    @JsonIgnore
    @XmlTransient
    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    @JsonbTransient
    @JsonIgnore
    @XmlTransient
    public List<Request> getRequestList1() {
        return requestList1;
    }

    public void setRequestList1(List<Request> requestList1) {
        this.requestList1 = requestList1;
    }

    @JsonbTransient
    @JsonIgnore
    @XmlTransient
    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }
    
    @JsonbTransient
    @JsonIgnore
    @XmlTransient
    public List<Chat> getChatList1() {
        return chatList1;
    }

    public void setChatList1(List<Chat> chatList1) {
        this.chatList1 = chatList1;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.penzasoft.uldbs.model.User[ uuid=" + uuid + " ]";
    }
    
}
