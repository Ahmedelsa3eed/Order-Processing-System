import { CheckoutService } from './../../services/checkout.service';
import { Component, Input, OnInit } from '@angular/core';
import { faLongArrowAltRight } from '@fortawesome/free-solid-svg-icons';
import {
  faCcMastercard,
  faCcVisa,
  faCcAmex,
  faCcPaypal,
} from '@fortawesome/free-brands-svg-icons';
import { CheckoutData } from 'src/app/models/CheckoutData';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
})
export class CheckoutComponent implements OnInit {
  checkoutData: CheckoutData = new CheckoutData();
  @Input() totalPrice: number = 0;
  isLoading: boolean = false
  // fart awsome icons
  faLongArrowAltRight = faLongArrowAltRight;
  faCcMastercard = faCcMastercard;
  faCcVisa = faCcVisa;
  faCcAmex = faCcAmex;
  faCcPaypal = faCcPaypal;

  constructor(private checkoutService: CheckoutService) {}

  ngOnInit(): void {}

  public checkoutOrder(): void {
    this.checkoutService.checkout(this.checkoutData).subscribe({
      next: (res) => {
        window.alert(res);
      }
    });
  }
}
