#!/bin/bash

##下载最新版本
git pull
##maven重新打包
mvn clean package

cd ..
#打包成镜像
docker build -t dashboard:latest .
##运行
docker run -d -p 8080:8080 --name dashboard dashboard