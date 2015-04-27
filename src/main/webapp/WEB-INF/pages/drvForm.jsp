<%--
  Created by IntelliJ IDEA.
  User: sam158
  Date: 21.04.2015
  Time: 23:32
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" id="drv-css" href="<c:url value='/resources/css/drv.css'/>" type="text/css" media="all">
    <link rel="stylesheet" id="jquery-ui" href="<c:url value='/resources/css/jquery-ui.css'/>" type="text/css" media="all">
    <script type="text/javascript" data-main="<c:url value='/resources/js/init.js'/>" src="<c:url value='/resources/js/require.js'/>"></script>
  <title>Home Page</title>
</head>
<body>

<div class="drv">
    <table id="gridDrivers">
        <thead>
            <tr>
                </th><th>Фамилия Имя Отчество</th><th>Дата рождения</th><th>Возраст</th><th>Пол</th><th>Класс</th><th></th>
            </tr>
        </thead>
        <tbody data-bind="foreach: records">
        <tr>
            <td><span data-bind='text:fio'></span></td>
            <td><span data-bind='text:birthday'></span></td>
            <td><span data-bind='text:age'></span></td>
            <td><span data-bind='text:sex'></span></td>
            <td><span data-bind='text:drvClass'></span></td>
            <td>
                <input type="button" data-bind="click: $parent.removeRecord" value="del" >
                <input type="button" data-bind="click: $parent.editRecord"  value="edit" >
            </td>
        </tr>
        </tbody>
    </table>
        <%--для поля autocomplete--%>
        <div class="searchResult" data-bind="event: { keypress: ac_key}">
            <input id="states" type="text" data-bind="autocomplete: { source: ac_source, minLength: 3 ,select: ac_select, delay: 1000}" class="Width90">
        </div>



        <div class ="editRecordZone">
            <div id="toolboxNew" data-bind="visible: !showEditData()">
                <input type="button" data-bind="click:addRecord" value="Добавить" >
            </div>
            <table id="editRecordTable" data-bind="visible: showEditData">
                <thead>
                <tr>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Дата рождения</th>
                    <th>Пол</th>
                    <th>Класс</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input id="name" type="text" data-bind="value: edit_name" /></td>
                    <td><input id="secondName" type="text" data-bind="value: edit_secondName"/></td>
                    <td><input id="lastName" type="text" data-bind="value: edit_lastName"/></td>
                    <td><input type="text" data-bind="datepicker:{dateFormat: 'dd-mm-yy',
                     dayNames: [ 'Воскресенье', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Суббота' ],
                     dayNamesMin: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб' ],
                     firstDay: 1,
                     maxDate:'-16y -1w',
                     monthNames:['Январь','Февраль','Март','Апрель','Май','Июнь','Июль','Август','Сентябрь','Октябрь','Ноябрь','Декабрь']
                     }, value: edit_birthDay" /></td>
                    <td>
                        <label><input type="radio" value="M" data-bind="checked: edit_showSex" />M</label>
                        <label><input type="radio" value="Ж" data-bind="checked: edit_showSex" />Ж</label>
                    </td>
                    <td><select name="drvClass" id="drvClass"
                                data-bind="options: availableDrvClass(),
                                            value: drvClass,
                                            multiple: false,
                                            optionsCaption: 'Выберите класс водителя...'">
                    </select></td>
                </tr>
                </tbody>
            </table>
            <div id="toolboxEdit" data-bind="visible: showEditData()">
                <input type="button" data-bind="click: saveRecord"  value="Сохранить" >
                <input type="button" data-bind="click: cancelRecord"  value="Отменить" >
            </div>
        </div>









      <%--
      <div>
             Имя: <input data-bind="value: name"/>
      </div>
      <div>
        Класс водителя: <select name="drvClass" id="drvClass"
                         data-bind="options: availableDrvClass(),
                                            value: drvClass,
                                            multiple: false,
                                            optionsCaption: 'Выберите класс водителя...'">

                        </select>
      </div>
      <div>
        <button type="submit">Сохранить</button>
      </div>
        --%>
</div>
</body>
</html>
