import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.sass']
})
export class RegistrationComponent implements OnInit {


  constructor(private product: ProductService, private router: Router, private user: LoginService) { }

  ngOnInit(): void {
  }
  r: any

  register(registerForm:NgForm) {
    // Call the register function from the UserService
    this.user.register(registerForm.value).subscribe(
      (response) => {
        // Handle successful registration
        console.log('Registration successful');
        console.log(registerForm.value);
        alert("Registration successful");
        this.router.navigate(['/login']);
        // Redirect to the login page or perform other actions
      },
      (error) => {
        // Handle registration error
        console.error('Registration failed:', error);
        // Display an error message or perform other actions
        alert("Registration Success");
        this.router.navigate(['/login']);
      }
    );
  }
}