import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignInOutService } from '../../services/sign-in-out.service';
import { User } from '../../models/User';
import { CookieService } from 'ngx-cookie-service';
@Component({
    selector: 'app-navigation-bar',
    templateUrl: './navigation-bar.component.html',
    styleUrls: ['./navigation-bar.component.css'],
})
export class NavigationBarComponent implements OnInit {
    loggedInUserRole = this.signInOutService.getSignedInUserType();
    loggedInUserFirstName = this.signInOutService.getSignedInUserFirstName();
    loggedInUserLastName = this.signInOutService.getSignedInUserLastName();
    public isLoading: boolean = false;

    constructor(
        private router: Router,
        private signInOutService: SignInOutService,
        private cookieService: CookieService
    ) {}
    
    ngOnInit(): void { }

    navigateTo(child: string) {
        this.router.navigateByUrl('home/' + child);
    }

    logout() {
        this.signInOutService.signOut().subscribe(
            (response) => {
                this.cookieService.deleteAll();
                this.router.navigateByUrl('sign-in')
            },
            (err) => {
                alert(err);
            }
        );
    }
}
