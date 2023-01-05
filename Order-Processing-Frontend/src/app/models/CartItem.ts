export class CartItem {
  isbn: number;
  title: string;
  price: number;
  publisherName: string;
  quantity: number;

  constructor() {
    this.isbn = 0;
    this.title = '';
    this.price = 0;
    this.publisherName = '';
    this.quantity = 1;
  }
}
