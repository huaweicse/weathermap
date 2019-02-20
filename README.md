# WEATHERMAP

> * **WEATHERMAP** is a weather forecasting app that gives you current weather details around the world and forecasts for the next three days.
> * This demo shows you the basic capabilities of the **CSE microservices engine** and the ability to support **rapid development** of new services and **zero-transform access** to existing services.

# Building

* **Java 8 +**  and **Node 4.8.4+**
* Modify file **credentials**, set with your own **AS/SK**. 

> Default **project** is **cn-north-1**. To modify it, you can simply set the following property in **credentials** :

```
project=xxx
```
* Run **startup_all.bat** or **startup_all.sh**
* **WebBrowser**: http://localhost:3000
> As **WEATHERMAP** needs to obtain weather forecast data through the Openweather service, the working environment must be able to **connect the Internet**.

more details: http://support.huaweicloud.com/bestpractice-servicestage/servicestage_bestpractice_0032.html
