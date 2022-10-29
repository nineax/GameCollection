FROM nginx:1.10.1-alpine
COPY src /user/share/nginx

CMD ["nginx", "-g", "GameCollection"];