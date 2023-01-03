export class Order {
    orderId : number;
    ISBN: number;
    quantity: number;

    constructor() {
        this.orderId = -1;
        this.ISBN = -1;
        this.quantity = -1;
    }
}
