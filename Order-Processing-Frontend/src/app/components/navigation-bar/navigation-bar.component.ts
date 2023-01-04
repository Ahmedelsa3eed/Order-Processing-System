import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignInOutService } from '../../services/sign-in-out.service';
import { CookieService } from 'ngx-cookie-service';
@Component({
    selector: 'app-navigation-bar',
    templateUrl: './navigation-bar.component.html',
    styleUrls: ['./navigation-bar.component.css'],
})
export class NavigationBarComponent implements OnInit {
    public loggedInUserFirstName!: string;
    public loggedInUserLastName!: string;
    public isLoading: boolean = false;
    public signedInUserType!: string;

    constructor(
        private router: Router,
        private signInOutService: SignInOutService,
        private cookieService: CookieService
    ) {}
    
    ngOnInit(): void { 
        this.loggedInUserFirstName = this.signInOutService.getSignedInUserFirstName();
        this.loggedInUserLastName = this.signInOutService.getSignedInUserLastName();
        this.signedInUserType = this.signInOutService.getSignedInUserType();
        console.log(this.signedInUserType);
    }

    navigateTo(child: string) {
        this.router.navigateByUrl('home/' + child);
    }

    logout() {
        this.signInOutService.signOut().subscribe(
            () => {this.router.navigate(['sign-in']);},
        );
    }
}
