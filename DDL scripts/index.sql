CREATE INDEX idx_authors_last_name ON Authors (last_name);
CREATE INDEX idx_authors_first_name ON Authors (first_name);
CREATE INDEX idx_books_title ON Books (title);
CREATE INDEX idx_books_publisher ON Books (publisher_id);
CREATE INDEX idx_books_category ON Books (category);
CREATE INDEX idx_publisher_last_name ON publisher (last_name);
CREATE INDEX idx_publisher_first_name ON publisher (first_name);