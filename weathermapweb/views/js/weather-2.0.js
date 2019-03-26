(function () {
    var myApp = angular.module("app", ['chart.js', 'ui.bootstrap', 'pascalprecht.translate']);
    myApp.config(["$translateProvider", function($translateProvider) {
        $translateProvider.translations('zh_cn', globalLanguageZh);
        $translateProvider.translations('en_us', globalLanguageEn);
        if (!window.localStorage.getItem("lang")) {
            window.localStorage.setItem("lang", navigator.language.toLowerCase() == "zh_cn" ? "zh_cn" : "en_us");
        }
        var lang = window.localStorage.getItem("lang");
        $translateProvider.preferredLanguage(lang);
        $translateProvider.useStaticFilesLoader({
            prefix: 'i18n/',
            suffix: '.json'
        })
    }]);

    myApp.factory("T", ['$translate', function($translate){
        var T = {
            T: function(key){
                if(key){
                    return $translate.instant(key);
                }
                return key;
            }
        }
        return T;
    }]);

    myApp.controller("LineCtrl", function ($scope, $http, $timeout, $filter, $window, $translate, T, $location) {

        $scope.toggleLangModel = {
            lang: window.localStorage.getItem("lang") == "zh_cn" ? "中文" : "English",
            click: function(la) {
               if (la == "en_us" && $translate.use() != "en_us") {
                   $translate.use("en_us");
                   window.localStorage.setItem("lang","en_us");
                   $scope.toggleLangModel.lang="English";
                   $window.location.reload();
               } else if (la == "zh_cn" && $translate.use() != "zh_cn") {
                   $translate.use("zh_cn");
                   window.localStorage.setItem("lang","zh_cn");
                   $scope.toggleLangModel.lang="中文";
                   $window.location.reload();
               }
           }
        };

        $scope.globalData = {
            city: "shenzhen",
            country: "CN",
            tempType: "C",
            text1: T.T("currentCity"),
            text2: T.T("weather36H"),
            text3: T.T("weather5D"),
            text4: T.T("map5D"),
            text5: T.T("UVIndexAndExposureLevel"),
            text6: T.T("lowVolume"),
            text7: T.T("amount"),
            text8: T.T("highVolume"),
            text9: T.T("excess"),
            text10: T.T("excess11"),
            refreshText: function () {
                $scope.globalData.text1 = T.T("currentCity") + $scope.globalData.city + ", " + $scope.globalData.country;
                $scope.globalData.text2 = T.T("weather 36 ")+ $scope.globalData.city + ", " + $scope.globalData.country;
                $scope.globalData.text3 = T.T("weather3D") + $scope.globalData.city + ", " + $scope.globalData.country;
                $scope.globalData.text4 = T.T("map5D") + $scope.globalData.city + ", " + $scope.globalData.country;
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
                $location.search("city", $scope.searchModel.cityNameInput);
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
            series: [$scope.highestTemp, $scope.lowestTemp],
            data: [],
            onClick: function (points, evt) {
                console.log(points, evt);
            },
            // colors: ['#45b7cd', '#ff6384', '#DCDCDC'],
            datasetOverride: [{
                label: $scope.highestTemp,
                borderWidth: 2,
                type: 'line'
            }, {
                label: $scope.lowestTemp,
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
                label: T.T("tempPieChart"),
                borderWidth: 1,
                type: 'bar'
            }, {
                label: T.T("tempCurve"),
                borderWidth: 3,
                hoverBackgroundColor: "rgba(255,99,132,0.4)",
                hoverBorderColor: "rgba(255,99,132,1)",
                type: 'line'
            }],
            type: "Temperature",
            onChange: function (value) {
                if (value == "Wind") {
                    $scope.forecastChartMultiModel.data = [g_pageData.v_barWindData, g_pageData.v_barWindData];
                    $scope.forecastChartMultiModel.datasetOverride[0].label = T.T("windPieChart");
                    $scope.forecastChartMultiModel.datasetOverride[1].label = T.T("windSpeedCurve");
                }
                else if (value == "Pressure") {
                    $scope.forecastChartMultiModel.data = [g_pageData.v_barPressureData, g_pageData.v_barPressureData];
                    $scope.forecastChartMultiModel.datasetOverride[0].label = T.T("pressurePieChart");
                    $scope.forecastChartMultiModel.datasetOverride[1].label = T.T("pressureCurve");
                }
                else if (value == "Precipitation") {
                    $scope.forecastChartMultiModel.data = [g_pageData.v_barPrecipitationData, g_pageData.v_barPrecipitationData];
                    $scope.forecastChartMultiModel.datasetOverride[0].label = T.T("prePieChart");
                    $scope.forecastChartMultiModel.datasetOverride[1].label = T.T("preCurve");
                } else {
                    $scope.forecastChartMultiModel.data = [g_pageData.v_barTempData, g_pageData.v_barTempData];
                    $scope.forecastChartMultiModel.datasetOverride[0].label = T.T("tempPieChart");
                    $scope.forecastChartMultiModel.datasetOverride[1].label = T.T("tempCurve");
                }
            }
        };

        $scope.uviDataModel = {
            date: "",
            value: 0,
            isBeta: false
        };

        function achieveAllWeatherData(v_c) {
            var vCityName = v_c || "shenzhen";;
            if (!$location.search().city) {
                $location.search("city", vCityName);
            } else {
                vCityName = $location.search().city;
            }
            $http({
                method: 'GET',
                url: "/weathermapweb/ui/fusionweatherdata",
                params: {"city": vCityName},
                headers: {"demo": "2.0"},
                timeout: 20000
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
                    $scope.globalData.city = rForecastWeather.cityName || rCurrentWeather.cityName || "";
                    $scope.globalData.country = rForecastWeather.country || rCurrentWeather.country || "";

                    $scope.globalData.refreshText();

                }, function (err, stat) {
                    console.log(err);
                    $scope.restAlertModel.list.push({msg:T.T("noData")});
                }
            );
        }

        function toFahrenheit(num) {
            return (num * 1.8 + 32).toFixed(2);
        }

        achieveAllWeatherData($scope.globalData.city);
    });

    myApp.config(['ChartJsProvider', function (ChartJsProvider) {
    }]);

})();
