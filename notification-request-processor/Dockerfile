FROM websphere-liberty:23.0.0.3-full-java8-ibmjava

LABEL description="Pattern Sample - Notification Request"

USER root


ENV SSL_TRUSTSTORE_LOCATION=/opt/ibm/java/jre/lib/security/cacerts

COPY ./target/notificationprocessor.war /config/apps/notificationprocessor.war
COPY ./resources/server.xml /config/
RUN ls -lhtr /config/
RUN ls -lhtr /config/apps/
# RUN ls -lhtr /config/lib/
RUN cat /config/server.xml

COPY wlp-base-license.jar /tmp/
RUN java -jar /tmp/wlp-base-license.jar --acceptLicense /opt/ibm \
    && rm /tmp/wlp-base-license.jar

EXPOSE 9080
EXPOSE 9443

ENV LICENSE accept