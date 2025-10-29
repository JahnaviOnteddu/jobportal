import { Component } from '@angular/core';
import { NavigationEnd, Router, RouterModule, RouterOutlet } from '@angular/router';
import { Navbar } from './component/navbar/navbar';
import { CommonModule } from '@angular/common';
import { Auth } from './service/auth';
import { filter } from 'rxjs';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-root',
  standalone: true,              // ✅ required in standalone mode
  imports: [RouterOutlet, Navbar, CommonModule , RouterModule , FormsModule ],  // ✅ import your Navbar
  templateUrl: './app.html',
  styleUrls: ['./app.css']        // ✅ should be plural “styleUrls”
})
export class AppComponent {

  showNavbar = false;


  navbarRoutes: string[] = [
    '/',
    '/home',
    '/login',
    '/contact',
    '/register'
  ];

  constructor(private router: Router) {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        this.showNavbar = this.navbarRoutes.some(path =>
          event.url === path || event.url.startsWith(path + '?')
        );
      });
    }
  
}
