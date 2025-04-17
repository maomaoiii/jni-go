
export GOEXPERIMENT=cgocheck2
export GODEBUG=invalidptr=1,gctrace=0,cgocheck=2
export GOGC=300

mvn clean package
java -Djava.library.path=so -jar target/jni-go-0.0.1-SNAPSHOT.jar -Xms2g -Xmx2g -Xss256k -XX:+UseG1GC