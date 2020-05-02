FROM openjdk:8

COPY harjoitustyo /harjoitustyo

RUN javac /harjoitustyo/Oope2HT.java

CMD ["java", "harjoitustyo/Oope2HT"]