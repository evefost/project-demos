FROM openjdk:8-jdk

# 将压缩包添加到images /usr/local目录下
ADD dubbo-simple-service.tar.gz /usr/local
#将 startup.sh 脚本copy 到 docker /usr/local/skywalking-web/bin 目录下
COPY start.sh /usr/local/dubbo-simple-service/bin

RUN  chmod +x /usr/local/dubbo-simple-service/bin/start.sh

ENTRYPOINT [ "" ]
CMD [ "/usr/local/dubbo-simple-service/bin/start.sh" ]
