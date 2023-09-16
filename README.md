<div align="center">
  <img src="restaurant-logo.jpg" alt="Restaurant Database System Logo" width="200">
  <h1>CSE 108: Java Term Project</h1>
  <h2>Restaurant Database System</h2>
</div>

## 🍔 Overview

Welcome to the **Restaurant Database System** project! This server-based application allows restaurant owners and customers to streamline their interactions seamlessly through a Java-based networked system. In Part 2, we've introduced an enhanced JavaFX GUI-based interface and networking capabilities for an even more magical experience.

## Screenshots

<!-- Add the following HTML and JavaScript code to automatically list images from the 'screenshots' folder -->

<div>
  <p>Click on the images to view larger versions:</p>
  <ul id="screenshot-list">
    <!-- The script below will populate this list -->
  </ul>
</div>

<script>
  // JavaScript code to generate a list of images from the 'screenshots' folder
  const screenshotList = document.getElementById('screenshot-list');

  // Replace 'screenshots/' with the actual path to your folder
  const screenshotPath = 'screenshots/';

  // List of allowed image file extensions
  const imageExtensions = ['.png', '.jpg', '.jpeg', '.gif'];

  // Function to check if a file has an allowed image extension
  function isImageFile(filename) {
    const ext = filename.slice(((filename.lastIndexOf(".") - 1) >>> 0) + 2);
    return imageExtensions.includes('.' + ext.toLowerCase());
  }

  // Function to generate the HTML for each image
  function createImageElement(filename) {
    return `<li><a href="${screenshotPath}${filename}" target="_blank"><img src="${screenshotPath}${filename}" alt="${filename}" width="200"></a></li>`;
  }

  // Fetch the list of files in the 'screenshots' folder and generate the list
  fetch(screenshotPath)
    .then((response) => response.text())
    .then((text) => {
      const parser = new DOMParser();
      const htmlDoc = parser.parseFromString(text, 'text/html');
      const files = Array.from(htmlDoc.links).map((link) => link.href);
      const imageFiles = files.filter(isImageFile);
      const imageListHTML = imageFiles.map(createImageElement).join('');
      screenshotList.innerHTML = imageListHTML;
    })
    .catch((error) => {
      console.error('Error fetching screenshot files:', error);
    });
</script>


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
1. Ensure you have the Java Development Kit (JDK) installed.
2. Compile the program: `javac RestaurantApp.java`
3. Start the magic: `java RestaurantApp`

**Option 2: JavaFX GUI & Networking (Part 2)**
1. Make sure you have JavaFX installed or included in your project.
2. Compile the program: `javac RestaurantApp.java`
3. Start the enchantment: `java RestaurantApp`

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
3. Add Restaurant
4. Add Food Item to the Menu
5. Network Mode
6. Exit System

## 🌐 Network Mode (Part 2)

Embark on a new journey with the Network Mode:

- **Restaurant Mode:** Log in as a restaurant owner, receive orders from customers, and confirm them in real-time.
- **Customer Mode:** Log in by name, search for food, filter results, place orders, and receive real-time updates on order status.

## 📦 Input Files

Load the magic of your data from `restaurant.txt` and `menu.txt`. Both files use a comma-separated format; Also restaurant passwords are safe within `restaurantPass.txt` file.

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

## Screenshots

![Restaurant Page](screenshots/restaurant_page.png)
*Screenshot of the restaurant owner's dashboard.*

![Customer Page](screenshots/customer_page.png)
*Screenshot of the customer's interface for ordering food.*
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
1. Install Java:
   - Ensure that you have Java installed on your client PC.

2. Update Configuration:
   - In the client application code or configuration, specify the server PC's IP address obtained earlier. You may need to modify a configuration file or provide the IP address as a command-line argument.

3. Run the Client Application:
   - Start the client application on your client PC.
   - You should now be able to connect to the server PC using its IP address.

**Connecting from Multiple PCs:**
- You can repeat the Client PC Configuration steps on as many PCs as needed to connect to the server simultaneously.

Please note that network configurations may vary based on your specific setup and operating system. Ensure that your server PC and client PCs are on the same local network for this setup to work. If you encounter connection issues, consider checking your firewall settings and network configuration.


Happy Dining!
