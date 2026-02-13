import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderToolbarComponent } from '../header-toolbar/header-toolbar';

@Component({
  selector: 'app-layout',
  imports: [HeaderToolbarComponent, RouterOutlet],
  templateUrl: './layout.html',
  styleUrl: './layout.css',
})
export class Layout { }
