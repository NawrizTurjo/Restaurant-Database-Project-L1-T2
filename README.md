<div align="center">
  <img src="src/assets/Restaurant Table.jpg" alt="Restaurant Database System Logo" width="200">
  <h1>CSE 108: Java Term Project</h1>
  <h2>Restaurant Database System</h2>
</div>

## 🍔 Overview

Welcome to the **Restaurant Database System** project! This server-based application allows restaurant owners and customers to streamline their interactions seamlessly through a Java-based networked system. In Part 2, we've introduced an enhanced JavaFX GUI-based interface and networking capabilities for an even more magical experience.

## Screenshots

![Restaurant Page](screenshots/12.png)
![Restaurant Page](screenshots/13.png)
*Screenshot of the restaurant owner's dashboard.*

![Restaurant Page](screenshots/22.png)
*Unique logo for each restaurant in database.*

![Customer Page](screenshots/1.png)
![Customer Page](screenshots/2.png)
*Screenshot of customer's login page*

![Customer Page](screenshots/3.png)
![Customer Page](screenshots/4.png)
![Customer Page](screenshots/8.png)
*Screenshot of the customer's interface for searching food.*

![Customer Page](screenshots/5.png)
*Confirmation for safe logout*

![Customer Page](screenshots/10.png)
![Customer Page](screenshots/11.png)
*Screenshot of the customer's interface for ordering food.*

![Customer Page](screenshots/14.png)
![Customer Page](screenshots/15.png)
*place of order*

![Customer Page](screenshots/16.png)
![Customer Page](screenshots/17.png)
*order confirmation*

![Customer Page](screenshots/21.png)
![Customer Page](screenshots/22.png)
*place order at multiple restaurant simuntaneously*

![Customer Page](screenshots/24.png)
*multiple user mode*

![Customer Page](screenshots/7.png)
*CSS Styling*

## Features

- **Two User Roles:** 
  - **Restaurant:** Log in to receive and confirm orders from customers.
  - **Customer:** Log in by name, search for food, filter results, and place orders.

- **Real-Time Updates:** Receive and process orders in real-time without the need for page reloads, thanks to Java networking.

- **Intuitive User Interface:** A clean and visually appealing UI with improved button hovering and page coloring using CSS.

- **Dynamic Restaurant Logos:** Logos on each restaurant's page update dynamically as new restaurants log in, provided corresponding images exist.

## 🚀 Getting Started

To run this enchanting program:

**Option 1: Command Line (Part 1)**
1. Ensure you have the `Java Development Kit (JDK)` installed.
2. Compile the program:
   ```
   javac RestaurantApp.java
   ```
6. Start the magic:
   ```
   java RestaurantApp
   ```

**Option 2: JavaFX GUI & Networking (Part 2)**
1. Make sure you have `JavaFX` installed or included in your project.
2. Update launch.json: Open the project in `vscode` and update the `launch.json` by updating the `vmArgs` location to your `javafx-lib` folder
3. Compile the program: `run the server first, then both clients (It is desirable to login as all restaurants to enjoy full features)`
4. Start the enchantment: `enjoy`

## 🌮 Main Menu Magic

Experience the wonder of the **Main Menu**:

Main Menu (Part 1):

1. Search Restaurants
2. Search Food Items
3. Add Restaurant
4. Add Food Item to the Menu
5. Exit System

Main Menu (Part 2):

1. Search Restaurants
2. Search Food Items
3. Network Mode
4. Exit System

## 🌐 Network Mode (Part 2)

Embark on a new journey with the Network Mode:

- **Restaurant Mode:** Log in as a restaurant owner, receive orders from customers, and confirm them in real-time.
- **Customer Mode:** Log in by name, search for food, filter results, place orders, and receive real-time updates on order status, find out how much you have spent.

## 📦 Input Files

Load the magic of your data from `restaurant.txt` and `menu.txt`. Both files use a comma-separated format; also restaurant passwords are safe within `restaurantPass.txt` file.

## 🎩 Assumptions and Enchantments

Embrace these enchantments during your magical journey:

- No hardcoding allowed.
- Design code for reusability.
- All restaurant names are unique.
- Food items are unique on restaurant menus.
- No limits on restaurants or food items.
- Searches are case-insensitive.
- Data file formats are correct; no validation needed.
- Command-line interface (Part 1) and JavaFX GUI with networking (Part 2).

## 💸 Price Symbols 

The mystical price symbols indicate cost per person for an average meal.

## 🌟 Now, Begin Your Enhanced Journey

Explore the enchanted world of restaurants and food items with the enhanced **Restaurant Database System**. Let the magic unfold with a beautiful GUI and real-time networking features!

## 🌐 Network Configuration (Running Client from Multiple PCs)

To run the client from two different PCs and connect to the server, follow these steps:

**Server PC Configuration:**
1. Unblock Firewall:
   - Ensure that the server PC's firewall allows incoming connections on the designated port (e.g., port 12345). You may need administrator privileges to make these changes.

2. Obtain the Server PC's IP Address:
   - Open a command prompt on the server PC.
   - Enter the following command to display the server PC's IP address:
     ```
     ipconfig
     ```
   - Look for the IPv4 Address (e.g., 192.168.1.100). Note down this address.

3. Start the Server Application:
   - Run the server application on the server PC. Make sure it's listening for incoming connections.

**Client PC Configuration:**
1. Install Java and Javafx:
   - Ensure that you have `Java` along with `Javafx` installed on your client PC.

2. Update Configuration:
   - In the client application code or configuration, specify the server PC's `IP address` obtained earlier. You may change the server address in `connectToServer` method for client application.

3. Run the Client Application:
   - Start the `client` application on your client PC.
   - You should now be able to connect to the server PC using its IP address.

**Connecting from Multiple PCs:**
- You can repeat the Client PC Configuration steps on as many PCs as needed to connect to the server simultaneously.

Please note that network configurations may vary based on your specific setup and operating system. Ensure that your server PC and client PCs are on the same local network for this setup to work. If you encounter connection issues, consider checking your firewall settings and network configuration.


<div align="center">
  <h1> Happy Dining! </h1>
</div>
