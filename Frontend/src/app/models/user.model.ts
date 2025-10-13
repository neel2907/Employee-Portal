// src/app/models/user.model.ts

export interface User {
  id: number;
  name: string;
  email: string;
  roles: string[];
  // add other fields as needed
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  name: string;
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  user: User;
}

export interface JwtPayload {
  sub: string;       // subject - usually user id/email
  iat: number;       // issued at
  exp: number;       // expiry
  roles?: string[];  // user roles if encoded
  // add other JWT claims as needed
}
