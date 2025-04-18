FROM  alibaba-cloud-linux-3-registry.cn-hangzhou.cr.aliyuncs.com/alinux3/alinux3

LABEL authors="qqm"

RUN yum install -y git wget gcc make glibc-static glibc-devel tar zip unzip procps-ng

RUN wget -P /tmp https://go.dev/dl/go1.19.linux-amd64.tar.gz && \
    tar -C /usr/local -xzf /tmp/go1.19.linux-amd64.tar.gz && \
    rm /tmp/go1.19.linux-amd64.tar.gz
ENV PATH="/usr/local/go/bin:${PATH}"
ENV CGO_ENABLED=1

RUN wget -P /tmp https://cdn.azul.com/zulu/bin/zulu17.44.53-ca-jdk17.0.8.1-linux_x64.zip && \
    unzip  /tmp/zulu17.44.53-ca-jdk17.0.8.1-linux_x64.zip && \
    mkdir /usr/local/java && \
    mv zulu17.44.53-ca-jdk17.0.8.1-linux_x64 zulu17 &&\
    mv zulu17 /usr/local/java/
ENV JAVA_HOME=/usr/local/java/zulu17

RUN wget -P /tmp https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz && \
    tar -xzf /tmp/apache-maven-3.9.6-bin.tar.gz -C /opt && \
    mv /opt/apache-maven-3.9.6 /opt/maven && \
    rm /tmp/apache-maven-3.9.6-bin.tar.gz
ENV MAVEN_HOME=/opt/maven
ENV PATH="${MAVEN_HOME}/bin:${JAVA_HOME}/bin:${PATH}"

ARG CACHE_BUSTER
WORKDIR /app
#COPY  jni-go.tgz /app
#RUN tar -zxvf jni-go.tgz
RUN git clone https://github.com/maomaoiii/jni-go.git

WORKDIR /app/jni-go/example/build_jni/
RUN bash build-so.sh

WORKDIR /app/jni-go/example/build_jni/
RUN mvn clean package -DskipTests

#ENV LD_LIBRARY_PATH=/app/java-project/src/main/resources/lib:${LD_LIBRARY_PATH}
ENV CGO_ENABLED=1
#ENV GODEBUG=invalidptr=1,cgocheck=2,gctrace=1
#ENV GOGC=30


ENTRYPOINT ["java", "-jar", "-server", "/app/jni-go/example/build_jni/target/demo-0.0.2-SNAPSHOT.jar", \
    "-Xms6g", "-Xmx6g", "-Xss256k", "-XX:MetaspaceSize=256m",  "-Dfastjson.parse.safeMode=true", \
    "-XX:MaxMetaspaceSize=512m", "-XX:MaxDirectMemorySize=4g", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=200", "-XX:G1HeapRegionSize=32m", \
    "-XX:G1ReservePercent=20", "-XX:+PrintHeapAtGC", "-XX:-OmitStackTraceInFastThrow", "-XX:+ParallelRefProcEnabled", \
    "-XX:G1MixedGCCountTarget=16", "-XX:+ExplicitGCInvokesConcurrent", "-XX:ConcGCThreads=8", \
    "--server.tomcat.threads.max=1000", "--server.tomcat.accept-count=300", " --server.tomcat.connection-timeout=20000", "--server.tomcat.threads.min-spare-threads=500"]

