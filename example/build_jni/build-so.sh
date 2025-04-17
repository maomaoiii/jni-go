#!/bin/bash

gcc -c jx.c -o jx.o -I$JAVA_HOME/include/ -I$JAVA_HOME/include/linux/
ar rcs libjx.a jx.o

export CGO_CFLAGS="-I$JAVA_HOME/include/ -I$JAVA_HOME/include/linux/"
export CGO_LDFLAGS="-L$JAVA_HOME/lib/server -L$JAVA_HOME/jre/lib/amd64/server -L$(pwd) -ljvm -ljx"

# go1.21
#GOOS=linux GOARCH=amd64 CGO_ENABLED=1 GODEBUG=invalidptr=1 GOEXPERIMENT=cgocheck2 GOGC=30 go build -buildmode=c-shared -o libnative-sign.so

#go1.19
GOOS=linux GOARCH=amd64 CGO_ENABLED=1 GODEBUG=invalidptr=1 GODEBUG=cgocheck=2 GOGC=30 go build -buildmode=c-shared -o libnative-sign.so

cp libnative-sign.so /usr/lib64
