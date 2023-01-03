export class CartItem {
  isbn: number;
  title: string;
  price: string;
  publisherName: string;
  quantity: number;

  constructor() {
    this.isbn = 0;
    this.title = '';
    this.price = '';
    this.publisherName = '';
    this.quantity = 1;
  }
}
