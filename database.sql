CREATE DATABASE desenvolvimento_web;
USE desenvolvimento_web;

CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT,
  name VARCHAR(500) NOT NULL,
  email VARCHAR(500) NOT NULL UNIQUE,
  pass VARCHAR(500),
  role VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products (
  id INT AUTO_INCREMENT,
  name TEXT NOT NULL,
  description TEXT NOT NULL,
  price DOUBLE NOT NULL,
  img_url TEXT,
  brand VARCHAR(500) NOT NULL,
  category VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ratings (
  id INT AUTO_INCREMENT,
  user_id INT NOT NULL,
  product_id INT NOT NULL,
  rating INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS stock (
  id INT AUTO_INCREMENT,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS sells (
  id INT AUTO_INCREMENT,
  user_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  creation_date DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS favorites (
  id INT AUTO_INCREMENT,
  user_id INT NOT NULL,
  product_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);
