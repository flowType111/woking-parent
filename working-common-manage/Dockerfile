# 基础镜像
FROM openjdk:8-jdk-alpine

# 指定工作目录
WORKDIR /app

# 将 jar 包添加到工作目录，比如 target/yuoj-backend-user-service-0.0.1-SNAPSHOT.jar
ADD target/working-common-manage-0.0.1-SNAPSHOT-exec.jar .

#指定阿里镜象
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories

#增加字体，解决验证码没有字体报空指针问题
RUN set -xe \
&& apk --no-cache add ttf-dejavu fontconfig

# 暴露端口
EXPOSE 8860

# 启动命令
ENTRYPOINT ["java","-jar","/app/working-common-manage-0.0.1-SNAPSHOT-exec.jar","--spring.profiles.active=prod"]