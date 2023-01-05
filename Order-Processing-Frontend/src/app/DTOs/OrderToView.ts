export class OrderToView {
    orderId : number;
    title: string;
    quantity: number;

    constructor() {
        this.orderId = -1;
        this.title = "";
        this.quantity = -1;
    }
}
