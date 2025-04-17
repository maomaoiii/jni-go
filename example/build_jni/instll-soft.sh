#!/bin/bash
yum install unzip -y
wget https://cdn.azul.com/zulu/bin/zulu17.58.21-ca-jdk17.0.15-linux_x64.zip -O zulu17.58.21-ca-jdk17.0.15-linux_x64.zip
unzip zulu17.58.21-ca-jdk17.0.15-linux_x64.zip
mkdir /usr/local/java
mv zulu17.58.21-ca-jdk17.0.15-linux_x64/ /usr/local/java

wget https://go.dev/dl/go1.21.0.linux-amd64.tar.gz -O go1.21.0.linux-amd64.tar.gz
wget https://go.dev/dl/go1.19.linux-amd64.tar.gz -O go1.19.linux-amd64.tar.gz

tar -zxvf go1.19.linux-amd64.tar.gz
mv go /usr/local/

cd /usr/local
sudo wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
sudo tar -xzvf apache-maven-3.9.6-bin.tar.gz
sudo mv apache-maven-3.9.6 maven

# 配置环境变量（以 Bash 为例）
echo 'export MAVEN_HOME=/usr/local/maven' >> /etc/bashrc
echo 'export PATH=$MAVEN_HOME/bin:$PATH' >> /etc/bashrc

source /etc/bashrc


