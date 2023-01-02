import { CartService } from './../../services/cart.service';
import { CartItem } from './../../models/CartItem';
import { Component, OnInit } from '@angular/core';
import { faLongArrowAltLeft, faLongArrowAltRight } from '@fortawesome/free-solid-svg-icons';
import { faCcMastercard, faCcVisa, faCcAmex, faCcPaypal } from '@fortawesome/free-brands-svg-icons';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cartItems: CartItem[] = [];
  cartSize: number = 0;
  faLongArrowAltLeft = faLongArrowAltLeft;
  faLongArrowAltRight = faLongArrowAltRight;
  faCcMastercard = faCcMastercard;
  faCcVisa = faCcVisa;
  faCcAmex = faCcAmex;
  faCcPaypal = faCcPaypal;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.getCartItems();
  }

  private getCartItems(): void {
    this.cartService.getCartItems().subscribe({
      next: (res) => {
        this.cartItems = res;
        this.updateCartSize();
      },
      error: (err) => console.error(err),
    });
  }

  private updateCartSize(): void {
    this.cartSize = 0;
    this.cartItems.forEach((item) => (this.cartSize += item.count));
  }

  public deleteCartItem($cartItem: any): void {
    this.cartItems = this.cartItems.filter(item => item !== $cartItem);
    this.updateCartSize();
  }
  
}
