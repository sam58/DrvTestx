/**
 * Created by sam158 on 25.04.2015.
 */
'use strict';
define(
    [
        'knockout'

    ],
    function(ko) {

        return function gridViewModel() {
            var self = this;

            this.searchValue=ko.observable('');

        }
    }
)
