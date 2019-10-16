import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { AuthService } from '@core/services/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  username = '';
  password = '';
  // matcher = new MyErrorStateMatcher();
  isLoadingResults = false;
  reactiveForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService) {
    this.reactiveForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  ngOnInit() {
    sessionStorage.setItem('token', '');
  }

  login(form: NgForm) {
    // this.router.navigateByUrl('/');
    let body = new FormData();
    body.append('username', this.reactiveForm.get('username').value );
    body.append('password', this.reactiveForm.get('password').value);
    this.authService.login(body) // TUDO back to token implementation soon
      .subscribe(res => {
        console.log(res);
        if (res.token) {
          localStorage.setItem('token', res.token);
          this.router.navigate(['products']);
        }
      }, (err) => {
        console.log(err);
      });
  }

  register() {
    this.router.navigate(['register']);
  }

}
