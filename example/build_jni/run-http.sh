#!/bin/bash
killall -9 java
mvn clean compile
mvn spring-boot:run -Djava.library.path=$(pwd)/ -Dspring-boot.run.arguments='--server.port=7111'
