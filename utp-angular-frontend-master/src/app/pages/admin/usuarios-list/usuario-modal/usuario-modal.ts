import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { UsuarioService, Usuario, UserListDto } from '../../../../services/usuario.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
    selector: 'app-usuario-modal',
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
        MatSelectModule,
        MatIconModule
    ],
    templateUrl: './usuario-modal.html',
    styleUrl: './usuario-modal.css'
})
export class UsuarioModalComponent implements OnInit {
    usuarioForm!: FormGroup;
    isEditMode = false;
    isLoading = false;

    roles = [
        { id: 1, name: 'Súper Admin (Control Total)' },
        { id: 2, name: 'Empleado (Solo Manejo)' },
        { id: 3, name: 'Cliente (Lectura)' }
    ];

    constructor(
        private fb: FormBuilder,
        private usuarioService: UsuarioService,
        private snackBar: MatSnackBar,
        public dialogRef: MatDialogRef<UsuarioModalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: UserListDto | null
    ) {
        this.isEditMode = !!data;
    }

    ngOnInit(): void {
        this.usuarioForm = this.fb.group({
            nombre: [this.data?.nombre || '', [Validators.required, Validators.minLength(3)]],
            correo: [this.data?.correo || '', [Validators.required, Validators.email]],
            clave: ['', this.isEditMode ? [] : [Validators.required, Validators.minLength(6)]],
            idRol: [this.data?.idRol || 3, Validators.required]
        });
    }

    guardar(): void {
        if (this.usuarioForm.invalid) {
            return;
        }

        this.isLoading = true;
        const formData: Usuario = this.usuarioForm.value;

        if (this.isEditMode && this.data) {
            this.usuarioService.update(this.data.id, formData).subscribe({
                next: (res) => {
                    this.isLoading = false;
                    this.snackBar.open('Usuario actualizado exitosamente', 'Cerrar', { duration: 3000 });
                    this.dialogRef.close(true);
                },
                error: (err) => {
                    console.error(err);
                    this.snackBar.open('Error al actualizar usuario', 'Cerrar', { duration: 3000 });
                    this.isLoading = false;
                }
            });
        } else {
            this.usuarioService.store(formData).subscribe({
                next: (res) => {
                    this.snackBar.open('Usuario creado exitosamente', 'Cerrar', { duration: 3000 });
                    this.dialogRef.close(true);
                },
                error: (err) => {
                    console.error(err);
                    this.snackBar.open('Error al crear usuario', 'Cerrar', { duration: 3000 });
                    this.isLoading = false;
                }
            });
        }
    }

    cerrar(): void {
        this.dialogRef.close(false);
    }
}
