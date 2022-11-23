FROM nginx:alpine
COPY src/main/resources/static /usr/share/nginx/html/
COPY default.conf /etc/nginx/conf.d