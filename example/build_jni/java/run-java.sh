#!/bin/bash
go121=$(go version | grep "1.21")
export CGO_ENABLED=1
export GODEBUG=invalidptr=1
if [[ "$go121" == "x" ]]; then
  echo "not go 1.19"
  export GODEBUG=cgocheck=2
else
  echo "go 1.21"
  export GOEXPERIMENT=cgocheck2
fi

javac -d target/classes -cp  ".:./jar/fastjson-1.2.83.jar" com/example/*.java && \
java -Djava.library.path=../ -cp "target/classes:./jar/fastjson-1.2.83.jar" com.example.Main -Xms2g -Xmx2g -Xss256k -XX:+UseG1GC
