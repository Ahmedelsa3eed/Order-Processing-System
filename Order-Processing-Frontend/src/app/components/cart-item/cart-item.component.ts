import { CartService } from './../../services/cart.service';
import { CartItem } from './../../models/CartItem';
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css'],
})
export class CartItemComponent implements OnInit {
  @Input() cartItem: CartItem = new CartItem();
  @Output() removeCartItemEvent = new EventEmitter();
  @Output() updateCartItemEvent = new EventEmitter();
  faTrashAlt = faTrashAlt;
  isLoading: boolean = false;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {}

  public deleteCartItem(): void {
    this.isLoading = true;
    this.cartService.deleteCartItem(this.cartItem.isbn).subscribe({
      next: (res) => {
        if (res == true) {
          this.isLoading = false;
          this.removeCartItemEvent.emit(this.cartItem);
        }
      },
      error: (err) => (this.isLoading = false),
    });
  }

  public updateItemQuantity(): void {
    this.cartService
      .updateCartItemQuantity(this.cartItem.isbn, this.cartItem.quantity)
      .subscribe({
        next: (res) => {
          if (res == true) {
            this.updateCartItemEvent.emit(this.cartItem);
            console.info('item quantity updated');
          }
        },
      });
  }
}
