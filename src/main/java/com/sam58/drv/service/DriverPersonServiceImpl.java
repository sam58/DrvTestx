package com.sam58.drv.service;

import com.sam58.drv.model.DriverPerson;
import com.sam58.drv.repository.DriverPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by sam158 on 22.04.2015.
 */
@Service
public class DriverPersonServiceImpl implements DriverPersonService {

    @Autowired
    private DriverPersonRepository driverPersonRepository;

    /**
     * Возвращает все доступные возможные категории. Не стал заморачиваться на хранение в чем то
     * @return
     */
    public List<String> getAvailableCategory() {
        List<String> drvClasses = new LinkedList<>();

        drvClasses.add("Первый");
        drvClasses.add("Второй");
        drvClasses.add("или А");
        drvClasses.add("или B");
        return drvClasses;
    }

    @Override
    public List<DriverPerson> getAll() {
        return this.driverPersonRepository.getAll();
    }

    @Override
    public  List<DriverPerson>  getByPartFio(String partFio) {
        return this.driverPersonRepository.getByPartFio(partFio);
    }

    @Override
    public Set<DriverPerson> getFromGridDrvAll() {
        return this.driverPersonRepository.getFromGridDrvAll();
    }

    @Override
    public boolean delGridRecDriver(String id) {
        return this.driverPersonRepository.delGridRecDriver(id);
    }

    @Override
    public DriverPerson getByID(String id) {
        return this.driverPersonRepository.getByID(id);
    }

    @Override
    public String putPerson(DriverPerson person){
        return this.driverPersonRepository.putPerson(person);
    }
}
