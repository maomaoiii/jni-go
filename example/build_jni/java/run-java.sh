#!/bin/bash

javac -d target/classes -cp  ".:./jar/fastjson-1.2.83.jar" com/example/*.java && \
java -Djava.library.path=../ -cp "target/classes:./jar/fastjson-1.2.83.jar" com.example.Main
