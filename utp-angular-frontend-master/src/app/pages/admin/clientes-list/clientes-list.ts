import { AfterViewInit, Component, OnDestroy, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {
  MatPaginator,
  MatPaginatorModule,
  PageEvent,
} from '@angular/material/paginator';
import { MatSortModule, Sort } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { startWith, Subscription, switchMap } from 'rxjs';
import { ListClienteDto } from '../../../models/cliente';
import { ClienteService } from '../../../services/cliente.service';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-clientes-list',
  imports: [
    MatButtonModule,
    MatTableModule,
    MatTooltipModule,
    MatIconModule,
    MatPaginatorModule,
    MatSortModule,
  ],
  templateUrl: './clientes-list.html',
  styleUrl: './clientes-list.css',
})
export class ClientesList implements AfterViewInit, OnDestroy {
  private readonly subscription = new Subscription();
  isLoading = true;
  resultsLength = 0;
  sortOrder?: string;
  sortBy?: string;

  columns = [
    'id',
    'nombre',
    'apellido',
    'dni',
    'telefono',
    'correo',
    'acciones',
  ];

  readonly dataSource = new MatTableDataSource<ListClienteDto>();

  @ViewChild(MatPaginator)
  readonly paginator!: MatPaginator;

  constructor(private clienteService: ClienteService) {}

  ngAfterViewInit(): void {
    this.subscription.add(
      this.paginator.page
        .pipe(
          startWith({} as PageEvent),
          switchMap(() => {
            this.isLoading = true;
            return this.cargarPedidos();
          })
        )
        .subscribe({
          next: (res) => {
            this.isLoading = false;
            this.resultsLength = res.totalSize;
            this.dataSource.data = res.data;
          },
        })
    );
  }

  cargarPedidos() {
    return this.clienteService.getClientes(
      undefined,
      this.paginator.pageIndex,
      this.paginator.pageSize,
      this.sortBy,
      this.sortOrder
    );
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction === '' || sortState.active === 'id') {
      this.sortOrder =
        sortState.direction === '' ? undefined : sortState.direction;
      this.sortBy = undefined;
    } else {
      this.sortOrder = sortState.direction;
      this.sortBy = sortState.active;
    }
    this.paginator.page.emit();
  }

  emitPageEvent() {
    this.paginator.pageIndex = 0;
    this.paginator.page.emit();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  enDesarrollo(): void {
    window.alert('🚧');
  }
}
