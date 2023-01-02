DELIMITER $$

# this trigger is to update the book quantity in the DB if the new quantity will be -ve
CREATE TRIGGER update_books_quantity_trigger
BEFORE UPDATE ON Books
FOR EACH ROW
BEGIN
  IF NEW.quantity < 0 THEN
    SET NEW.quantity = OLD.quantity;
  END IF;
END$$

# this triger is to update the book quantity in the book table if the quantity will be less than threshold
CREATE TRIGGER place_orders_trigger
AFTER UPDATE ON Books
FOR EACH ROW
BEGIN
  IF NEW.quantity < NEW.threshold THEN
    INSERT INTO Orders (ISBN, quantity)
    VALUES (NEW.ISBN, NEW.threshold - NEW.quantity);
  END IF;
END$$

CREATE TRIGGER confirm_orders_trigger
BEFORE DELETE ON Orders
FOR EACH ROW
BEGIN
  UPDATE Books
  SET quantity = quantity + OLD.quantity
  WHERE ISBN = OLD.ISBN;
END$$

CREATE TRIGGER add_to_shopping_cart_trigger
AFTER INSERT ON cart
FOR EACH ROW
BEGIN
  UPDATE Books
  SET quantity = quantity - NEW.quantity
  WHERE ISBN = NEW.ISBN;
END$$

CREATE TRIGGER remove_from_shopping_cart_trigger
AFTER DELETE ON cart
FOR EACH ROW
BEGIN
  UPDATE Books
  SET quantity = quantity + OLD.quantity
  WHERE ISBN = OLD.ISBN;
END$$
