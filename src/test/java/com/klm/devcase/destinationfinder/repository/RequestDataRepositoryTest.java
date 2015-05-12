package com.klm.devcase.destinationfinder.repository;

import com.klm.devcase.destinationfinder.model.RequestData;
import com.klm.devcase.destinationfinder.respository.RequestDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

/**
 * Created by Matteo on 28/03/2015.
 */
@Slf4j
public class RequestDataRepositoryTest extends AbstractSpringTestSupport {

    @Autowired
    RequestDataRepository repo;

    @Test
    public void testSave() {
        RequestData data = new RequestData();
        data.setBegin(Instant.now());
        data.setEnd(Instant.now());
        RequestData saved = repo.save(data.copy());
        Assert.assertNotNull(saved);
        Assert.assertNotEquals(saved, data);
    }

    public void testFind() {
        Iterable<RequestData> found = repo.findAll();
        Assert.assertNotNull(found);
    }

}
