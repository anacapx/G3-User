FROM debrum/ubuntu-jdk-mvn
RUN mkdir g3-user
COPY . g3-user
RUN cd g3-user && mvn clean package -Dmaven.test.skip
RUN cp g3-user/target/*jar /app-user.jar
RUN cp g3-user/newrelic/*jar /newrelic-user.jar
RUN cp g3-user/newrelic/*yml /newrelic.yml
ENV NEW_RELIC_APP_NAME=${NEW_RELIC_APP_NAME}
ENV NEW_RELIC_LICENSE_KEY=${NEW_RELIC_LICENSE_KEY}
ENV NEW_RELIC_LOG_FILE_NAME=${NEW_RELIC_LOG_FILE_NAME}
EXPOSE 8082
ENTRYPOINT ["java","-javaagent:/newrelic-user.jar","-jar","/app-user.jar"]
