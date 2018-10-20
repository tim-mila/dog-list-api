FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ADD target/thin/root/repository m2/repository
ADD ./target/dog-breed-list-api*.jar dog-breed-list-api.jar
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar dog-breed-list-api.jar --thin.root=/m2" ]