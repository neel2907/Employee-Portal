import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { AuthGuard } from './guards/auth.guard'; // Optional: for protecting routes

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  // Protect dashboard route, only accessible after login

  { path: 'register', component: RegisterComponent, canActivate: [AuthGuard] },
  // Redirect empty path to login

  { path: '', redirectTo: '/login', pathMatch: 'full' },
  // Wildcard route for 404 - page not found (optional)
  
  { path: '**', redirectTo: '/login' }
];
