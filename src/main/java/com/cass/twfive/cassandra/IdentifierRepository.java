package com.cass.twfive.cassandra;

import com.cass.twfive.model.Identifier;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

public interface IdentifierRepository extends CassandraRepository<Identifier, MapId> {

    @Query("update Identifier SET id=id+1 WHERE type = ?0 AND scope= ?1;")
    Object updateCounterValue(String type, String scope);

}
