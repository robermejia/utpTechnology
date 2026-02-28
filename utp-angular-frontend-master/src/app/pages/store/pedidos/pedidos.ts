import { CurrencyPipe, DatePipe, CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnDestroy, ViewChild, ChangeDetectorRef } from '@angular/core';
import Swal from 'sweetalert2';
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
import { MatTooltip } from '@angular/material/tooltip';
import { startWith, Subscription, switchMap } from 'rxjs';
import { ListPedidoDto } from '../../../models/pedido';
import { PedidoService } from '../../../services/pedido.service';
import { HeaderToolbarFinalComponent } from '../../shared/header-toolbar/header-toolbar';
import {
  DetallePedidoModalComponent,
  DetallePedidoModalData,
} from './detalle-pedido-modal/detalle-pedido-modal';
import { PagarPedidoModalComponent } from './pagar-pedido-modal/pagar-pedido-modal';

@Component({
  selector: 'app-pedidos',
  imports: [
    CommonModule,
    CurrencyPipe,
    DatePipe,
    MatIconModule,
    MatTooltip,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    HeaderToolbarFinalComponent,
  ],
  templateUrl: './pedidos.html',
  styleUrl: './pedidos.css',
})
export class PedidosStoreComponent implements AfterViewInit, OnDestroy {
  private readonly subscription = new Subscription();
  public isLoading = true;
  public resultsLength = 0;
  public sortOrder?: string;
  public sortBy?: string;

  columns = ['id', 'fecha', 'estado', 'total', 'acciones'];

  public readonly dataSource = new MatTableDataSource<ListPedidoDto>();

  @ViewChild(MatPaginator)
  readonly paginator!: MatPaginator;

  constructor(
    private pedidoService: PedidoService,
    private dialog: MatDialog,
    private cd: ChangeDetectorRef
  ) { }

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.initPage();
    }, 0);
  }

  private initPage(): void {
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
            this.cd.detectChanges();
          },
          error: (err) => {
            this.isLoading = false;
            console.error(err);
            this.cd.detectChanges();
          }
        })
    );
  }

  cargarPedidos() {
    return this.pedidoService.getPedidos(
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

  public descargarComprobante(pedido: ListPedidoDto): void {
    this.pedidoService.descargarComprobante(pedido.id);
  }

  public pagarPedido(pedido: ListPedidoDto): void {
    this.pedidoService.verDetalles(pedido.id).subscribe({
      next: (res) => {
        this.dialog
          .open<PagarPedidoModalComponent>(PagarPedidoModalComponent, {
            data: { pedido, detalles: res.data },
            width: '95vw',
            maxWidth: '500px'
          })
          .afterClosed()
          .subscribe((res) => {
            this.paginator.page.emit();
          });
      },
    });
  }

  public anularPedido(pedido: ListPedidoDto): void {
    Swal.fire({
      title: '¿Anular pedido?',
      text: "¿Estás seguro de que deseas anular este pedido?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#ef4444',
      cancelButtonColor: '#475569',
      confirmButtonText: 'Sí, anular',
      cancelButtonText: 'Cancelar',
      background: '#1e293b',
      color: '#fff'
    }).then((result) => {
      if (result.isConfirmed) {
        this.pedidoService.anular(pedido.id).subscribe({
          next: (res) => {
            Swal.fire({
              title: 'Anulado',
              text: res.message,
              icon: 'success',
              background: '#1e293b',
              color: '#fff'
            });
            this.paginator.page.emit();
          },
        });
      }
    });
  }

  public verDetallesPedido(pedido: ListPedidoDto): void {
    this.pedidoService.verDetalles(pedido.id).subscribe({
      next: (res) => {
        this.dialog.open<DetallePedidoModalComponent, DetallePedidoModalData>(
          DetallePedidoModalComponent,
          {
            data: { pedido, detalles: res.data },
            width: '95vw',
            maxWidth: '600px'
          }
        );
      },
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
