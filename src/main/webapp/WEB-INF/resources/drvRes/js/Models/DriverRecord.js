'use strict';
define(['knockout'],
    function(ko) {

        return function DriverRecord(item) {

            this.id=ko.observable(item.id);

            this.fio= ko.observable(item.name+" "+item.secondName+" "+ item.lastName );
            this.birthday = ko.observable(item.birthday);
            this.age=ko.observable(item.age>0?item.age:"-");
            this.rawSex=ko.observable(item.sex);
            this.drvClass=ko.observable(item.drvClass);

            this.sex=ko.computed(function(){
                return this.rawSex() ? "M" :"Ж";
            }, this);
        }
    }
);