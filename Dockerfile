FROM 6c6249b9f7f5

LABEL authors="qqm"

RUN yum install -y git wget gcc make glibc-static glibc-devel tar zip unzip

RUN wget -P /tmp https://golang.org/dl/go1.19.linux-amd64.tar.gz && \
    tar -C /usr/local -xzf /tmp/go1.19.linux-amd64.tar.gz && \
    rm /tmp/go1.19.linux-amd64.tar.gz
ENV PATH="/usr/local/go/bin:${PATH}"
ENV CGO_ENABLED=1

RUN wget -P /tmp https://cdn.azul.com/zulu/bin/zulu17.58.21-ca-jdk17.0.15-linux_x64.zip && \
    unzip  /tmp/zulu17.58.21-ca-jdk17.0.15-linux_x64.zip && \
    mkdir /usr/local/java && \
    mv zulu17.58.21-ca-jdk17.0.15-linux_x64/ /usr/local/java/zulu17
ENV JAVA_HOME=/usr/lib/java/zulu17

RUN wget -P /tmp https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz && \
    tar -xzf /tmp/apache-maven-3.9.6-bin.tar.gz -C /opt && \
    mv /opt/apache-maven-3.9.6 /opt/maven && \
    rm /tmp/apache-maven-3.9.6-bin.tar.gz
ENV MAVEN_HOME=/opt/maven
ENV PATH="${MAVEN_HOME}/bin:${JAVA_HOME}/bin:${PATH}"

RUN git clone https://x-access-token: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@github.com/maomaoiii/jni-go.git /app

WORKDIR /app/example/build_jni/
RUN bash build-so.sh

WORKDIR /app/example/build_jni/
RUN mvn clean package -DskipTests

#ENV LD_LIBRARY_PATH=/app/java-project/src/main/resources/lib:${LD_LIBRARY_PATH}


ENTRYPOINT ["java", "-jar", "/app/example/build_jni/target/demo-0.0.1-SNAPSHOT.jar", "-Xms2g", "-Xmx2g", "-Xss256k", "-XX:+UseG1GC", "-XX:MetaspaceSize=256m",  "-Dfastjson.parse.safeMode=true"]

