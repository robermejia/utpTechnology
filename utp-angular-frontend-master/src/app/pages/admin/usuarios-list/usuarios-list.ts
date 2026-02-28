import { CurrencyPipe, DatePipe, CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnDestroy, ViewChild, ChangeDetectorRef } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import {
    MatPaginator,
    MatPaginatorModule,
    PageEvent,
} from '@angular/material/paginator';
import { MatSortModule, Sort } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { startWith, Subscription, switchMap, of, catchError, Subject, debounceTime, distinctUntilChanged } from 'rxjs';
import { UserListDto, UsuarioService, Usuario } from '../../../services/usuario.service';
import { FormsModule } from '@angular/forms';
import { UsuarioModalComponent } from './usuario-modal/usuario-modal';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
    selector: 'app-usuarios-list',
    standalone: true,
    imports: [
        CommonModule,
        MatButtonModule,
        MatTableModule,
        MatTooltipModule,
        MatIconModule,
        MatPaginatorModule,
        MatSortModule,
        FormsModule
    ],
    templateUrl: './usuarios-list.html',
    styleUrl: './usuarios-list.css',
})
export class UsuariosListComponent implements AfterViewInit, OnDestroy {
    private readonly subscription = new Subscription();
    isLoading = true;
    resultsLength = 0;
    pageSize = 10;
    pageIndex = 0;

    columns = [
        'id',
        'nombre',
        'correo',
        'rol',
        'acciones',
    ];

    readonly dataSource = new MatTableDataSource<UserListDto>();

    @ViewChild(MatPaginator)
    readonly paginator!: MatPaginator;

    constructor(
        private usuarioService: UsuarioService,
        private dialog: MatDialog,
        private snackBar: MatSnackBar,
        private cd: ChangeDetectorRef
    ) { }

    ngAfterViewInit(): void {
        setTimeout(() => {
            this.cargarUsuarios();
        }, 0);
    }

    cargarUsuarios() {
        this.isLoading = true;
        this.subscription.add(
            this.usuarioService.listAll().subscribe({
                next: (res) => {
                    this.isLoading = false;
                    if (res.success && res.data) {
                        this.resultsLength = res.data.length;

                        // Local pagination handling since backend doesn't paginate listAll yet
                        const startIndex = this.pageIndex * this.pageSize;
                        const endIndex = startIndex + this.pageSize;
                        this.dataSource.data = res.data.slice(startIndex, endIndex);

                        // Forzar detección de cambios para que la tabla se muestre inmediatamente
                        this.cd.detectChanges();
                    }
                },
                error: (err) => {
                    this.isLoading = false;
                    console.error(err);
                    this.cd.detectChanges();
                }
            })
        );
    }

    handlePageEvent(e: PageEvent) {
        this.pageSize = e.pageSize;
        this.pageIndex = e.pageIndex;
        this.cargarUsuarios();
    }

    applyFilter(event: Event) {
        const filterValue = (event.target as HTMLInputElement).value;
        this.dataSource.filter = filterValue.trim().toLowerCase();

        if (this.dataSource.paginator) {
            this.dataSource.paginator.firstPage();
        }
    }

    getRoleName(idRol: number): string {
        switch (idRol) {
            case 1: return 'Súper Admin';
            case 2: return 'Empleado';
            case 3: return 'Cliente';
            default: return 'Desconocido';
        }
    }

    getRoleClass(idRol: number): string {
        switch (idRol) {
            case 1: return 'bg-rose-500/20 text-rose-400 border border-rose-500/30';
            case 2: return 'bg-amber-500/20 text-amber-400 border border-amber-500/30';
            case 3: return 'bg-blue-500/20 text-blue-400 border border-blue-500/30';
            default: return 'bg-slate-500/20 text-slate-400 border border-slate-500/30';
        }
    }

    abrirModal(usuario?: UserListDto) {
        const dialogRef = this.dialog.open(UsuarioModalComponent, {
            width: '95vw',
            maxWidth: '500px',
            data: usuario ? { ...usuario } : null,
            panelClass: 'glass-dialog'
        });

        dialogRef.afterClosed().subscribe((result) => {
            if (result) {
                this.cargarUsuarios();
            }
        });
    }

    eliminar(id: number) {
        if (confirm('¿Está seguro que desea eliminar este usuario? Esa acción no se puede deshacer.')) {
            this.usuarioService.delete(id).subscribe({
                next: (res) => {
                    this.snackBar.open('Usuario eliminado exitosamente', 'Cerrar', { duration: 3000 });
                    this.cargarUsuarios();
                },
                error: (err) => {
                    console.error(err);
                    this.snackBar.open('Error al eliminar usuario', 'Cerrar', { duration: 3000 });
                }
            });
        }
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }
}
