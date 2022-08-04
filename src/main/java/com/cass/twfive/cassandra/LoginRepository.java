package com.cass.twfive.cassandra;

import com.cass.twfive.model.Login;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.Optional;

public interface LoginRepository extends CassandraRepository<Login, MapId> {

    List<Login> findByPartKey(byte partKey);

}
