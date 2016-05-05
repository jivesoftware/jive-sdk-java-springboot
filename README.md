<img align="right" src="https://github.com/jivesoftware/jive-sdk-java-springboot/blob/master/sdk-logo.png?raw=true" width="128">

This is an implementation of the Jive SDK using Java w/SpringBoot.  For questions about this project, please visit the [Jive Developer Community](https://community.jivesoftware.com/community/developer)

#### Pre-Requisites

1. Install [Git](http://git-scm.com/book/en/Getting-Started-Installing-Git) (needed for cloning and contributing)
2. Install [Maven 3.3.x](http://maven.apache.org/download.cgi)
3. Install [Java 8 SDK](http://www.oracle.com/technetwork/java/javaee/downloads/index.html)

#### Getting Started
This SDK is more of a boiler plate application that gets your service up and running and FAST.

>Before you get started, make sure your default Java Runtime is Java 8. 
You can execute **java -version** to confirm.   (see Mac Commands below for possible help)

To get started, simply perform the following steps:

1. **git clone https://github.com/jivesoftware/jive-sdk-java-springboot.git**
2. **cd jive-sdk-java-springboot**
3. **Configure Service + JPA settings in /src/main/resources/application.properties**
4. **mvn spring-boot:run**

Congrats, your Jive Add-On Service is should now be running with an embedded Tomcat container!  Now we just need to link a Jive Instance to it! 

---

#### Java Environment Tip & Tricks

##### Mac Tricks
To switch your Terminal Java Runtime back to Java 8, use:
>export JAVA_HOME=\`/usr/libexec/java_home -v 1.8\`

To switch your Terminal Java Runtime back to Java 7, use:
>export JAVA_HOME=\`/usr/libexec/java_home -v 1.7\`

To switch your Terminal Java Runtime back to Java 6, use:
>export JAVA_HOME=\`/usr/libexec/java_home -v 1.6\`

##### Windows Tricks
TODO:


##### Linux (Ubuntu/CentOS/RHEL/SUSE/etc..) Tricks
TODO: 

---

#### Deployment Instructions

**Instructions for deploying on Google Cloud Platform**
* TODO - Instructions

#### Supports
- Jive Signature Validation
- Jive Signed Fetch Validation
- Add-On Register/Unregister
- Add-On Configuration Register/Unregister
- Tile Register/Unregister
- Static App + Custom View Tile Hosting

#### Need:
- Support for Tile Data Pushes
- Support for Jive API Client
- Support for Jive Analytics Client
- Unit Tests that can be run to insure that services haven't changed between versions
