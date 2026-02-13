FROM node:22 AS build

ARG BUILD

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm install --legacy-peer-deps

COPY . .

RUN if [ -z "$BUILD" ]; then \
    npm run build -- --configuration production; \
    else \
    npm run build -- --configuration ${BUILD}; \
    fi


FROM node:22-alpine

WORKDIR /app

RUN npm install -g http-server

COPY --from=build /app/dist/utpTechnology/browser /app

RUN cp index.html 404.html

EXPOSE 8081

CMD [ "http-server", "-p", "8081", "-c60", "." ]
