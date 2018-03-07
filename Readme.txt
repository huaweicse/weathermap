天气服务运行步骤：

1. 运行环境准备：安装下载JDK1.8+和Node 4.8.4+版本

2. 打开credentials配置租户ak/sk     (project默认为cn-north-1,如需修改则在credentials增一行project=xxx参数)

3. 执行startup_all.bat脚本

4. 浏览器：http://localhost:3000

运行环境要可以访问外网，天气服务需要连接外部Openweather服务获取天气数据

详情参考：http://support.huaweicloud.com/bestpractice-servicestage/servicestage_bestpractice_0032.html