#!/bin/bash

##下载最新版本
git pull
##maven重新打包
mvn clean package

#打包成镜像
docker build -t dashboard:latest .
# 删除正在运行的容器
docker stop dashboard && docker rm dashboard
##运行
docker run -d -p 8080:8080 --name dashboard dashboard