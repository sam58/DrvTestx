package com.sam58.drv.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sam58.drv.model.DriverPerson;
import com.sam58.drv.model.JSViews;
import com.sam58.drv.service.DriverPersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

class Respose{
    @JsonView(Respose.class)
    String success;
    @JsonView(Respose.class)
    String error;
}

/**
 * Created by sam158 on 21.04.2015.
 */
@Controller
public class DrvFormController {
    private static final Logger log = Logger.getLogger(DrvFormController.class);

    @Autowired
    private DriverPersonService driverPersonService;

    /**
     * Запросы на корень отправить на форму
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectHomeRoot(HttpServletRequest request,
                                   HttpServletResponse response) {
        return "redirect:drvForm.html";
    }

    /**
     * Отобразить основную страницу
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/drvForm.html", method = RequestMethod.GET)
    public String getHome(HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap model) {
        return "drvForm";
    }

    /**
     * Получить список всех доступных классов водителя
     * @return
     */
    @RequestMapping(value = "/ajax/drvClasses/get", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAvailabledrvClass() {
        return this.driverPersonService.getAvailableCategory();
    }

    /**
     * Получить список вообще всех доступных водителей
     * @return
     */
    @RequestMapping(value = "/ajax/AllDriver/get", method = RequestMethod.GET)
    @ResponseBody
    public List<DriverPerson> getAllDriver() {
        return this.driverPersonService.getAll();
    }

    /**
     * Получить список доступных водителей по части их ФИО
     * @param partName
     * @return
     */
    @JsonView(JSViews.PersonalData.class)
    @RequestMapping(value = "/ajax/byPart/get", method = RequestMethod.GET)
    public @ResponseBody
    List<DriverPerson> getTags(@RequestParam String partName) {
        return this.driverPersonService.getByPartFio(partName);
    }

    /**
     * Получить список занесенных в таблицу
     * @return
     */
    @RequestMapping(value = "/ajax/GridDrvAll/get", method = RequestMethod.GET)
    @ResponseBody
    public Set<DriverPerson> getFromGridDrvAll() {
        return this.driverPersonService.getFromGridDrvAll();
    }

    /**
     * Удалить занесенного в таблицу по указанному ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/ajax/GridDrv/del/{id}", method = RequestMethod.GET)
    @ResponseBody
    @JsonView(Respose.class)
    public Respose delGridDrv(@PathVariable("id") String id)
    {
        Respose resp=new Respose();

        if(this.driverPersonService.delGridRecDriver(id)){
            resp.success="ok";
            resp.error="";
        }  else{
            resp.success="failure";
            resp.error="не хочу перехватывать ошибки";
        }
        return resp;
    }



    @JsonView(JSViews.AllData.class)
    @RequestMapping(value = "/ajax/Drv/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DriverPerson getByIDDrv(@PathVariable("id") String id){
        return this.driverPersonService.getByID(id);
    }


    @RequestMapping(value = "/ajax/person/submit", method = RequestMethod.POST)
    public @ResponseBody Respose  submitDriverPerson(@RequestBody final  DriverPerson person ) throws IOException {
        Respose resp=new Respose();

        String res=this.driverPersonService.putPerson(person);
        if(!"error".equals(res)){
            resp.success=res;
            resp.error="";
        }else{
            resp.success="failure";
            resp.error="Ошибка создания/ихменения записи";
        }
        return resp;
    }





}
