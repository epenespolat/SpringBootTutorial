package com.enespolat.security.jwt;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.enespolat.data.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;

//register (kayıt olma) için kullanacağız

//lombok
@Getter
@Setter

//Jpa
@Entity
@Table(name="users")
public class _02_UserEntity  extends BaseEntity {

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column(name = "system_auto_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;

}