# Biblioteca Online

Welcome to Biblioteca Library Management System. Enroll yourself, and enjoy your favourite books and movies.
This is a web application. Read below more about the Application architecture.

## Getting Started

### Setup code locally
1. Import this folder with Intellij as an Gradle project
2. Select Gradle JVM 1.8

### Run the Application on Console
1. Got to `BibliotecaOnline` folder
2. Type ```./gradlew build``` on console to build and test the application
3. Type ```./gradlew appRun``` on console to run the application
4. Once the application is running. Open ```http://localhost:8080/Biblioteca_Online/login.html```

 Read below to know more about the plugins, tools and Libraries used.

### Enrolling in Biblioteca/ Login
1. Open file ```Biblioteca.java``` in the codebase.
2. Search for ```getUsers``` where there is a list of existing Users.
3. Add a new User in the users list
4. Re-run the application
5. Navigate to ```http://localhost:8080/Biblioteca_Online/login.html```
6. Login with your user credentials.

### Adding books/ Checkout Book

1. Open file ```Biblioteca.java``` in the codebase.
2. Search for ```getBooks```.
3. Add a new Book in the existing lists.
4. Re-run the application
5. Login to the application
6. Navigate to ```http://localhost:8080/Biblioteca_Online/CheckoutBook.html```
7. Enter the name of the book and checkout

## Overview of the application

Here is a quick overview of the application.

### Completed Features
- Checkout Book from the Library: To check out a book, user must be logged into the application.

### In Progress Features
- Homepage:
    - The homepage has the structure and also we have a design for it.
- Login into the application:
    - User can login into app, alert is displayed on successful login.
    - There are no side validations.
    - On successful login user is not navigated to checkout Book Page.
- Other features Return Book, Checkout/Return Movie
    - The functionality exists in Library to perform these operations
    - There is no user interface to trigger these.
    - API for these are exposed to be consumed by the client side.

### Future Scope
-  On successful login, navigate the user to the existing functionality of Checkout Book.
-  Add Client side validations for the Login Page with appropriate tests.
-  Make the UI of home Page, in consistence of the [visual guide](Biblioteca_online_homepage_design.pdf).

## Application Architecture

### Client Server
Biblioteca online is a web based Application. It follows the Client Server architecture.
There is a client side which provides an interface for the user to trigger operations.
Client side prepares a HTTP request for the server. Makes the calls to the server.
Server processes the request, performs the corresponding operations. For example checking out book from the library.
Server prepares a response on completion of the operation, and returns back response to the client side.
Client side receives the response, consumes the response to render the next UI element.
Client side can also maintain user activity.

### Authentication Mechanism
Once a user successful logs in, into the application. User is granted a token.
This token is stored at the client side.
Once the token exists, it is send along in the request to the server.
Server if receives the token, authenticate the user with it
If user is authenticated, proceeds further with performing the operation

### Tech Stack

- Client Side
    - HTML
    - CSS
    - Javascript
- Server side
    - Servlets
    - Java

## Code Structure

- src
 -  jsTest (Javascript Library and Specs)
 -  main
     - java (Server side source - core Biblioteca models and Servlets)
     - webapp (Client side source - HTML, CSS and javascript )
 -  test (Java Server Side tests)

## Getting Started with developing

### Samples

Checkout Book is a good example of how to code for different layers.

Client Side: CheckoutBook.html, checkoutBook.js, checkout-book.css
Server Side: CheckoutBookServlet.java

Also have a look at their tests.

### Running Tests

1. Running Java tests: ```./gradlew clean test```
2. Running Javascript tests: Open file ```SpecRunner.html``` in a browser. Don't open the html file directly, using the Intellij `Open in browser` - select the file and click, you can find it in menu.

### Debugging

- Debugging Java Server Code In IntelliJ - Set Up Remote Debugging
    1. Enable debugging and start the server
    2. ./gradlew appRunDebug
    3. In IntelliJ, click Run -> Edit Configurations.
    4. Create a new configuration + -> Remote -> "Debug Biblioteca". Accept default settings -> Apply.
    5. Run -> Debug "Debug Biblioteca"

- Debugging JS Code in browser:
    1. Open the page in browser
    2. Right click -> Inspect
    3. Navigate to Sources
    4. Locate the JS file to Debug
    5. Add breakpoints to Debug

## Optional Exercise to Try
- Add functionality in the application to Return the book.
- Integrate your Biblioteca from the Pre-course with this structure to practice reusing components.

## Tools/ Libraries/ Plugins
- Gradle (Dependency manager and Build tool)
- Junit (Java unit testing framework)
- Mockito (mocking framework for java)
- Jasmine (Javascript unit testing framework)
- Gretty (to provide a web server and run the app on localhost)

## Why not production ready ?
Biblioteca-base is for learning purposes. This uses a simple mechanism for authenticating the users, to help understand the process. To be production ready, tokens, passwords should be encrypted.
Currently there is no database for Biblioteca. The setup data about existing users and books, is hard corded in the server side.
