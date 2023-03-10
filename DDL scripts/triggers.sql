DELIMITER $$

# this trigger is to update the book quantity in the DB if the new quantity will be -ve
CREATE TRIGGER update_books_quantity_trigger
BEFORE UPDATE ON Books
FOR EACH ROW
BEGIN
  IF NEW.quantity < 0 THEN
    SIGNAL SQLSTATE '50001' SET MESSAGE_TEXT = 'Insufficient books';
  END IF;
END$$

CREATE TRIGGER negative_threshold
BEFORE UPDATE ON Books
FOR EACH ROW
BEGIN
	IF NEW.threshold < 0 THEN
		SIGNAL SQLSTATE '50002' SET MESSAGE_TEXT = 'Book can not have negative threshold';
	END IF;
END$$

CREATE TRIGGER remove_item_from_cart
BEFORE DELETE ON cart
FOR EACH ROW
BEGIN
    IF OLD.confirmed = 1 THEN
		UPDATE books SET books.quantity = books.quantity - OLD.quantity WHERE books.ISBN = OLD.ISBN;
    END IF;
END;

# this triger is to update the book quantity in the book table if the quantity will be less than threshold
CREATE TRIGGER place_orders_trigger
AFTER UPDATE ON Books
FOR EACH ROW
BEGIN
  IF NEW.quantity < NEW.threshold THEN
    INSERT INTO Orders (ISBN, quantity)
    VALUES (NEW.ISBN, 2 * NEW.threshold);
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