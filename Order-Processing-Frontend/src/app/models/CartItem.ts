export class CartItem {
    name: string;
    price: string;
    publisher: string;
    count: number;

    constructor() {
        this.name = '';
        this.price = '';
        this.publisher = '';
        this.count = 1;
    }
}