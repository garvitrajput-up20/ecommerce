import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  

  url="http://localhost:9090/"

  constructor(private http:HttpClient, private user: LoginService) { }

  getProducts()
  {
    return this.http.get(this.url+"products");

  }

  getCode(code: any)
  {
    return this.http.get(this.url+"products/code/"+code);
  }

  getPrice(price: any)
  {
    return this.http.get(this.url+"products/price/"+price);
  }

  getName(name: any)
  {
    return this.http.get(this.url+"products/name/"+name);
  }

  getBrand(brand: any)
  {
    return this.http.get(this.url+"products/brand/"+brand);
  }

  getID(id: any)
  {
    return this.http.get(this.url+"products/id/"+id);
  }

  getpincode(id: any,pincode: any)
  {
    return this.http.get(this.url+"serviceability/"+id+"/"+pincode);
  }
 
}

