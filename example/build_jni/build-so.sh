#!/bin/bash

gcc -c jx.c -o jx.o -I$JAVA_HOME/include/ -I$JAVA_HOME/include/linux/
ar rcs libjx.a jx.o

export CGO_CFLAGS="-I$JAVA_HOME/include/ -I$JAVA_HOME/include/linux/"
export CGO_LDFLAGS="-L$JAVA_HOME/lib/server -L$(pwd) -ljvm -ljx"

GOOS=linux GOARCH=amd64 CGO_ENABLED=1 go build -buildmode=c-shared -o libnative-sign.so dispatch.go
