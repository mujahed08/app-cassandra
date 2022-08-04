package com.cass.twfive;

import com.cass.twfive.cassandra.IdentifierRepository;
import com.cass.twfive.model.Identifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.QueryOptions;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import org.springframework.stereotype.Service;

@Service
public class IdentifierService {

    @Autowired
    IdentifierRepository identifierRepository;

    public Identifier getNextIdentifier(String type, String scope) {

        Object o = identifierRepository.updateCounterValue(type, scope);

        System.out.println(o);

        return  identifierRepository.findById(new BasicMapId()
            .with("type", type)
            .with("scope", scope)
        ).get();
    }
}
