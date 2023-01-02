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
  faTrashAlt = faTrashAlt;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {}

  public deleteCartItem(): void {
    this.removeCartItemEvent.emit(this.cartItem);
    // this.cartService.deleteCartItem().subscribe({
    //   next: (res) => {
    //     this.removeCartItemEvent.emit(this.cartItem);
    //   }
    // })
  }

}

