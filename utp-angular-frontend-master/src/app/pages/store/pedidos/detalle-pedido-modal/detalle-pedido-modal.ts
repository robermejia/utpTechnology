import { CurrencyPipe, DatePipe } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { ListPedidoDto, PedidoDetailDto } from '../../../../models/pedido';

export type DetallePedidoModalData = {
  pedido: ListPedidoDto;
  detalles: PedidoDetailDto[];
};

@Component({
  selector: 'app-detalle-pedido-modal',
  imports: [CurrencyPipe, DatePipe, MatButtonModule, MatDialogModule, MatIconModule],
  templateUrl: './detalle-pedido-modal.html',
  styleUrl: './detalle-pedido-modal.css',
})
export class DetallePedidoModalComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: DetallePedidoModalData,
    public dialogRef: MatDialogRef<DetallePedidoModalComponent>
  ) { }
}
