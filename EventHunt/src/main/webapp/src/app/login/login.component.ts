import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, MatButtonModule, MatInputModule, MatCardModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  // 🔐 Call backend login endpoint
  onLogin(): void {
    const loginData = {
      userEmail: this.email,
      password: this.password
    };

    this.http.post('/api/v1/auth/login', loginData, { responseType: 'text' }).subscribe({
      next: (token: string) => {
        console.log('Login successful. Token:', token);
        localStorage.setItem('jwtToken', token); // Store JWT
        alert('Login successful');
        this.router.navigate(['/browse-events']); // Route after login
      },
      error: (err) => {
        console.error('Login failed:', err);
        alert('Login failed: ' + (err.error?.message || err.message || 'Unknown error'));
      }
    });
  }
}
