import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map, switchMap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {



  API = "http://localhost:9090";
  requestHeader = new HttpHeaders({ "No-Auth": "True" });

  constructor(private http: HttpClient, private router: Router) { }

  public login(loginData: any) {
    return this.http.post(this.API + '/authentication/login', loginData, { headers: this.requestHeader }).pipe(
      switchMap((response: any) => {
        const jwt = response.jwt;
        const loggedInUserUrl = this.API + '/authentication/LoggedInUser';
        const loggedInUserHeaders = new HttpHeaders({
          'Authorization': `Bearer ${jwt}`
        });

        const loggedInUserRequest = this.http.get(loggedInUserUrl, { headers: loggedInUserHeaders });
        return loggedInUserRequest.pipe(
          map((loggedInUserResponse: any) => {
            const userData = {
              jwt: jwt,
              message: response.message,
              usersStructure: loggedInUserResponse
            };
            // Save user data to localStorage
            localStorage.setItem('userData', JSON.stringify(userData));
            if (response.message === 'Login Success') {
              this.router.navigate(['/']);
            }
            return userData;
          })
        );
      }),
      catchError((error: any) => {
        if (error instanceof HttpErrorResponse && error.status === 400) {
          // Bad Request: wrong credentials
          console.log("Invalid Details")
        }
        return throwError(error);
      })
    );
  }

  public isLoggedIn(): boolean {
    // Check if user data is present in localStorage
    return !!localStorage.getItem('userData');
  }



  register(userData: any){
    return this.http.post(this.API + '/authentication/registerUser', userData, { headers: this.requestHeader });
  }

}
