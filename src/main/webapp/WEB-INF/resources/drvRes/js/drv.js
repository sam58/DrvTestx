/**
 * Created by sam158 on 22.04.2015.
 * models and other for KO (all in one for test)
 */
function ViewModel() {
    var self = this;

    self.name = ko.observable('');
    self.drvClass = ko.observable('');

    self.availableDrvClass = ko.observableArray([]);

    /**
     *  Загрузить классы водителя для списка
     */
    self.loadDrvClass = function() {
        debugger;
        $.get("ajax/drvClasses/get", function (data) {
            self.availableDrvClass(data);
        });
    };
    /**
     * Загрузка данных по всей модели
     */
    self.load = function() {
        self.loadDrvClass();
    };

    // готовлю json по модели
    ViewModel.prototype.toJSON = function() {
        var self = this;
        return { name: self.name, drvClass: self.drvClass }
    };

    //Сабмит данных на сервер(присоединен к форме)
    self.onSubmit = function() {

        var data = ko.toJSON(self);

        $.ajax({
            type: 'POST',
            url: 'ajax/person/submit',
            data: data,
            contentType: "application/json",
            error: function(data, textStatus, xhr) {
                alert(xhr);
            }
        });
    }

}

////////////////////////for test only///////////////////////////////////////////////////////



var PagedGridModel = function() {
    var self = this;

    self.allDrivers = ko.observableArray([]);

    self.loadAllDrivers = function() {
        debugger
        $.get("/ajax/AllDriver/get", function (data) {
            debugger
            var mappedDrivers = $.map(allData, function(item) { return new Task(item) });
            self.allDrivers(data);
        });
    };

    self.load = function() {
        self.loadAllDrivers();
    };
}


$(document).ready(function() {
    //------------------jQuery



//___________________KO____________________________

    var viewModel = new ViewModel();

    ko.applyBindings(p_gridModel,$("#topGrid")[0]);
    ko.applyBindings(viewModel,$("#mainForm")[0]);

    viewModel.load();
    p_gridModel.load();
});