import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, from, map, switchMap } from 'rxjs';
import { environment } from '../../environments/environment';
import { ApiResponse } from '../models/api';
import { LoggedUser, LoginResponse } from '../models/auth.model';
import { Auth, signInWithEmailAndPassword, user, getIdToken, signOut } from '@angular/fire/auth';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly apiUrl;
  private tokenKey = 'token';
  private auth = inject(Auth);

  constructor(private http: HttpClient) {
    this.apiUrl = `${environment.apiUrl}/auth`;
  }

  login(
    email: string,
    password: string
  ): Observable<ApiResponse<LoginResponse>> {
    return from(signInWithEmailAndPassword(this.auth, email, password)).pipe(
      switchMap(userCredential => from(getIdToken(userCredential.user)).pipe(
        map(token => {
          // Firebase doesn't return the custom 'user' object with id_rol directly
          // This should ideally come from our backend or custom claims
          // For now, we simulate the response to match the existing UI expectations
          const loginResponse: LoginResponse = {
            access_token: token,
            user: {
              id: 0, // Should be fetched from backend or claims
              nombre: userCredential.user.displayName || userCredential.user.email || '',
              correo: userCredential.user.email || '',
              id_rol: 3, // Default role, should be handled by custom claims
              token_expiracion: ''
            }
          };
          return { data: loginResponse, message: 'Success', status: 200 } as ApiResponse<LoginResponse>;
        })
      ))
    );
  }

  loginExitoso(data: LoginResponse) {
    localStorage.setItem(this.tokenKey, data.access_token);
    localStorage.setItem('user', JSON.stringify(data.user));
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getUser(): LoggedUser | null {
    let userJsonString = localStorage.getItem('user');
    if (userJsonString === null) return null;
    return JSON.parse(userJsonString) as LoggedUser;
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem('user');
    signOut(this.auth);
  }
}
