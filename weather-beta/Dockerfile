FROM 100.125.0.198:20202/hwcse/dockerhub-java:8-jre-alpine

WORKDIR /home/apps/
ADD target/currentweatherdata-2.0.0.jar .
ADD target/lib ./lib
ADD start.sh .

ENTRYPOINT ["sh", "/home/apps/start.sh"]
