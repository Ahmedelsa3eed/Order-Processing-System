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
    loggedInUserFirstName = this.signInOutService.getSignedInUserFirstName();
    loggedInUserLastName = this.signInOutService.getSignedInUserLastName();
    public isLoading: boolean = false;
    signedInUserType: string = this.signInOutService.getSignedInUserType();

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
        this.cookieService.deleteAll();
        this.router.navigate(['sign-in']);
        this.signInOutService.signOut().subscribe(
            () => {},
        );
    }
}
