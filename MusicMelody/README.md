Developing Online Music Store (Spring Boot E-Commerce Cart Web App)

Background of the problem statement:
An online product catalog that can be browsed: The work starts with adding many new product catalog features which includes displaying categories, products, and product details.
Searching the Catalog: For the visual part, a text box is used in which the visitor can enter one or more words to search through the product catalog. In Music CD Shop, the words entered by the visitor are searched for in the products’ names and descriptions. Also, the user can search for a particular song by entering the title, artist, style, format and the price range.

A Custom Shopping Cart and checkout: A custom shopping basket is implemented, which stores its data into the local database. Also a “shopping cart summary control” is created that shows up in every catalog page except the shopping cart page.

Handling Customer Accounts: In customer account system, details such as credit card numbers are stored in a database so that customers don’t have to retype this information each time they place an order. Customers can log in via a login page or dialog box to get access to secured areas of the web site. Once logged in, the Web Application remembers the customer until the customer logs out (either manually via a Log Out button or automatically, if the session times out or a server error occurs). All secure pages in a Web # Application need to check whether a customer is logged in before allowing access.

Catalog Administration: This administrative interface is implemented for easy management of the web store data. The catalog administration page allows the administrator to:

-	Add or remove genres, and update the details of existing genres
-	View and manage the categories that belong to a genre
-	Manage the list of products in a specific category, and edit product details
-	Assign an existing product to an additional, or move it to another category
-	Remove a product from a category or delete the product from the catalog
-	Manage orders by updating their status

## Configuration: 
-	application.properties is the main configuration file, used to connect to database MusicMelody. Also contains SSL information so that info is not transmitted in plain text. 

## Running the app:
Either go to musicmelody.us or clone repository, and build project and run as a java applications from main
Type in: https://localhost:8443/ in web browser. Takes to home page.  
From there you can either watch and/or listen to any of the 3 embedded music files or navigate to the login/register page in the top right hand corner. 

![](images/HomePage.jpg)
 
Once on login/sign up page, choose either sign up, if new user, or login with past created credentials. Clicking on either one of them will shift the tab to the appropriate form. 
Once the user has logged on, you are taken to user/home page and from there you can browse the catalog and add selected songs to cart. Songs can be viewed by Title, Artist Name, Duration, and Cost. The ability to search is also included. 

![](images/login.jpg)
  
One can go to the profile page and edit billing information, see their songs, go to their cart and purchase songs that are selected. 

![](images/TopNav.jpg)
 
User is able to edit their profile information and edit their billing information (Name, card #, address, etc)

![](images/cart.jpg)

![](images/profile.jpg)
 
On the cart page there is a table that will display all of the added songs.
There is also a logout link at the top. Pressing that link will end the current session. 
