FROM centos:7
WORKDIR /apps
COPY ./foodtindr-1.0.0-1.noarch.rpm food.rpm

RUN yum -y update \
  && yum -y install java-11-openjdk.x86_64 \
  && yum -y install food.rpm

CMD /apps/foodtindr/bin/runjava.sh food.MyServer