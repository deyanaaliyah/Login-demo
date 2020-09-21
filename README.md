## Login demo
A demo of a MVC login-system written in Java with a MySQL database connection.

**MySQL:**
primary key is an ID which is auto incremental. Each user in the database has the following:
* ID
* Name
* Email
* Password

**Java:**
For easier management, the classes are stored in packages/folders. In the folders, you'll find an interface, a controller and two repository classes (one class reads from an arraylist, the other from the database). The server port will be default (8080) – but can be changed via the application.properties-file where you'll have to enter your password to your database in order to work properly.

**Web:**
Everything is pure CSS and HTML
