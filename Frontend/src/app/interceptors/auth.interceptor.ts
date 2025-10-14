 import { Injectable } from '@angular/core';
// import {
//   HttpEvent,
//   HttpHandler,
//   HttpInterceptor,
//   HttpRequest,
//   HttpErrorResponse
// } from '@angular/common/http';
// import { Observable, throwError } from 'rxjs';
// import { catchError } from 'rxjs/operators';
// import { AuthService } from '../services/auth.service';
// import { Router } from '@angular/router';

// @Injectable()
// export class AuthInterceptor implements HttpInterceptor {

//   constructor(
//     private authService: AuthService,
//     private router: Router
//   ) {}

//   intercept(
//     req: HttpRequest<any>,
//     next: HttpHandler
//   ): Observable<HttpEvent<any>> {

//     // Get the auth token from the service
//     const authToken = this.authService.getToken();

//     // Clone the request and add the Authorization header if token exists
//     let authReq = req;
//     if (authToken) {
//       authReq = req.clone({
//         setHeaders: {
//           Authorization: `Bearer ${authToken}`
//         }
//       });
//     }

//     // Handle the request and catch errors
//     return next.handle(authReq).pipe(
//       catchError((error: HttpErrorResponse) => {
//         // Optionally handle unauthorized errors (401)
//         if (error.status === 401) {
//           // You might want to logout the user and redirect to login page
//           this.authService.logout();
//           this.router.navigate(['/login']);
//         }
//         return throwError(() => error);
//       })
//     );
//   }
// }

import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getToken();

  if (token) {
    const authReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`)
    });
    return next(authReq);
  }

  return next(req);
};
