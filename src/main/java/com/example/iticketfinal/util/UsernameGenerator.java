package com.example.iticketfinal.util;

import com.example.iticketfinal.dao.entity.PerformerEntity;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class UsernameGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        PerformerEntity performer = (PerformerEntity) object;
        if (performer.getName() != null && performer.getSurname() != null) {
            return (performer.getName() + "_" + performer.getSurname()).toLowerCase();
        }
        return null;
    }
}