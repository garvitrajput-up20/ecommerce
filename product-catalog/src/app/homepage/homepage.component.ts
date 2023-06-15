import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import { Router, RouterModule, Routes } from '@angular/router';
import { DomSanitizer, SafeResourceUrl, SafeUrl } from '@angular/platform-browser'; 


@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.sass']
})
export class HomepageComponent implements OnInit {

  Homepage = new FormGroup({
    search: new FormControl('', Validators.required),
    filter: new FormControl('name', Validators.required)

  })
  error = false

  userData=false;
   

  get search() { return this.Homepage.get('search') }



  constructor(private product: ProductService, private router: Router , private sanitizer: DomSanitizer) { }

  products: any
  
  ngOnInit(): void {
    if(localStorage.getItem("userData")!=null){
      this.userData=true;
    }
    this.product.getProducts().subscribe((result) => {
      console.warn(result)
      this.products = result
      console.log(this.products)


    })
  }
  getProductImageUrl(base64String: string): SafeUrl{
    const dataurl= 'data:image/jpeg;base64,' + base64String;
    return this.sanitizer.bypassSecurityTrustUrl(dataurl);
  }
  logout()
  {
    localStorage.setItem("logged","false")
    localStorage.removeItem("userData");
    this.router.navigate(['/login']);
  }
  details(id: any) {
   
    this.router.navigate(['/productDetails/' + id]);
  }

  login() {
    this.router.navigate(['/login']);
  }
  

  Search() {
    console.log(this.Homepage.value)
    if (this.Homepage.value.filter == "name") {
      this.product.getName(this.Homepage.value.search).subscribe((result) => {
        console.warn(result)
        this.products = result
        console.log(this.products)

      })

    }

    if (this.Homepage.value.filter == "brand") {
      this.product.getBrand(this.Homepage.value.search).subscribe((result) => {
        console.warn(result)
        this.products = result
        console.log(this.products)

      })

    }

    if (this.Homepage.value.filter == "code") {
      this.product.getCode(this.Homepage.value.search).subscribe((result) => {
        console.warn(result)
        this.products = result
        console.log(this.products)

      })

    }

    if (this.Homepage.value.filter == "price") {
      this.product.getPrice(this.Homepage.value.search).subscribe((result) => {
        console.warn(result)
        this.products = result
        console.log(this.products)

      })

    }
  }
}
