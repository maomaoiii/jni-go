#!/bin/bash
killall -9 java
mvn clean compile
mvn spring-boot:run -Djava.library.path=$(pwd)/ -Dspring-boot.run.arguments='--server.port=7111' -Dspring-boot.run.jvmArguments="-Xms2g -Xmx2g -Xss256k -XX:+UseG1GC -XX:MetaspaceSize=256m -Dfastjson.parse.safeMode=true"
