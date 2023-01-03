import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators,
  ValidatorFn,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';


import {RegistrationRequest} from "../../models/RegistrationRequest";
import {RegistrationService} from "../../services/registration.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerForm!: FormGroup;
  postError: boolean = false;
  postErrorMessage: string = '';
  registrationRequest: RegistrationRequest = {} as RegistrationRequest;
  constructor(private fb: FormBuilder, private registrationService: RegistrationService, private router: Router) {}


  ngOnInit(): void {
    function ConfirmedValidator(controlName: string, matchingControlName: string): ValidatorFn {
      return (control: AbstractControl): ValidationErrors | null => {
        const FormGroup = control as FormGroup;
        const controlValue = FormGroup.get(controlName)?.value;
        const matchingControlValue = FormGroup.get(matchingControlName)?.value;

        if (control.get('password')?.value === control.get('rPassword')?.value) {
          return null;
        } else {
          return { ValuesNotMatch: true };
        }
      };
    }

    this.registerForm = this.fb.group(
      {
        firstName: this.fb.control(null, [
          Validators.required,
          Validators.maxLength(16),
          Validators.pattern('[a-zA-Z]*'),
        ]),
        lastName: this.fb.control(null, [
          Validators.required,
          Validators.maxLength(16),
          Validators.pattern('[a-zA-Z]*'),
        ]),
        email: this.fb.control(null, [Validators.required]),
        password: this.fb.control(null, [
          Validators.required,
          Validators.maxLength(16),
          Validators.minLength(8),
        ]),
        rPassword: this.fb.control(null, [Validators.required, ConfirmedValidator('password', 'rPassword')]),
        userName: this.fb.control(null, [
          Validators.required,
          Validators.maxLength(16),
        ]),
        address: this.fb.control(null, null),
        phoneNumber: this.fb.control(null, [
          Validators.minLength(11),
          Validators.maxLength(11),
          Validators.pattern('[0-9]*'),
        ]),
      },{

        validators: ConfirmedValidator('password', 'rPassword'),
      }
    );
  }

  // on destroy of component
  ngOnDestroy() {
    this.registerForm.reset();
  }

  // Method to register a new user
  registerSubmitted() {
    this.registrationRequest.firstName = this.registerForm.get('firstName')?.value;
    this.registrationRequest.lastName = this.registerForm.get('lastName')?.value;
    this.registrationRequest.email = this.registerForm.get('email')?.value;
    this.registrationRequest.password = this.registerForm.get('password')?.value;
    this.registrationRequest.userName = this.registerForm.get('userName')?.value;
    this.registrationRequest.phoneNumber = this.registerForm.get('phoneNumber')?.value;
    this.registrationRequest.address = this.registerForm.get('address')?.value;

    this.registrationService.postSignUpData(this.registrationRequest).subscribe({
      next: (res) => {
        // If user already exists or registered successfully, he will be directed to signIn

        this.router.navigate(['sign-in'], {
          queryParams: { email: this.registrationRequest.email },
        });
      },
      error: (e) => this.httpError(e),
      complete: () => console.info('Registration Done!'),
    });
  }

  // method to print the error message from the backend
  httpError(httpError: any) {
    console.error(httpError);
    this.postError = true;
    this.postErrorMessage = httpError.error;
    console.log(this.postErrorMessage);
  }

  // getters for the form controls for every field to get the error messages
  get FirstName(): FormControl {
    return this.registerForm.get('firstName') as FormControl;
  }
  get LastName(): FormControl {
    return this.registerForm.get('lastName') as FormControl;
  }
  get Email(): FormControl {
    return this.registerForm.get('email') as FormControl;
  }
  get Password(): FormControl {
    return this.registerForm.get('password') as FormControl;
  }
  get RPassword(): FormControl {
    return this.registerForm.get('rPassword') as FormControl;
  }
  get userName(): FormControl {
    return this.registerForm.get('userName') as FormControl;
  }

  get address(): FormControl {
    return this.registerForm.get('address') as FormControl;
  }

  get phoneNumber(): FormControl {
    return this.registerForm.get('phoneNumber') as FormControl;
  }

}
