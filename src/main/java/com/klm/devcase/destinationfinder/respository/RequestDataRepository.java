package com.klm.devcase.destinationfinder.respository;

import com.klm.devcase.destinationfinder.model.RequestData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Matteo on 28/03/2015.
 */
@Repository
public interface RequestDataRepository extends CrudRepository<RequestData, Long> {

}
