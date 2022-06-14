FROM debrum/ubuntu-jdk-mvn
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV DB_URL=${DB_URL}
RUN mkdir g3-user
COPY . g3-user
RUN cd g3-user && mvn clean package
RUN cp g3-user/target/*jar /app-user.jar
RUN rm -rf g3-user
ENTRYPOINT [ "java", "-jar", "/app-user.jar" ]