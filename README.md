## Spring MVC Film C.R.U.D. Project

### Summary
This program allows a user to look up movies from a database. It was created after week 7 of the bootcamp. This project included much of the work from the previous project but incorporated web funtionality
This program allows a user to look up movies from a database. This program was created after week six of bootcamp. This project was all about the database and organizing the program in a way so that everything talked to each and most importantly to the database. After getting over the initial hump of setting up one of the Database Accessor Objects (dao) the project was pretty straight forward to get the other Database Accessor Objects set up and get the rest of the code up and going.

### Overview
The program runs out of many different classes with each one doing a specific function. You are started with a menu to select if you would like to look up a film by keyword or if you would like to look up a film by keyword. When looking up a movie by keyword it will search for the keyword in both the title and the description of the film.

#### Package/Classes Overview
- Package ...filmquery.app
    - FilmQueryApp.java
    - FilmQueryLookUp.java
    - FilmQueryMenus.java
    - FilmQueryOperation.java
    - FilmQueryPrint.java
- Package ...filmquery.database
    - DatabaseAccessor.java
    - DatabaseAccessorObject.java
- Package ...filmquery.entities
    - Actor.java
    - Film.java

#### Classes Breakdown

FilmQueryApp:
- this class contains the main method to start the program

FilmQueryLookUp:
- this class attempt to call the proper database accessor object

FilmQueryMenus:
- this class holds the menus that are needing in the program

FilmQueryOperation:
- this class holds most of the coding for the program which calls the other methods in its class or other classes depending on the need

FilmQueryPrint:
- this class is called to print whatever is needed

DatabaseAccessor:
- this is an interface for the methods that are required (contract) in the DatabaseAccessorObject class

DatabaseAccessorObject:
- this class connects to the database and retrieves the information that was requested form the database

Actor:
- this class is to make an Actor object

Film:
- this class is to make a Film object

#### Operation/Description of how the program works:

Program starts by welcoming the user to the Film Database
- this is done by creating an instance of itself and calling the launch method
- inside the launch method it creates an instance and calls the FilmQueryOperation class and the startOperation method of that class
- The startOperation method creates and closes a scanner that will be used through out the program
- this method calls the startUserInterface method and pass the scanner through

The user sees "Welcome to the Film Database" and then the menu is displayed for them to choose how they would like to look up a movie

- If the user selects option 1 the user will be asked for an id of a movie the would like to look up  
If the user selects option 2 the user will be asked for a keyword of a movie they would like to look up  
If the user selects option 0 the program will quit 
- A loop keeps printing the menu (by calling the class for menus) for the user until the select a correct option
- Menu selection and scanner are passed to the menu options method to determine what to do with the users selection
- Here is what the menu looks like
    1. Look up a film by its id
    2. Look up a film by a search keyword
    0. Exit

Once an option is selected it sends it to a switch that determines that to do with it the users selection
- in this method we only create a new DatabaseAccessorObject and FilmQueryLookUp if the user selects to look up a film

When a user selects to look up a film by Id or keyword they are prompted to enter the id or keyword of a film
- Messages are printed to console if the user did not enter the correct information
- If the user did not enter the correct information it will loop to have them try again
- The class and method to look up a film are called and it passes the Database Accessor Object, Scanner, user input, and the reference for the film
- If the user did select a proper film it will print out the basic details by calling the print method in the print class

When a user selects to look up a film by keyword and that keyword is not fount in a film title or description if will prompt the user if they would like to try again
- Messages are printed to console if the user did not enter the correct information
- If the user did not enter the correct information it will loop to have them try again

When a user sees the basic details of a film they are prompted to see if they would like to see all of the details of the film or return to the main menu
- Messages are printed to console if the user did not enter the correct information
- If the user did not enter the correct information it will loop to have them try again

