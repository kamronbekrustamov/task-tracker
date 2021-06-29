
## About <a href="https://github.com/kamronbekrustamov/task-tracker"><b>Task Tracker</b></a>

Task Tracker is an application created for online tasks management. It is made using Spring Boot and Vue Frameworks by Kamronbek Rustamov. Frontend design is inspired by the design in <a href="https://github.com/bradtraversy/vue-crash-2021">Brad Traversy's Vue Crash Course</a>

## How to clone and work on your local machine or use it on your host?

- Pre-requirements:
1. Install <a href="https://www.apachefriends.org/index.html">XAMPP</a>
2. Install <a href="https://www.oracle.com/java/technologies/javase-jdk16-downloads.html">JDK 16</a>
3. Install <a href="https://nodejs.org/en/">Node JS</a>

- Steps to run the software on your local machine:
1. Clone this repository to your computer.
2. In XAMPP, run Apache and MySQL, and open PhpMyAdmin
3. In PhpMyAdmin, create a database called `spring`
4. Open PowerShell and navigate to the projcet folder.
5. Run 
```
mvnw spring-boot:run
```
7. Open another Powershell, and navigate to `src/main/frontend` folder
8. Run
```
npm run serve
```
9. Go to `http://localhost:3000` and enjoy

<hr>

## How to use it as a Client

- Go to `http://localhost:3000` and sign in as a User
- You will forwarded to Home Page after succesfull login
- You can create, delete tasks from there

PS: **Double Click** to change the importance of the task.
