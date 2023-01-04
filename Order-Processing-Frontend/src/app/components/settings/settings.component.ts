import { Component, OnInit } from '@angular/core';
import {ChangeNameRequest} from "../../models/settings/ChangeNameRequest";
import {ChangePasswordRequest} from "../../models/settings/ChangePasswordRequest";
import {CookieService} from "ngx-cookie-service";
import {SettingsService} from "../../services/settings.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  constructor(private settingsService: SettingsService, private cookieService: CookieService) {}



  ngOnInit(): void {

  }

  changeName() {
    let request = new ChangeNameRequest();
    // @ts-ignore
    request.firstName = document.getElementById('firstName')?.value;
    // @ts-ignore
    request.lastName = document.getElementById('lastName')?.value;
    console.log(request);

    console.log(document.getElementById('firstName')?.getAttribute('value'));

    this.settingsService.changeName(request).subscribe((response) => {
      if (response == "SUCCESSFUL") {
        console.log(response);
        // print success message
        let expirationDate = "0";
        expirationDate = this.cookieService.get("expires");

        this.cookieService.set(
          'first_name',
          request.firstName,
          new Date(expirationDate),
          '/',
          '',
          true,
          'Strict'
        );
        this.cookieService.set('last_name', request.lastName, new Date(expirationDate), '/', '', true, 'Strict');
        window.location.reload();
        alert('Name changed successfully');
      } else {
        // print response message
        alert('Name change failed');
      }
    });
  }

  changeUsername() {
    // @ts-ignore
    let newUsername = document.getElementById('username')?.value;

    if(newUsername != null){
      this.settingsService.changeUsername(newUsername).subscribe((response) => {
        if (response == "SUCCESSFUL") {
          // print success message
          alert('Username changed successfully');
        } else {
          // print response message
          alert('Username change failed');
        }
      });
    }

  }



  changeUserPassword() {
    let request = new ChangePasswordRequest();
    // @ts-ignore
    request.currentPassword = document.getElementById('currentPassword')?.value;
    // @ts-ignore
    request.newPassword = document.getElementById('newPassword')?.value;
    // @ts-ignore
    let confirmPassword = document.getElementById('confirmNewPassword')?.value;
    console.log(request);
    if(request.currentPassword != null && request.newPassword != null && confirmPassword != null
      && confirmPassword == request.newPassword){
      this.settingsService.changePassword(request).subscribe((response) => {
        console.log(response);
        if (response == "SUCCESSFUL") {
          // print success message
          alert('Password changed successfully');
        } else {
          // print response message
          alert('Password change failed');
        }
      });
    } else {
      alert('new password and confirm password do not match');
    }
  }

  changeAddress() {
    // @ts-ignore
    let newAddress = document.getElementById('address')?.value;

    if(newAddress != null){
      this.settingsService.changeAddress(newAddress).subscribe((response) => {
        if (response == "SUCCESSFUL") {
          // print success message
          alert('Address changed successfully');
        } else {
          // print response message
          alert('Address change failed');
        }
      });
    }

  }

  changePhone() {
    // @ts-ignore
    let newPhone = document.getElementById('phone')?.value;

    if(newPhone != null){
      this.settingsService.changePhoneNumber(newPhone).subscribe((response) => {
        if (response == "SUCCESSFUL") {
          // print success message
          alert('Phone changed successfully');
        } else {
          // print response message
          alert('Phone change failed');
        }
      });
    }


  }

  changeEmail() {
    // @ts-ignore
    let newEmail = document.getElementById('email')?.value;

    if(newEmail != null){
      this.settingsService.changeEmail(newEmail).subscribe((response) => {
        if (response == "SUCCESSFUL") {
          // print success message
          alert('Email changed successfully');
        } else {
          // print response message
          alert('Email change failed');
        }
      });
    }

  }
}

