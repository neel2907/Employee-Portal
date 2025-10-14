import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { User, LoginRequest, RegisterRequest, AuthResponse, JwtPayload } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();
  private sessionTimeout: any;
  private readonly SESSION_TIMEOUT = 15 * 60 * 1000; // 15 minutes
  private readonly API_URL = 'http://localhost:8090/api'; // Update to match your backend

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  // 🔐 Login
  login(credentials: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/auth/login`, credentials)
      .pipe(
        tap(response => {
          this.setCurrentUser(response.user, response.token);
          this.startSessionTimer();
        })
      );
  }

  // 📝 Registration
  register(userData: RegisterRequest): Observable<any> {
    return this.http.post<any>(`${this.API_URL}/auth/register`, userData);
  }

  // 🚪 Logout
  logout(): void {
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.removeItem('currentUser');
      localStorage.removeItem('token');
    }
    this.currentUserSubject.next(null);
    this.clearSessionTimer();
    this.router.navigate(['/login']);
  }

  // 👤 Get Current User
  getCurrentUser(): User | null {
    return this.currentUserSubject.value;
  }

  // 🔑 Get JWT Token
  getToken(): string | null {
    if (typeof window !== 'undefined' && window.localStorage) {
      return localStorage.getItem('token');
    }
    return null;
  }

  // ✅ Check Authentication
  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;

    try {
      const payload = this.decodeToken(token);
      const currentTime = Math.floor(Date.now() / 1000);
      return payload.exp > currentTime;
    } catch {
      return false;
    }
  }

  // 🔄 Load User from Storage (call from AppComponent ngOnInit)
  public loadUserFromStorage(): void {
    if (typeof window !== 'undefined' && window.localStorage) {
      const userStr = localStorage.getItem('currentUser');
      const token = localStorage.getItem('token');

      if (userStr && token && this.isAuthenticated()) {
        const user = JSON.parse(userStr);
        this.currentUserSubject.next(user);
      } else {
        localStorage.removeItem('currentUser');
        localStorage.removeItem('token');
      }
    } else {
      console.warn('localStorage is not available in this environment.');
    }
  }

  // 🔐 Decode JWT Token
  private decodeToken(token: string): JwtPayload {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(c =>
      '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
    ).join(''));

    return JSON.parse(jsonPayload);
  }

  // 🕒 Session Management
  resetSessionTimer(): void {
    if (this.isAuthenticated()) {
      this.startSessionTimer();
    }
  }

  private startSessionTimer(): void {
    this.clearSessionTimer();
    this.sessionTimeout = setTimeout(() => {
      this.logout();
    }, this.SESSION_TIMEOUT);
  }

  private clearSessionTimer(): void {
    if (this.sessionTimeout) {
      clearTimeout(this.sessionTimeout);
      this.sessionTimeout = null;
    }
  }

  private setCurrentUser(user: User, token: string): void {
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.setItem('currentUser', JSON.stringify(user));
      localStorage.setItem('token', token);
    }
    this.currentUserSubject.next(user);
  }

  // 🔧 Profile Update
  updateProfile(userId: string, profileData: Partial<User>): Observable<User> {
    return this.http.put<User>(`${this.API_URL}/admin/users/${userId}`, profileData)
      .pipe(
        tap(updatedUser => {
          this.setCurrentUser(updatedUser, this.getToken() || '');
        })
      );
  }

  // 🔐 Password Reset
  initiatePasswordReset(email: string): Observable<{ message: string }> {
    return this.http.post<{ message: string }>(`${this.API_URL}/auth/forgot-password`, { email });
  }

  verifyOTPAndResetPassword(email: string, otp: string, newPassword: string): Observable<{ message: string }> {
    return this.http.post<{ message: string }>(`${this.API_URL}/auth/reset-password`, {
      email,
      otp,
      newPassword
    });
  }
}
