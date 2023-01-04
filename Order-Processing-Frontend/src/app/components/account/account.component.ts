import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from 'src/app/models/User';
import { AccountService } from 'src/app/services/account.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  @Input() user = new User();
  @Output() onDelete: EventEmitter<any> = new EventEmitter();
  public isRemoving: boolean = false;
  public isChangingRole: boolean = false;

  constructor(
    private accountService: AccountService,
    public signInOutService: SignInOutService
  ) {}

  ngOnInit(): void {}

  removeUser() {
    this.isRemoving = true;
    this.accountService
      .deleteAccount(this.user.user_id).subscribe({
        next: (res: any) => {
          if (res.body == true)
            this.onDelete.emit(this.user.user_id);
          this.isRemoving = false;
        },
        error: (err: any) => {
          this.isRemoving = false;
          console.error(err);
        }
      });
  }

  changeRole() {
    this.isChangingRole = true;
    this.accountService
      .changeRole(this.user.user_id, this.user.type)
      .subscribe({
        next: (res) => {
          if (res.body == true)
            console.info("role changed")
          this.isChangingRole = false;
        },
        error: (err) => {
          this.isChangingRole = false;
          console.error(err);
        }
      });
  }
}
