import { Component, OnInit } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { User } from 'src/app/models/User';
import { AccountService } from 'src/app/services/account.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
  selector: 'app-accounts-page',
  templateUrl: './accounts-page.component.html',
  styleUrls: ['./accounts-page.component.css'],
})
export class AccountsPageComponent implements OnInit {
  public isLoading: boolean = false;
  public showAlert: boolean = false;
  public error: string =
    'You are not authorized to use this page only admins can use this page';
  users?: Observable<User[]>;
  users$ = new BehaviorSubject<User[]>([]);
  public searchString: string = '';
  pageNum: number = 0;
  accountsPerPage: number = 4;
  constructor(private accountsService: AccountService) {}

  ngOnInit(): void {
    this.users = this.users$.asObservable();
    this.getAccounts();
  }
  
  private getAccounts() {
    this.isLoading = true;
    this.accountsService
      .getAccountFromTo(0, this.accountsPerPage)
      .subscribe({
        next: (res) => {
          if (res.body) {
            this.isLoading = false;
            this.users$.next(res.body);
          }
        },
        error: (err) => {
          this.isLoading = false;
          this.showAlert = true;
        }
      }
      );
  }
  
  getNextPage() {
    if (this.users$.value.length < this.accountsPerPage){
      return;
    }
    this.pageNum++;
    this.accountsService.getAccountFromTo(this.pageNum * this.accountsPerPage, (this.pageNum + 1) * this.accountsPerPage).subscribe({
      next: (res) => {
        if (res.body){
          this.isLoading = false;
          this.users$.next(res.body);
        }
      },
      error: (err) => {
        this.isLoading = false;
        this.showAlert = true;
      }
    });
  }

  getPreviousPage() {
    if (this.pageNum == 0)
      return;
    this.pageNum--;
    this.accountsService.getAccountFromTo(this.pageNum * this.accountsPerPage, (this.pageNum + 1) * this.accountsPerPage).subscribe({
      next: (res) => {
        if (res.body){
          this.users$.next(res.body);
        }
      },
      error: (err) => {
        this.isLoading = false;
        this.showAlert = true;
      }
    });
  }

  public deleteAccount($userId: number) {
    this.users$.next(
      this.users$.value.filter((user) => user.user_id !== $userId)
    );
  }

  public search() {
    this.isLoading = true;
    this.accountsService
      .searchAccounts(this.searchString)
      .subscribe({
        next: (res) => {
          if (res.body) {
            this.isLoading = false;
            this.users$.next(res.body);
          }
        },
        error: (err) => this.isLoading = false
      });
  }
  
}
