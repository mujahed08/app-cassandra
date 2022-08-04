package com.cass.twfive.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@Table("user_feed")
public class UserFeed {

    @PrimaryKeyColumn(name = "part_ky", type = PrimaryKeyType.PARTITIONED)
    private String partKey;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private String username;

    @PrimaryKeyColumn(name = "activity",type = PrimaryKeyType.CLUSTERED)
    private String type;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private String name;

    @PrimaryKeyColumn(name="created_at", type = PrimaryKeyType.CLUSTERED)
    private LocalDateTime createdAt;

    @Column
    private int points;

    @Column
    private Map<String, String> streak;

    @Column
    private Map<String, String> mission;
}
