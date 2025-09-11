import { Routes } from '@angular/router';
import { EventListComponent } from './event-list/event-list.component';
import { EventDetailsComponent } from './event-details/event-details.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';  // Import the RegisterComponent
import { LoginComponent } from './login/login.component';

export const APP_ROUTES: Routes = [
  { path: '', component: HomeComponent },  // Home route
  { path: 'events', component: EventListComponent },  // Events list route
  { path: 'event-details/:id', component: EventDetailsComponent },  // Event details route
  { path: 'register', component: RegisterComponent },  // Register route
  { path: 'login', component: LoginComponent }, 
];
