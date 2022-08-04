package com.cass.twfive.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Table("login")
public class Login implements Serializable {

    @PrimaryKeyColumn(name = "part_ky", type = PrimaryKeyType.PARTITIONED)
    private byte partKey;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String email;

    @Column("registered_on")
    private LocalDateTime registeredOn;

    @Column
    private LocalDate dob;

    @Column
    private Long id;

    @Column
    private byte appId;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;

    @Column
    private Boolean enabled;

    @Column
    private Boolean locked;

    @Column
    private Boolean suspended;

    @Column
    private String prefix;

    @Column
    private String suffix;

    @Column("id_txt")
    private String idTxt;

    @Column
    private List<String> roles;
}
