# University Registration System by  
# Michael Dotson and Marcellin fom Tchassem  

## app URL:  
http://cs338f13p3.herokuapp.com/

## Project layout:
app  
-- controllers  
---- root - the root page "/". The layout of the website matches the layout of
this package  
------ admin_portal - "/admin_portal"  
-------- Controller for each view under admin_portal  
------ professor_login - "/professor_login"  
-------- Controller for each view under professor_login  
------ student_login - "/student_login  
-------- Controller for each view under student_login  
-- models  
---- course (Course related model files)  
---- database (Database files)  
---- forms (Classes used to validate forms)  
---- person (Person related model files)  
-- views  
---- helpers  
------ forms  
-------- complex (any non entity form)  
-------- entity (any forms based on the model objects)  
------ tables  
-------- tables to display model objects  
------ text  
-------- helpers for displaying html formatting  
---- multiuse_templates  
------ any templates you can use for different model objects  
---- resources  
------ admin  
-------- pages only administrations should use  
------ read_only  
-------- pages others should use  
------ other files that do not have both admin and read_only versions  
---- root - This again follows the layout of the website.  
------ views for the controllers (one view per page/controller)  

### How to create things:
Students:  
http://cs338f13p3.herokuapp.com/admin_portal/students  
Departments:  
http://cs338f13p3.herokuapp.com/admin_portal/departments  
Professors:  
At the url for departments or here:  
http://cs338f13p3.herokuapp.com/admin_portal/departments/{DEPARTMENT}/faculty  
Courses:  
http://cs338f13p3.herokuapp.com/admin_portal/departments/{DEPARTMENT}/courses  
Course Schedules:  
http://cs338f13p3.herokuapp.com/admin_portal/course_schedules  
Course Offerings: 
http://cs338f13p3.herokuapp.com/admin_portal/course_schedules/{SEMESTER}  

### How to register for courses:  
http://cs338f13p3.herokuapp.com/student_login/{ID}/registration/{SEMESTER}  

### How to teach a course:  
http://cs338f13p3.herokuapp.com/professor_login/{ID}/course_schedules/{SEMESTER}/{DEPARTMENT}/{COURSE_NUMBER}/{SECTION_NUMBER}  

### How to get a class roster:  
http://cs338f13p3.herokuapp.com/professor_login/{ID}/course_schedules/{SEMESTER}/{DEPARTMENT}/{COURSE_NUMBER}/{SECTION_NUMBER}/students/  

### How to pay for classes: 
http://cs338f13p3.herokuapp.com/student_login/{ID}/payments  

## Application overview:
The idea for our application was to create a RESTful website. This website
would follow all the principles of REST and be stateless. RESTful websites are
scalable and allow for caching, while single page websites do not. As a result,
our app has about 67 pages which reuse resources, a feature of REST. These pages
can all be cached, and take the loaf off the server. This approach is great for 
a university registration system, because generally students live in the same
area around the university. This means that caching can have a staggering
effect on scalability for anyone who uses the services in the same general area,
because everything is cached. Unfortunately, a RESTful approach takes much more 
time to implement. As a result, we were unable to implement a few requirements.

## Unable to implement:
*   Course prerequisites. A simple addition, but not necessary for
functionality, because the prerequisites were only guidelines (did not prevent
student from registering. With a little more time, this functionality would
have been added.  
*   A course offering with less than 3 students is cancelled. This notation
would require a sense of "time" (At "X" time, students may not longer register,
and any courses with an insufficient amount of students would be dropped. There
would need to be a "time" associated with the app. While possible, it would 
require extensive testing and time, which we did not have.  
*   Dropping courses and other modifications. Our idea was to go for 
functionality first (an agile approach). A next step would be to add mutability
to the courses, professors, and students.  

