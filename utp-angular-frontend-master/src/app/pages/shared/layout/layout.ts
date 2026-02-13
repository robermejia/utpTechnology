import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderToolbarFinalComponent } from '../header-toolbar/header-toolbar';

@Component({
  selector: 'app-layout',
  imports: [HeaderToolbarFinalComponent, RouterOutlet],
  templateUrl: './layout.html',
  styleUrl: './layout.css',
})
export class Layout { }
