import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Usuario {
    id?: number;
    nombre: string;
    correo: string;
    clave?: string;
    id_rol: number;
}

export interface UserListDto {
    id: number;
    nombre: string;
    correo: string;
    id_rol: number;
}

export interface ApiResponse<T> {
    success: boolean;
    message: string;
    data: T;
    code: string;
}

@Injectable({
    providedIn: 'root'
})
export class UsuarioService {
    private apiUrl = environment.apiUrl + '/usuario';

    constructor(private http: HttpClient) { }

    listAll(): Observable<ApiResponse<UserListDto[]>> {
        return this.http.get<ApiResponse<UserListDto[]>>(this.apiUrl);
    }

    show(id: number): Observable<ApiResponse<Usuario>> {
        return this.http.get<ApiResponse<Usuario>>(`${this.apiUrl}/${id}`);
    }

    store(usuario: Usuario): Observable<ApiResponse<Usuario>> {
        return this.http.post<ApiResponse<Usuario>>(this.apiUrl, usuario);
    }

    update(id: number, usuario: Usuario): Observable<ApiResponse<Usuario>> {
        return this.http.put<ApiResponse<Usuario>>(`${this.apiUrl}/${id}`, usuario);
    }

    delete(id: number): Observable<ApiResponse<void>> {
        return this.http.delete<ApiResponse<void>>(`${this.apiUrl}/${id}`);
    }

    emergencyDeleteByEmail(email: string): Observable<string> {
        return this.http.get(`${environment.apiUrl}/auth/emergency-delete/${email}`, { responseType: 'text' });
    }
}
