import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import Swal from 'sweetalert2';

import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-login',
  imports: [ReactiveFormsModule, MatButtonModule, MatIconModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  form = new FormGroup({
    email: new FormControl('empleado@tienda.com', [
      Validators.required,
      Validators.email,
    ]),
    password: new FormControl('123456', [Validators.required]),
  });

  passwordType: 'password' | 'text' = 'password';

  constructor(private authService: AuthService, private router: Router) { }

  togglePassword() {
    if (this.passwordType === 'password') this.passwordType = 'text';
    else this.passwordType = 'password';
  }

  loginClick() {
    let email: string = this.form.value.email || '';
    let password: string = this.form.value.password || '';

    this.authService.login(email, password).subscribe({
      next: (res) => {
        this.authService.loginExitoso(res.data);
        if ([1, 2].includes(res.data.user.id_rol)) {
          this.router.navigate(['/admin/productos']);
        } else {
          this.router.navigate(['/productos']);
        }
      },
      error: (ex) => {
        if (ex instanceof HttpErrorResponse) {
          if (!!ex.error.message) {
            Swal.fire({
              title: 'Error de Acceso',
              text: ex.error.message,
              icon: 'error',
              background: '#1e293b',
              color: '#fff',
              confirmButtonColor: '#ef4444'
            });
          }
        }
      },
    });
  }
}
