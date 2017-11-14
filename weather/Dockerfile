FROM java:8-jdk

WORKDIR /home/apps/
ADD target/currentweatherdata-1.0.0.jar .
ADD target/lib ./lib
ADD start.sh .

ENTRYPOINT ["sh", "/home/apps/start.sh"]