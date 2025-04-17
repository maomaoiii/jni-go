#!/bin/bash
JAVA_HOME=/usr/local/Java/jdk-17.0.9.jdk/Contents/Home

gcc -c jx.c -o jx.o -I$JAVA_HOME/include/ -I$JAVA_HOME/include/darwin/
ar rcs libjx.a jx.o

export CGO_CFLAGS="-I$JAVA_HOME/include/ -I$JAVA_HOME/include/darwin/"
export CGO_LDFLAGS="-L$JAVA_HOME/lib/server -L$(pwd) -ljvm -ljx"


GOOS=darwin GOARCH=arm64 CGO_ENABLED=1 go build -buildmode=c-shared -o libnative-sign.dylib dispatch.go

