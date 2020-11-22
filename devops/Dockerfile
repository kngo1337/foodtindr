# Basic Dockerfile

FROM centos:7

# Your rpm needs to be in the same file as your Dockerfile.  
# cp $JENKINS_BUILD/myrpm.rpm  $SRC_PATH/myrpm.rpm
COPY $SRC_PATH/myrpm.rpm $DEST_PATH/myrpm.rpm

# Install the rpm that Jenkins built
RUN yum -y update \ 
  && yum -y install java-11-openjdk.x86_64 \
  && yum -y install myrpm.rpm

# Set up what you want to happen when you do "docker run" 
# In this case, want to run the java server
CMD $TARGET_PATH/runjava.sh javaclass.MyServer




# === Docker Commands ===

# Build image with:
#    docker build --tag myproj:latest .
# Run the container
#    docker run -d -p 8000:8000 --name projContainer myproj:latest
# Stop the container
#    docker stop projContainer



