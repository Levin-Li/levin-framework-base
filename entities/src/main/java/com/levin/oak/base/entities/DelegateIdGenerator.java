package com.levin.oak.base.entities;

import com.levin.commons.service.support.SpringContextHolder;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

/**
 * 代理的ID生成器
 */
public class DelegateIdGenerator implements IdentifierGenerator, Configurable {

    IdentifierGenerator identifierGenerator;

    Properties params;
    Type type;

    private IdentifierGenerator getIdentifierGenerator() {

        if (this.identifierGenerator == null) {

            this.identifierGenerator = SpringContextHolder.getBeanFactory()
                    .getBeanProvider(IdentifierGenerator.class)
                    .getIfAvailable();
        }

        if (this.identifierGenerator == null) {
            identifierGenerator = new UUIDGenerator();
        }

        return identifierGenerator;
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        this.params = new Properties();
        this.params.putAll(params);
        this.type = type;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return getIdentifierGenerator().generate(session, object);
    }

}