package com.by.mantis.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "mantis_user_table")
public class MantisUserData {

    @Id
    private Integer id = Integer.MAX_VALUE;
    private String userName;
    @Column(columnDefinition = "text")
    private String email;


    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }


    public MantisUserData withId(Integer id) {
        this.id = id;
        return this;
    }

    public MantisUserData userName(String userName) {
        this.userName = userName;
        return this;
    }

    public MantisUserData withEmail(String email) {
        this.email = email;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MantisUserData that = (MantisUserData) o;
        return Objects.equals(id, that.id) && Objects.equals(userName, that.userName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email);
    }

    @Override
    public String toString() {
        return "MantisUserData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}


