From 100.125.0.198:20202/hwcse/nodejs:1.0.0

WORKDIR /home/apps/
COPY ./app.js ./
COPY ./bin ./bin
COPY ./package.json ./
COPY ./package-lock.json ./
COPY ./public ./public
COPY ./routes ./routes
COPY ./shutdown.sh ./
COPY ./start.sh ./
COPY ./views ./views


ENTRYPOINT ["sh", "/home/apps/start.sh"]
