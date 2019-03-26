(function () {

    var myApp = angular.module("app", ["chart.js", 'ui.bootstrap']);

    myApp.controller("LineCtrl", function ($scope, $http, $timeout, $filter) {

        $scope.searchModel = {
            cityNameInput: "",
            searchClickFn: function () {
                $scope.forecastMainChartModel.data.reverse();
                //$scope.forecastMainChartModel.labels.reverse();
            }
        };

        $scope.temperatureTypeModel = {
            data: "°C",
            changeClickFn: function (val) {
                console.log(val);
            }
        };

        $scope.forecastMainChartModel = {
            labels: [],
            series: ['highest temperature', 'lowest temperature'],
            data: [],
            onClick: function (points, evt) {
                console.log(points, evt);
            },
            // colors: ['#45b7cd', '#ff6384', '#DCDCDC'],
            datasetOverride: [{yAxisID: 'y-axis-1'}, {yAxisID: 'y-axis-2'}],
            options: {
                scales: {
                    yAxes: [
                        {
                            id: 'y-axis-1',
                            type: 'linear',
                            display: true,
                            position: 'left'
                        },
                        {
                            id: 'y-axis-2',
                            type: 'linear',
                            display: true,
                            position: 'right'
                        }
                    ]
                }
            }
        };

        $scope.forecastMainListGroupModel = {};

        $scope.forecastHourlyTableModel = {};

        $scope.forecastChartMultiModel = {
            labels: [],
            series: ['temperature'],
            data: [],
            // colors: ['#45b7cd', '#ff6384', '#DCDCDC'],
            datasetOverride: [{
                label: "temperature",
                borderWidth: 1,
                type: 'bar'
            }, {
                label: "temperature",
                borderWidth: 3,
                hoverBackgroundColor: "rgba(255,99,132,0.4)",
                hoverBorderColor: "rgba(255,99,132,1)",
                type: 'line'
            }],
            type: "Temperature",
            onChange: function (value) {
                console.log(value);
            }
        };

        $http({
            method: 'GET',
            url: "./mock/forecast.json",
            params: {"q": "shenzhen,cn", "appid": "763d8bb819e1b0fb58c8385ddd26856e"},
            headers: {'Authorization': ""},
            timeout: 5000
        }).then(function (response) {
                console.log(response);
                var v_labels = [];
                var v_minDatas = [];
                var v_maxDatas = [];
                var v_lineDatas = [];
                var v_listGroupDatas = [];
                var v_HourlyTableDatas = [];
                var v_barTempData = [];
                angular.forEach(response.data.list.slice(0, 12), function (item, index) {
                    var tm = $filter('date')(parseInt(item.dt) * 1000, "MM-dd HH:mm");
                    v_labels.push($filter('date')(parseInt(item.dt) * 1000, "HH:mm"));
                    v_minDatas.push(item.main.temp_min);
                    v_maxDatas.push(item.main.temp_max);
                    v_listGroupDatas.push({
                        "image": item.weather[0].icon,
                        "weather": item.weather[0].main,
                        "temp": item.main.temp + " °C",
                        "wind": item.wind.speed + " m/s",
                        "pressure": item.main.pressure
                    });
                    v_HourlyTableDatas.push({
                        "time": tm,
                        "weather": item.weather[0].description,
                        "image": item.weather[0].icon,
                        "temp": item.main.temp + " °C",
                        "summary": item.wind.speed + " m/s,  " + item.clouds.all + " %,  " + item.main.pressure + " hpa"
                    });
                    v_barTempData.push(item.main.temp);
                });
                v_lineDatas.push(v_maxDatas);
                v_lineDatas.push(v_minDatas);

                $scope.forecastMainChartModel.labels = v_labels;
                $scope.forecastMainChartModel.data = v_lineDatas;
                $scope.forecastMainListGroupModel.data = v_listGroupDatas;
                $scope.forecastHourlyTableModel.data = v_HourlyTableDatas;

                $scope.forecastChartMultiModel.labels = v_labels;
                $scope.forecastChartMultiModel.data = [v_barTempData, v_barTempData];

            }, function (err, stat) {
                console.log(err);
            }
        );

        $http({
            method: 'GET',
            url: "./mock/weather.json",
            params: {"q": "shenzhen,cn", "appid": "763d8bb819e1b0fb58c8385ddd26856e"},
            headers: {'Authorization': ""},
            timeout: 5000
        }).then(function (response) {
                console.log(response);
                var d = response.data;
                $scope.weatherTableModel = {
                    position: d.name,
                    temp: d.main.temp + " °C",
                    image: d.weather[0].icon,
                    time: $filter('date')(parseInt(d.dt) * 1000, "yyyy-MM-dd HH:mm"),
                    weather: d.weather[0].description,
                    data: [{
                        name: "Wind",
                        value: d.wind.speed + " m/s"
                    }, {
                        name: "Cloudiness",
                        value: d.weather[0].description
                    }, {
                        name: "Pressure",
                        value: d.main.pressure + " hpa"
                    }, {
                        name: "Humidity",
                        value: d.main.humidity + " %"
                    }, {
                        name: "Sunrise",
                        value: $filter('date')(parseInt(d.sys.sunrise) * 1000, "HH:mm")
                    }, {
                        name: "Sunset",
                        value: $filter('date')(parseInt(d.sys.sunset) * 1000, "HH:mm")
                    }, {
                        name: "Geo coords",
                        value: "[" + d.coord.lat + ", " + d.coord.lon + "]"
                    }]
                };
            }, function (err, stat) {
                console.log(err);
            }
        );
    });

    myApp.config(['ChartJsProvider', function (ChartJsProvider) {
    }]);

})();