package com.cass.twfive.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@Data
@Builder
public class Identifier implements Serializable {

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private String type;

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String scope;

    @Column("id")
    private Long id;
}
