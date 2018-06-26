#!/bin/sh
ps -ef |grep java |awk '{print $2}'|xargs kill -9
git pull
mvn clean package -Dmaven.test.skip=true
nohup java -Dvipserver.server.port=8080 -Dpandora.location=/root/.m2/repository/com/taobao/pandora/taobao-hsf.sar/dev-SNAPSHOT/taobao-hsf.sar-dev-SNAPSHOT.jar -jar interface-proxy-main/target/interface-proxy-main.jar --spring.profiles.active=dev &
