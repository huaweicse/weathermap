(function () {

    var myApp = angular.module("app", ["chart.js", 'ui.bootstrap']);

    myApp.controller("LineCtrl", function ($scope, $http, $timeout, $filter, $window) {

        console.log("--- " + $window.localStorage.cityName);
        $scope.globalData = {
            city: $window.localStorage.cityName || "Shenzhen",
            country: $window.localStorage.countryName || "CN",
            tempType: "C",
            text1: "当前城市 Shenzhen, CN",
            text2: "未来36小时预报 Shenzhen, CN",
            text3: "未来5天预报列表 Shenzhen, CN",
            text4: "未来5天预报趋势图 Shenzhen, CN",
            text5: "紫外线指数和暴晒级数的对应关系:",
            text6: "0-2: 低量（适宜）",
            text7: "3-5: 中量（外出需遮阳帽或太阳镜）",
            text8: "6-7: 高量（需涂擦防晒霜）",
            text9: "8-10: 过量（避免在烈日下活动）",
            text10: ">11: 超量（尽量不要外出）",
            refreshText: function () {
                $scope.globalData.text1 = "当前城市 " + $scope.globalData.city + ", " + $scope.globalData.country;
                $scope.globalData.text2 = "未来36小时预报 " + $scope.globalData.city + ", " + $scope.globalData.country;
                $scope.globalData.text3 = "未来3天预报列表 " + $scope.globalData.city + ", " + $scope.globalData.country;
                $scope.globalData.text4 = "未来3天预报趋势图 " + $scope.globalData.city + ", " + $scope.globalData.country;
            }
        };

        $scope.restAlertModel = {
            list: [],
            closeAlert: function (index) {
                $scope.restAlertModel.list.splice(index, 1);
            }
        };

        var g_pageData = {
            v_labels: [],
            v_minDatas: [],
            v_minDatasF: [],
            v_maxDatas: [],
            v_maxDatasF: [],
            v_listGroupDatas: [],
            v_listGroupDatasF: [],
            v_HourlyTableDatas: [],
            v_HourlyTableDatasF: [],
            v_barTempData: [],
            v_barTempDataF: [],
            v_barWindData: [],
            v_barPressureData: [],
            v_barPrecipitationData: []
        };

        $scope.searchModel = {
            cityNameInput: "",
            searchClickFn: function () {
                $window.localStorage.cityName = $scope.searchModel.cityNameInput;
                achieveAllWeatherData($scope.searchModel.cityNameInput);
            }
        };

        $scope.temperatureTypeModel = {
            dataText: "°C",
            changeClickFn: function (val) {
                $scope.globalData.tempType = val;
                if ($scope.globalData.tempType == 'F') {
                    $scope.forecastMainChartModel.data = [g_pageData.v_maxDatasF, g_pageData.v_minDatasF];
                    $scope.forecastMainListGroupModel.data = g_pageData.v_listGroupDatasF;
                    $scope.forecastHourlyTableModel.data = g_pageData.v_HourlyTableDatasF;
                    $scope.forecastChartMultiModel.data = [g_pageData.v_barTempDataF, g_pageData.v_barTempDataF];
                    $scope.weatherTableModel.temp = toFahrenheit($scope.weatherTableModel.tempValue) + " °F";
                } else {
                    $scope.forecastMainChartModel.data = [g_pageData.v_maxDatas, g_pageData.v_minDatas];
                    $scope.forecastMainListGroupModel.data = g_pageData.v_listGroupDatas;
                    $scope.forecastHourlyTableModel.data = g_pageData.v_HourlyTableDatas;
                    $scope.forecastChartMultiModel.data = [g_pageData.v_barTempData, g_pageData.v_barTempData];
                    $scope.weatherTableModel.temp = $scope.weatherTableModel.tempValue + " °C";
                }
            }
        };

        $scope.forecastMainChartModel = {
            labels: [],
            series: ['最高温度', '最低温度'],
            data: [],
            onClick: function (points, evt) {
                console.log(points, evt);
            },
            // colors: ['#45b7cd', '#ff6384', '#DCDCDC'],
            datasetOverride: [{
                label: "最高温度",
                borderWidth: 2,
                type: 'line'
            }, {
                label: "最低温度",
                borderWidth: 2,
                type: 'line'
            }]
        };

        $scope.forecastMainListGroupModel = {};

        $scope.forecastHourlyTableModel = {};

        $scope.forecastChartMultiModel = {
            labels: [],
            series: [],
            data: [],
            // colors: ['#45b7cd', '#ff6384', '#DCDCDC'],
            datasetOverride: [{
                label: "温度饼图",
                borderWidth: 1,
                type: 'bar'
            }, {
                label: "温度曲线",
                borderWidth: 3,
                hoverBackgroundColor: "rgba(255,99,132,0.4)",
                hoverBorderColor: "rgba(255,99,132,1)",
                type: 'line'
            }],
            type: "Temperature",
            onChange: function (value) {
                if (value == "Wind") {
                    $scope.forecastChartMultiModel.data = [g_pageData.v_barWindData, g_pageData.v_barWindData];
                    $scope.forecastChartMultiModel.datasetOverride[0].label = "风速饼图";
                    $scope.forecastChartMultiModel.datasetOverride[1].label = "风速曲线";
                }
                else if (value == "Pressure") {
                    $scope.forecastChartMultiModel.data = [g_pageData.v_barPressureData, g_pageData.v_barPressureData];
                    $scope.forecastChartMultiModel.datasetOverride[0].label = "气压饼图";
                    $scope.forecastChartMultiModel.datasetOverride[1].label = "气压曲线";
                }
                else if (value == "Precipitation") {
                    $scope.forecastChartMultiModel.data = [g_pageData.v_barPrecipitationData, g_pageData.v_barPrecipitationData];
                    $scope.forecastChartMultiModel.datasetOverride[0].label = "降水量饼图";
                    $scope.forecastChartMultiModel.datasetOverride[1].label = "降水量曲线";

                } else {
                    $scope.forecastChartMultiModel.data = [g_pageData.v_barTempData, g_pageData.v_barTempData];
                    $scope.forecastChartMultiModel.datasetOverride[0].label = "温度饼图";
                    $scope.forecastChartMultiModel.datasetOverride[1].label = "温度曲线";
                }
            }
        };

        $scope.uviDataModel = {
            date: "",
            value: 0,
            isBeta: false
        };

        function achieveAllWeatherData(vCityName) {
            $http({
                method: 'GET',
                url: "/weathermapweb/ui/fusionweatherdata",
                params: {"city": vCityName, user: getUrlParam("user")},
                headers: {"demo": "2.0"},
                timeout: 5000
            }).then(function (response) {
                    console.log(response);

                    g_pageData = {
                        v_labels: [],
                        v_minDatas: [],
                        v_minDatasF: [],
                        v_maxDatas: [],
                        v_maxDatasF: [],
                        v_listGroupDatas: [],
                        v_listGroupDatasF: [],
                        v_HourlyTableDatas: [],
                        v_HourlyTableDatasF: [],
                        v_barTempData: [],
                        v_barTempDataF: [],
                        v_barWindData: [],
                        v_barPressureData: [],
                        v_barPrecipitationData: []
                    };

                    // forecast
                    var rForecastWeather = response.data.forecastWeather || {};
                    rForecastWeather.dateList = rForecastWeather.dateList || [];
                    angular.forEach(rForecastWeather.dateList.slice(0, 12), function (item, index) {
                        var tm = $filter('date')(parseInt(item.date) * 1000, "MM-dd HH:mm");
                        g_pageData.v_labels.push($filter('date')(parseInt(item.date) * 1000, "HH:mm"));
                        g_pageData.v_minDatas.push(item.temperatureMin);
                        g_pageData.v_minDatasF.push(toFahrenheit(item.temperatureMin));
                        g_pageData.v_maxDatas.push(item.temperatureMax);
                        g_pageData.v_maxDatasF.push(toFahrenheit(item.temperatureMax));
                        g_pageData.v_listGroupDatas.push({
                            "image": item.image,
                            "weather": item.weather,
                            "temp": item.temperature + " °C",
                            "wind": item.windSpeed + " m/s",
                            "pressure": item.pressure
                        });
                        g_pageData.v_listGroupDatasF.push({
                            "image": item.image,
                            "weather": item.weather,
                            "temp": toFahrenheit(item.temperature) + " °F",
                            "wind": item.windSpeed + " m/s",
                            "pressure": item.pressure
                        });
                        g_pageData.v_HourlyTableDatas.push({
                            "time": tm,
                            "weather": item.weather,
                            "image": item.image,
                            "temp": item.temperature + " °C",
                            "summary": item.windSpeed + " m/s,  " + item.cloudsDeg + " %,  " + item.pressure + " hpa"
                        });
                        g_pageData.v_HourlyTableDatasF.push({
                            "time": tm,
                            "weather": item.weather,
                            "image": item.image,
                            "temp": toFahrenheit(item.temperature) + " °F",
                            "summary": item.windSpeed + " m/s,  " + item.cloudsDeg + " %,  " + item.pressure + " hpa"
                        });
                        g_pageData.v_barTempData.push(item.temperature);
                        g_pageData.v_barTempDataF.push(toFahrenheit(item.temperature));
                        g_pageData.v_barWindData.push(item.windSpeed);
                        g_pageData.v_barPressureData.push(item.pressure);
                        g_pageData.v_barPrecipitationData.push(item.rain3h);
                    });

                    angular.forEach(rForecastWeather.dateList.slice(12), function (item, index) {
                        var tm = $filter('date')(parseInt(item.date) * 1000, "MM-dd HH:mm");
                        g_pageData.v_HourlyTableDatas.push({
                            "time": tm,
                            "weather": item.weather,
                            "image": item.image,
                            "temp": item.temperature + " °C",
                            "summary": item.windSpeed + " m/s,  " + item.cloudsDeg + " %,  " + item.pressure + " hpa"
                        });
                        g_pageData.v_HourlyTableDatasF.push({
                            "time": tm,
                            "weather": item.weather,
                            "image": item.image,
                            "temp": toFahrenheit(item.temperature) + " °F",
                            "summary": item.windSpeed + " m/s,  " + item.cloudsDeg + " %,  " + item.pressure + " hpa"
                        });
                    });

                    $scope.forecastMainChartModel.labels = g_pageData.v_labels;
                    $scope.forecastChartMultiModel.labels = g_pageData.v_labels;
                    if ($scope.globalData.tempType == 'F') {
                        $scope.forecastMainChartModel.data = [g_pageData.v_maxDatasF, g_pageData.v_minDatasF];
                        $scope.forecastMainListGroupModel.data = g_pageData.v_listGroupDatasF;
                        $scope.forecastHourlyTableModel.data = g_pageData.v_HourlyTableDatasF;
                        $scope.forecastChartMultiModel.data = [g_pageData.v_barTempDataF, g_pageData.v_barTempDataF];
                    } else {
                        $scope.forecastMainChartModel.data = [g_pageData.v_maxDatas, g_pageData.v_minDatas];
                        $scope.forecastMainListGroupModel.data = g_pageData.v_listGroupDatas;
                        $scope.forecastHourlyTableModel.data = g_pageData.v_HourlyTableDatas;
                        $scope.forecastChartMultiModel.data = [g_pageData.v_barTempData, g_pageData.v_barTempData];
                    }

                    // current weather
                    var rCurrentWeather = response.data.currentWeather || {};
                    $scope.weatherTableModel = {
                        position: rCurrentWeather.cityName,
                        tempValue: rCurrentWeather.temperature,
                        temp: $scope.globalData.tempType == 'F' ? toFahrenheit(rCurrentWeather.temperature) + " °F" : rCurrentWeather.temperature + " °C",
                        image: rCurrentWeather.image,
                        time: $filter('date')(parseInt(rCurrentWeather.date) * 1000, "yyyy-MM-dd HH:mm"),
                        weather: rCurrentWeather.weather,
                        data: [{
                            name: "Wind",
                            value: rCurrentWeather.windSpeed + " m/s"
                        }, {
                            name: "Cloudiness",
                            value: rCurrentWeather.cloudiness
                        }, {
                            name: "Pressure",
                            value: rCurrentWeather.pressure + " hpa"
                        }, {
                            name: "Humidity",
                            value: rCurrentWeather.humidity + " %"
                        }, {
                            name: "Sunrise",
                            value: $filter('date')(parseInt(rCurrentWeather.sunrise) * 1000, "HH:mm")
                        }, {
                            name: "Sunset",
                            value: $filter('date')(parseInt(rCurrentWeather.sunset) * 1000, "HH:mm")
                        }, {
                            name: "Geo coords",
                            value: "[" + rCurrentWeather.coordinatesLat + ", " + rCurrentWeather.coordinatesLon + "]"
                        }]
                    };

                    // uvi
                    $scope.uviDataModel.date = $filter('date')(parseInt(rCurrentWeather.uviDate) * 1000, "yyyy-MM-dd HH:mm");
                    $scope.uviDataModel.value = rCurrentWeather.uviValue;
                    $scope.uviDataModel.isBeta = rCurrentWeather.uviDateISO != null;

                    // location
                    $scope.globalData.city = rForecastWeather.cityName || rCurrentWeather.cityName || "Shenzhen";
                    $scope.globalData.country = rForecastWeather.country || rCurrentWeather.country || "CN";

                    // localStorage
                    $window.localStorage.cityName = $scope.globalData.city;
                    $window.localStorage.countryName = $scope.globalData.country;

                    $scope.globalData.refreshText();

                }, function (err, stat) {
                    console.log(err);
                    $scope.restAlertModel.list.push({msg: "Failed to achieve the weather data."});
                }
            );
        }

        function toFahrenheit(num) {
            return (num * 1.8 + 32).toFixed(2);
        }

        function getUrlParam(paraName) {
            var url = document.location.toString();
            var arrObj = url.split("?");

            if (arrObj.length > 1) {
                var arrPara = arrObj[1].split("&");
                var arr;

                for (var i = 0; i < arrPara.length; i++) {
                    arr = arrPara[i].split("=");

                    if (arr != null && arr[0] == paraName) {
                        return arr[1];
                    }
                }
                return "";
            }
            else {
                return "";
            }
        }

        achieveAllWeatherData($scope.globalData.city);
    });

    myApp.config(['ChartJsProvider', function (ChartJsProvider) {
    }]);

})();
