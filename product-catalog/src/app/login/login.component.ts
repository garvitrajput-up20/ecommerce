import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  error: boolean = false;

  constructor(private user: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  Login(loginForm:NgForm){
    this.user.login(loginForm.value).subscribe(
      (response)=>{
        console.log(response)
      },
      (error)=>{
        console.log(error)
        this.error = true;
      }
    );
  }


}




  
  
  

  // r: any

  // login() {
  //   console.log(this.Login.value)

  //   this.product.getlogin(this.Login.value.email).subscribe((result) => {
  //     console.warn(result)
  //     this.r = result

  //     if (result == null) {

  //       this.error = true
  //     }
  //     else {

  //       if (this.r.password == this.Login.value.password) {
  //         localStorage.setItem("logged", "true")
  //         this.router.navigate(['']);

  //       }
  //       else {
  //         this.error = true
  //       }
  //     }



  //   })
