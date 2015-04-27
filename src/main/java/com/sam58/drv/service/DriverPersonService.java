package com.sam58.drv.service;

import com.sam58.drv.model.DriverPerson;

import java.util.List;
import java.util.Set;

/**
 * Created by sam158 on 22.04.2015.
 */
public interface DriverPersonService {
     List<String> getAvailableCategory();
     List<DriverPerson> getAll();
     List<DriverPerson> getByPartFio(String partFio);
     Set<DriverPerson> getFromGridDrvAll();

     DriverPerson getByID(String id);

     boolean delGridRecDriver(String id);

     String putPerson(DriverPerson person);
}
