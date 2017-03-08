# natural language process
#Liang Yuan Siwen Ou
#How to build the project
1. go to the root directory of the project
2. using command line, mvn package (if all codes are correct, it will show build success)

#How to run the project
1. using command line, sh target/bin/webapp
2. using web broswer, input: http://localhost:8080

#How to submit to heroku
1. git init
2. git add
3. git commit -m ""
4. heroku create
5. git push heroku master
6. heroku open

#note: add jar if needed, go to the folder of the jar
1. mvn install:install-file -Dfile=stanford-corenlp-1.3.4.jar -DgroupId=edu.stanford.nlp -DartifactId=stanford-corenlp -Dversion=1.3.4 -Dpackaging=jar
2. mvn install:install-file -Dfile=stanford-corenlp-1.3.4-models.jar -DgroupId=edu.stanford.nlp -DartifactId=stanford-corenlp-models -Dversion=1.3.4 -Dpackaging=jar
3. mvn install:install-file -Dfile=stanford-corenlp-1.3.4-sources.jar -DgroupId=edu.stanford.nlp -DartifactId=stanford-corenlp-sources -Dversion=1.3.4 -Dpackaging=jar
