# foodtindr
An iOS app that helps users find a restaurant to agree on.
This idea came from our group spending a lot of time deciding on restaurants.
So we made a Tinder-like food app that will select a winning restaurant if everyone in the group agrees.

![](foodtindr.gif)


# Devops
### Setup keys
- Allows people to log into EC2 instance

### Jenkins
1) Install Jenkins
2) Setup a Jenkins Job
- connect to java repository
- mvn package
    - this creates the food.rpm file

### Docker
1) copy the food.rpm file into the Docker build directory (with the Dockerfile)
2) docker build --tag food .
- creates an image food with our RPM

3) run our container
docker rm -f dumbun; docker run -p 80:8000 -d --name dumbun food; docker exec -it dumbun /bin/bash
- container is name dumbun
- port maps 80 to 8000 b/c webserver is started on 8000
- use image food
- exec into the container


4) verify that the container is running
- get the container IP using `hostname -I`
- curl "IP":80 -> see helloworld to know that container is running with the webserver


# Java
    Maven Project
        Manages Dependencies (Jetty/Jenkins/Gson)
        Build RPMs for DevOps team
    Uses Jetty/Jenkins for Webserver

Design for iOS and Java Web Server
https://drive.google.com/file/d/1TdYJzHtEsRMcE3R6fXWgJEna8r-z6P9P/view?usp=sharing

# iOS
Credit for the Swipe Interface: https://github.com/Onaeem26/TinderSwipeInterface