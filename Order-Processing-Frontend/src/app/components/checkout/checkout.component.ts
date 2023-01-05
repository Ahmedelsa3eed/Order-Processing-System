import { CheckoutData } from './../../models/CeckoutData';
import { Component, Input, OnInit } from '@angular/core';
import { faLongArrowAltRight } from '@fortawesome/free-solid-svg-icons';
import {
  faCcMastercard,
  faCcVisa,
  faCcAmex,
  faCcPaypal,
} from '@fortawesome/free-brands-svg-icons';

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

  constructor() {}

  ngOnInit(): void {}

  public checkoutOrder(): void {

  }
}
