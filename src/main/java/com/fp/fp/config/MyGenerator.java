package com.fp.fp.config;

import com.fp.fp.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyGenerator implements IdentifierGenerator{
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return (long) Math.floor(Math.random() * Long.MAX_VALUE);
    }
}
