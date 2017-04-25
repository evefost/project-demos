FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
# 环境变量
ENV APP_NAME @project.build.finalName@.@project.packaging@

#ADD
ADD dubbo-simple-service.jar .


ENTRYPOINT ["java","-Djava.security.egd=file:/dev./urandom","-jar", "dubbo-simple-service.jar"]