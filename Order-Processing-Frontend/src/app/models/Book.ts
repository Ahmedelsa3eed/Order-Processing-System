import { Author } from "./Author";
import { Publisher } from "./Publisher";

export class Book {
    isbn: number;
    title: string;
    publisher_id: number;
    publication_year: number;
    price: number;
    category: string;
    quantity: number;
    threshold: number;
    publisher: Publisher;
    authors: Author[];

    constructor() {
        this.isbn = -1;
        this.title = '';
        this.publisher_id = -1;
        this.publication_year = -1;
        this.price = -1;
        this.category = '';
        this.quantity = -1;
        this.threshold = -1;
        this.publisher = new Publisher();
        this.authors = [];
    }
}
