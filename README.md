# Simple Stock Management System

## Project Overview

The Simple Stock Management System is a Java application that allows users to manage product stock, generate invoices, and has two distinct login systems: one for administrators and one for cashiers.

## Authors

- [@isuruW](https://github.com/isuruwebdev)


## Badges

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)


## Setup and Installation

1. Clone this repository to your local machine.
2. Configure your MySQL database and update the connection settings in the application.
3. Build and run the application.

## Database Setup

1. Create a MySQL database named `apple-stock`. You can use a MySQL client or command line to create the database.

    ```sql
    CREATE DATABASE apple-stock;
    ```

2. Import the provided SQL sheet into the `apple-stock` database. You can use a MySQL client or command line to do this.

    ```bash
    mysql -u your_username -p apple-stock < path/to/sql-sheet.sql
    ```

    Replace `your_username` with your MySQL username and provide the path to the `sql-sheet.sql` file.

#### Admin Login

- Username: admin
- Password: admin

#### Cashier Login

- Username: cashier
- Password: cashier
## Features

#### Admin Login
- View the list of products.
- Filter and search for products.
- Update the admin's profile.
- Create, Read, Update, and Delete (CRUD) cashiers.
- CRUD operations for products.

#### Cashier Login
- View the list of products.
- Filter and search for products.
- Add products to a cart.
- Remove products from the cart.
- Generate and view invoices.
## Usage

### Admin Login
1. Use your admin credentials to log in.
2. Access the admin dashboard to manage products and cashiers.

### Cashier Login
1. Use your cashier credentials to log in.
2. Access the cashier dashboard to add products to the cart, remove items, and generate invoices.

## Tech Stack

**Client:** Java Swing / Maven

**Server:** phpMyAdmin / MySQL
## Color Reference

 | Hex                                                                |
 | ------------------------------------------------------------------ |
![#F5EEC8](https://via.placeholder.com/10/F5EEC8?text=+) #F5EEC8
![#232D3F](https://via.placeholder.com/10/232D3F?text=+) #232D3F
![#FFFFFF](https://via.placeholder.com/10/FFFFFF?text=+) #FFFFFF
![#040D12](https://via.placeholder.com/10/040D12?text=+) #040D12 
![#03C988](https://via.placeholder.com/10/03C988?text=+) #03C988
![#D80032](https://via.placeholder.com/10/D80032?text=+) #D80032
![#610C9F](https://via.placeholder.com/10/610C9F?text=+) #610C9F 
![#176B87](https://via.placeholder.com/10/176B87?text=+) #176B87 


## Feedback

If you have any feedback, please reach out to us at isuruwebdev@gmail.com

