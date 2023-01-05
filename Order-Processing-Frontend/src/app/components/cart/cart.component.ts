import { CartService } from './../../services/cart.service';
import { CartItem } from './../../models/CartItem';
import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cartItems!: Observable<CartItem[]>;
  cartItems$ = new BehaviorSubject<CartItem[]>([]);
  cartSize: number = 0;
  totalPrice: number = 0;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.cartItems = this.cartItems$.asObservable();
    this.getCartItems();
  }

  private getCartItems(): void {
    this.cartService.getCartItems().subscribe({
      next: (res) => {
        if (res.body && res.ok) {
          this.cartItems$.next(res.body);
          this.updateCartSize();
          this.calculateTotalPrice();
        }
      },
      error: (err) => console.error(err),
    });
  }

  private updateCartSize(): void {
    this.cartSize = 0;
    this.cartItems$.value.forEach((item) => (this.cartSize += item.quantity));
  }

  private calculateTotalPrice(): void {
    this.totalPrice = 0;
    this.cartItems$.value.forEach((item) => (this.totalPrice += item.quantity * item.price));
  }

  public deleteCartItem($cartItem: any): void {
    this.cartItems$.next(this.cartItems$.value.filter((item) => item !== $cartItem));
    this.updateCartSize();
    this.calculateTotalPrice();
  }

  public updateCartItem($cartItem: any): void {
    console.log($cartItem);
    console.log(this.cartItems$.value);
    this.updateCartSize();
    this.calculateTotalPrice();
  }
}
