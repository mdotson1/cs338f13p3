cs388f13p1 by Michael Dotson and Marcellin fom Tchassem

Prerequisites:  
1. Maven build tool installed.  
2. Java JDK 1.6 or higher installed.
3. git version control installed.
4. mysql installed locally. At least version 6.0. Could possibly work on lesser
	versions, but has not been tested.
5. mysql administration account that can create databases and tables. If your
	administration account is not username "root" and password "secret",
	you will need to change the global variable USERNAME and PASSWORD in the
	file com/cs388/f13p2/database/DBHelper.java.

Steps to run project tests:
1. git clone https://github.com/mdotson1/cs338f13p2.git
2. navigate to cs388f13p1/UniversityRegistrationSystem/
3. mvn package
4. java -cp target/com.cs388f13p2-0.0.1-SNAPSHOT.jar com.cs388f13p2.view.Registration

Output will be some tests for the functionality of the application based on the
Project Case Study (SSD_Course__Project_Case_Study.pdf). 

Previous version history can be found at:
https://bitbucket.org/mdotson1/cs338f13p1

Project converted from mercurial to git on 9/30/2013 and now hosted on github at 
https://github.com/mdotson1/cs338f13p2
