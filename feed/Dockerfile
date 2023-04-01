FROM openjdk:17-oracle

ARG JAR_FILE=target/*.jar

RUN mkdir /opt/feed

COPY ${JAR_FILE} /opt/feed/news_feed.jar

ENTRYPOINT ["java","-jar","/opt/feed/news_feed.jar"]