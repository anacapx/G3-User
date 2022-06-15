FROM debrum/ubuntu-jdk-mvn
RUN mkdir g3-user
COPY . g3-user
RUN cd g3-user && mvn clean package -Dmaven.test.skip
RUN cp g3-user/target/*jar /app-user.jar
RUN rm -rf g3-user
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "/app-user.jar" ]