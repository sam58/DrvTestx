/**
 * Created by sam158 on 24.04.2015.
 */
'use strict';
require.config({
    baseUrl: '/resources/js',
    paths: {
        jquery: 'lib/jquery-2.1.3.min',
        'jquery-ui': 'lib/jquery-ui',

        knockout:'lib/knockout-3.3.0',
        'knockout-jqueryui': 'lib/knockout-jqueryui',
        domReady:'lib/domReady'
    }

});

require(['knockout',
         'jquery',
         'jquery-ui',
         'Models/gridViewModel',
         'domReady!'],
  function(ko,$, ac, gridViewModel) {


      debugger
      ko.applyBindings(new gridViewModel());

//  ko.applyBindings(new gridViewModel(),$('#gridDrivers')[0]);
//  ko.applyBindings(new searchFormModel(),$('#searchForm')[0]);


   /*   $('#w-input-search').autocomplete({
          serviceUrl: '/ajax/byPart/get',
          paramName: "partName",
          minChars:3, //Ищу только с трех символов
          deferRequestBy:500,//Начинаю искать только через полсекунды,что бы не слалась куча запросов
          delimiter: ",",
          transformResult: function(response) {
              return {
                  suggestions: $.map($.parseJSON(response), function(item) {
                      return { value: item.name+" "+item.secondName+" "+ item.lastName , data: item.id };
                  })
              };
          }
      });
*/


  }
);