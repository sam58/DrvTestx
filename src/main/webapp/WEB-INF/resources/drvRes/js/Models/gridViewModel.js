/**
 * Created by sam158 on 24.04.2015.
 */
'use strict';
define(
    [
        'knockout',
        'Models/DriverRecord',
        'knockout-jqueryui/autocomplete',
        'knockout-jqueryui/datepicker'
    ],
  function(ko,DriverRecord,ac) {

    return function gridViewModel() {
        var self=this;

        this.records = ko.observableArray([]);



        this._allDrivers = function(data){

            var mappedDrivers = $.map(data, function(item) {
                                            return new DriverRecord(item)
                                        }
                                    );
            self.records(mappedDrivers);

        };

        //Функция общей загрузки
        this.loadAllDrivers = function() {
            $.get("/ajax/GridDrvAll/get",
                function (data) {
                    //todo проверка направильность р-та
                    self._allDrivers(data);
                }
            );
        };

        //Редактирование записи
        this.editRecord = function(rec){
            alert(rec.id());
        };

        //удаление записи
        this.removeRecord=function(rec){
            var reqStr="/ajax/GridDrv/del/"+ rec.id();
            $.get(reqStr,
                function(ans){
                    if(ans.success!="ok"){
                        alert("!! error"+ans.error);
                        //после ошибки перерисовываю целиком
                        self.records.removeAll();
                        self.loadAllDrivers();
                    }else{
                        //при успехе прибиваю только тукущую
                        self.records.remove(rec);
                    }
                }
            )
        };
//--------------------------------------------------------
        //поисковый autocomplete
        this.ac_selected=ko.observable('');
        this.ac_items=ko.observableArray([]);
        this.ac_itemSelectedID=ko.observable('');
        this.ac_cssSearchField=ko.observable('');

        this.ac_key=function(data, event){
            if (event.keyCode === 13)
            {
               debugger
            }
            console.dir(data);
            console.dir(event);
            return true;
        }



        this.ac_select = function(ev, ui){
            if(ui && !!ui.item){
                self.ac_selected(ui.item.id);
            }else{
             //   self.ac_selected(-1);
            }

        };


        this._timer=null;
        //Обработка результата аутокомплетед
        this._pricessRetData=function(data){
            if(!data || data.length<1 ){
                if(this._timer){
                    clearTimeout(self._timer);
                }
                self._timer = setTimeout(alert('по заданному шаблону поиска не найден ни один человек'), 1000);// задержка сообщениян на секунду
                return [];
            }else{
                return $.map( data, function( item ) {
                    return {
                        id: item.id,
                        value:item.birthday+" "+item.name+" "+item.secondName+" "+ item.lastName
                    };
                })
            }
        };

        this.ac_source=function (request, response) {
            $.get("/ajax/byPart/get", {
                partName: request.term
            }, function (data) {
                    response(
                        self._pricessRetData(data)
                    )
            });
        };

//Новая запись

        this.availableDrvClass = ko.observableArray([]);
        this.drvClass = ko.observableArray();

        this.edit_name = ko.observable('');
        this.edit_id = ko.observable('');
        this.edit_lastName= ko.observable('');
        this.edit_secondName= ko.observable('');
        this.edit_birthDay=ko.observable('');
        this.edit_sex=ko.observable(false);
        this.edit_showSex= ko.pureComputed({
            read: function () {
                var t = this.edit_sex()?"М":"Ж";
                return t;
            },
            write: function (value) {
                this.edit_sex(value==="M" ? true : false);
            },
            owner: self
        });
        this.showEditData=ko.observable(false);

        this.addRecord=function(){
            self.edit_id('new');
            self.clearEditData(true);
        };

        //TODO Должна проверять правильность заполнения и кидать на неверные. Но сейчас только стаб
        this._checkEditRecord=function(){
            return true;
        };

        this.refreshRecordsFromEdit=function(){

        };

        this.clearEditData=function(showData){
            this.drvClass('');
            this.edit_name('');
            this.edit_lastName('');
            this.edit_secondName('');
            this.edit_sex(true);
            self.edit_birthDay('');
            this.drvClass('');
            this.showEditData(showData);

        };
        this.cancelRecord=function(){
            self.edit_id('');
            self.clearEditData(false);
        };

        this.saveRecord=function(){
            if(!self._checkEditRecord()){return}

            var saveRecordData={
                id:self.edit_id(),
                name:self.edit_name(),
                lastName:self.edit_lastName(),
                drvClass:self.drvClass(),
                birthday:self.edit_birthDay(),
                secondName:self.edit_secondName(),
                sex:self.edit_sex()
            };
            debugger;
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '/ajax/person/submit',
                data:JSON.stringify( saveRecordData),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function( data, textStatus,jqXHR ){
                    if(data.error==""){
                        self.getDrvByIDToRec(data.success);
                        self.clearEditData(false);
                    }else{
                        alert(error);
                    }
                },
                error: function(data, textStatus, xhr) {
                    alert(xhr);
                }
            });
        };

        /**
         *  Загрузить классы водителя для списка
         */
        this.loadDrvClass = function() {
            debugger;
            $.get("ajax/drvClasses/get", function (data) {
                self.availableDrvClass(data);
            });
        };

        this.getDrvByIDToRec=function(id){
            var reqStr="/ajax/Drv/get/"+ id;
            $.get(reqStr,
                function (item) {

                    self.records.push(new DriverRecord(item));
                }
            );
        }



        //первонаяальный инит
        this.loadAllDrivers();
        this.loadDrvClass();
    };
});