"use strict";

/******************************************************************************************

Expenses controller

******************************************************************************************/

var app = angular.module("expenses.controller", []);

app.controller("ctrlExpenses", ["$rootScope", "$scope", "config", "restalchemy", "$http", "$interval", function ExpensesCtrl($rootScope, $scope, $config, $restalchemy, $http, $interval) {
	// Update the headings
	$rootScope.mainTitle = "Expenses";
	$rootScope.mainHeading = "Expenses";

	// Update the tab sections
	$rootScope.selectTabSection("expenses", 0);

	var restExpenses = $restalchemy.init({ root: $config.apiroot }).at("expenses");

	$scope.dateOptions = {
		changeMonth: true,
		changeYear: true,
		dateFormat: "dd/mm/yyyy"
	};
        
        $scope.currencyExpense = {
            inputAmount : "",
            amount : "",
            vat : "",
            toCurrency : ""
        };
        
        $scope.currency = {
            exchangeRate : "",
            currency : "EUR"
        };

	var loadExpenses = function() {
		// Retrieve a list of expenses via REST
		restExpenses.get().then(function(expenses) {
			$scope.expenses = expenses;
		});
	};

	$scope.saveExpense = function() {
            if ($scope.expensesform.$valid) {
                
                $scope.newExpense.amount = $scope.currencyExpense.toCurrency;
                $scope.newExpense.vat = ($scope.newExpense.amount  - $scope.newExpense.amount  * 100 / 120).toFixed(2);

                // Post the expense via REST
                restExpenses.post($scope.newExpense).then(function() {
                        // Reload new expenses list
                        loadExpenses();
                        $scope.clearExpense();
                });
            }
	};

	$scope.clearExpense = function() {
		$scope.newExpense = {};
                $scope.currencyExpense = {};
	};
        
        $scope.getExchangeRate = function() {
            //Real time API rest for currency conversion
            $http.get('https://api.fixer.io/latest?base=EUR').then(function(response) {
                $scope.currency.exchangeRate = response.data.rates["GBP"];
            });
        };
        
        //Refresh the exchange rate every minute.
        $interval(function() {
            $scope.getExchangeRate();
        }, 60000);
        
        $scope.setCurrencyExpense = function() {
            if ($scope.currencyExpense.inputAmount && 
                    !isNaN($scope.currencyExpense.inputAmount.split(" ")[0])) { 
                
                var parts = $scope.currencyExpense.inputAmount.split(" ");
                
                if (parts.length === 1 || (parts.length === 2 && $scope.currency.currency === parts[1])) {
                    $scope.currencyExpense.amount = $scope.currencyExpense.toCurrency = parts[0];
                    $scope.currencyExpense.vat = 
                            ($scope.currencyExpense.amount - $scope.currencyExpense.amount * 100 / 120).toFixed(2);

                    if (parts.length === 2 && $scope.currency.currency === parts[1]) {
                        $scope.currencyExpense.toCurrency = $scope.currencyExpense.amount * $scope.currency.exchangeRate;
                    }
                    return;
                }
            }
            
            $scope.currencyExpense.amount = "";
            $scope.currencyExpense.vat = "";
            $scope.currencyExpense.toCurrency = "";
        };

	// Initialise scope variables
	loadExpenses();
        $scope.getExchangeRate();
	$scope.clearExpense();
}]);
