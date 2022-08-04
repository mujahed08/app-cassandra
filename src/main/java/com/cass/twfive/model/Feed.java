package com.cass.twfive.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@Table("feed")
public class Feed implements Serializable {

    @PrimaryKeyColumn(name = "activity", type = PrimaryKeyType.PARTITIONED)
    private String type;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private String name;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private String username;

    @PrimaryKeyColumn(name="created_at", type = PrimaryKeyType.CLUSTERED)
    private LocalDateTime createdAt;

    @Column
    private int points;

    @Column
    private Map<String, String> streak;

    @Column
    private Map<String, String> mission;
}
