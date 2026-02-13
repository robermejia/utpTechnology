import { Component, OnInit, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenu, MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { LoggedUser } from '../../../models/auth.model';

@Component({
  selector: 'app-header-toolbar',
  imports: [
    RouterLink,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
  ],
  templateUrl: './header-toolbar-v2.html',
  styleUrl: './header-toolbar.css',
})
export class HeaderToolbarFinalComponent implements OnInit {
  @ViewChild('menu') public menu!: MatMenu;

  public user?: LoggedUser = undefined;
  public isLogged: boolean = false;
  public isAdmin: boolean = false;
  public isCustomer: boolean = true;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.user = this.authService.getUser() ?? undefined;
    this.isLogged = this.user !== undefined;
    this.isAdmin = [1, 2].includes(this.user?.id_rol || 0);
    if (!this.isLogged) {
      this.isCustomer = true;
    } else {
      this.isCustomer = ![1, 2].includes(this.user?.id_rol!);
    }
  }

  public logout(): void {
    this.authService.logout();
    this.user = undefined;
    this.isLogged = false;
    this.isAdmin = false;
    this.router.navigate(['/']);
  }
}