Other details:
- SQL string to be passed to the prepared statement along with the requested ID which returns the query  
If there is a query that is returned it will add each item requested to a new film object  
After that has finished it will close the Prepared Statement and connection and return the film object  
- When creating film/film lists the actors and conditions are called to do new SQLs for those specific objects
- The condition list that is returned is only because I had multiple SQLs that were in that same method

#### Technologies/Techniques Used
- created an instance of the main application to be able to launch the app inside of the main and have that be the only method in there.
- call other methods from classes and pass information to and from them
- declare and create new objects
- print out
- Scanner
- pass Scanner through to other methods and other classes as needed
- do/while loop
- while loop
- switch
- overloaded method
- getters/setters
- toString
- constructors
- Interface
- for loop
- hashCode
- equals
- if statements
- continue
- break
- parseInt
- label
- string builder
- regex
- array list
- iterator
- exceptions
- try/catch
- Mavin
- driver to access database
- Connection to database
- SQL queries
- Prepared Statement
- Result set

#### Lessons Learned
- I learned the process of how to connect to a database and run queries on it and get the results back. This was the biggest are where I was learning new things. Most of the rest of the program was similar to other programs we have done in the past. Just a matter of coding it out again so that it works for the details of this project
- I need to keep in mind on proper encapsulation so that I don't have to refactor later. I had at one point where I had to do some major refactoring of moving a bunch of code from one class to another. This led to having to test and debug multiple things.
- One thing that I was proud of putting in the program was how many dashes to print out a few different times. The string where I wanted to print the dashes under would be variable because of a user input. So if I might only need one dash or up to however long of a string the user put in. I did this by taking the string and calculating how long it was with the variable from the user. Then I would take a loop and have a string builder append a dash for the length of the string
- I need to remember to push up to github more often
- I originally had my print methods all named different based on what they did. But I realized that I would be able to name the same because the mostly did the same thing except for if they were being passed one object to print or a list containing objects to print. So I made the names the same but overloaded them so that java would know which one to pick based on what argument was being passed to it. I also did the same thing inside the print method because I would call the single object inside of a loop that was accessing the list to print each object in the list individually. I was amused because the method that accessed the list was calling the method of the same name but just as a single object. It is the little things in life :)
- I learned that putting comments in while coding helps you keep things straight when you are trying to make sense of everything. It also really helps when trying to make the README because you already have a lot of the work done for you
- Accounting for stupid users takes a lot of code

#### Problems/Issues
- I spent most of my time working on the lab that led up to this project. The lab took me a long time to understand and make sure I was doing everything right. I think I had issues with it because it was the first time that we had worked on getting something like that to work. I also ended up doing something completely wrong and had to go back and start over. I believe this was due in part because I did not understand what one part of the lab wanted us to do
- I don't think I did the app correct and believe I could have not created an instance of the app itself and then call a method inside the main that calls a different class. Seems like a lot of extra to me
- One of the stretch goals was to print out the category of the movie. And in whatever way I looked at the categories I was only able to see one category per movie. But the way the stretch goal was worded made me thing there was more than on category per movie. I was not able to figure this out
- It seemed as though I should have been able to encapsulate more but I was unsure of how to go about it and if I was encapsulating too much already. One method that I would think about encapsulating more is the filmByKeyword because it is over 50 lines of code long. This seems long to me because most of my programs have not had methods there were over 50 lines of code
- I am not sure if the ordering of the methods matter inside of a class. I imagine that it doesn't but I still try to make the orderly so that it is more readable
- Another thing that I am unsure about is when you should do a try/catch over just declaring an exception can be thrown when you just want the program to stop and print the stack trace

#### Things I was unable to add or incorporate
- Test Driven Development with JUnit tests
- More ways to access the database so that the user would have more options of seeing films from
- I wanted to be able to have a store connect to the inventory portion to see how many and what movies are at each store location
- I am not sure this one would be possible but a way to see how many times a movie has been rented
- It would have been nice to allow the user to select what details of a movie they would like to see instead of a basic print out of the film or all of the details of a film