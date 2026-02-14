FROM tomcat:9.0.115-jre21
RUN rm -rf /usr/local/tomcat/webapps/*
WORKDIR /usr/local/tomcat/webapps/
COPY target/puzzles-1.0-SNAPSHOT-0.0.1.war puzzles.war
EXPOSE 8080
CMD ["catalina.sh", "run"]