CREATE INDEX idx_authors_last_name ON Authors (last_name);
CREATE INDEX idx_authors_first_name ON Authors (first_name);
CREATE INDEX idx_books_title ON Books (title);
CREATE INDEX idx_books_publisher ON Books (publisher_id);
CREATE INDEX idx_books_category ON Books (category);
CREATE INDEX idx_publisher_last_name ON publishers (name);


#insert Into orders values (0, "mahmoud", "mahmoud", "gad", "kkk", 01234, "mega", "1234", 'manager');
#insert Into publishers values (2, "mega", "dd", 1213);
insert into books value(1, "what", 1, 123, 213,'Art', 23, 3);
