export class Book {
    ISBN: number;
    title: string;
    publisher_id: number;
    publication_year: number;
    price: number;
    category: string;
    quantity: number;
    threshold: number;

    constructor() {
        this.ISBN = -1;
        this.title = '';
        this.publisher_id = -1;
        this.publication_year = -1;
        this.price = -1;
        this.category = '';
        this.quantity = -1;
        this.threshold = -1;
    }
}
