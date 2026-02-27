import { CurrencyPipe, DatePipe } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { ListPedidoDto, PedidoDetailDto } from '../../../../models/pedido';
import { PedidoService } from '../../../../services/pedido.service';

export type PagarPedidoModalData = {
  pedido: ListPedidoDto;
  detalles: PedidoDetailDto[];
};

import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-pagar-pedido-modal',
  imports: [CurrencyPipe, DatePipe, MatButtonModule, MatDialogModule, MatIconModule, CommonModule],
  templateUrl: './pagar-pedido-modal.html',
  styleUrl: './pagar-pedido-modal.css',
})
export class PagarPedidoModalComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: PagarPedidoModalData,
    public dialogRef: MatDialogRef<PagarPedidoModalComponent>,
    private pedidoService: PedidoService
  ) { }

  public pagar() {
    this.pedidoService.pagar(this.data.pedido.id).subscribe({
      next: (res) => {
        window.alert(res.message);
        this.dialogRef.close(true);
      },
    });
  }
}
