# Natural language process
#developer: Liang Yuan Siwen Ou
#How to build the project locally
1. go to the root directory of the project
2. using command line, mvn package (if all codes are correct, it will show build success)

#How to run the project locally
1. using command line, sh target/bin/webapp
2. using web broswer, input: http://localhost:8080

#How to deploy the system to heroku
install heroku command line first. (https://devcenter.heroku.com/articles/heroku-cli)
1. go to the root directory of project 
2. git init
3. git add .
4. git commit -m "new apps"
5. heroku create
6. git push heroku master
7. heroku open

#Database configuration
https://devcenter.heroku.com/articles/heroku-postgresql
