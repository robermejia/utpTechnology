import { Routes } from '@angular/router';
import { superAdminGuard } from '../../guards/super-admin.guard';

export const adminRoutes: Routes = [
  {
    path: '',
    redirectTo: 'pedidos',
    pathMatch: 'full'
  },
  {
    path: 'productos',
    loadComponent: () =>
      import('./producto-list/producto-list').then(
        (m) => m.ProductoListComponent
      ),
  },
  {
    path: 'pedidos',
    loadComponent: () =>
      import('./pedidos-list/pedidos-list').then((m) => m.PedidosAdminComponent),
  },
  {
    path: 'clientes',
    loadComponent: () =>
      import('./clientes-list/clientes-list').then((m) => m.ClientesList),
  },
  {
    path: 'usuarios',
    loadComponent: () =>
      import('./usuarios-list/usuarios-list').then((m) => m.UsuariosListComponent),
    canActivate: [superAdminGuard]
  },
];
