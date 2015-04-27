package com.sam58.drv.repository;

import com.sam58.drv.model.DriverPerson;

import java.util.List;
import java.util.Set;

/**
 * Created by sam158 on 22.04.2015.
 */
public interface DriverPersonRepository {

    /**
     * Получает список всех водителей
     * @return List<DriverPerson>
     */
    List<DriverPerson> getAll();

    /**
     * Получить список по чатсти FIO
     * @param partFio
     * @return
     */
    List<DriverPerson> getByPartFio(String partFio);

    DriverPerson getByID(String id);

    Set<DriverPerson> getFromGridDrvAll();

    boolean delGridRecDriver(String id);

    String putPerson(DriverPerson person);
}
