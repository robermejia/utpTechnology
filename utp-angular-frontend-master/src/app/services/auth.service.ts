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
    return this.http.post<ApiResponse<any>>(`${this.apiUrl}/login`, { email, password }).pipe(
      map(res => {
        const user = res.data.user;
        const loginResponse: LoginResponse = {
          access_token: res.data.accessToken,
          user: user
        };
        return { ...res, data: loginResponse };
      })
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
