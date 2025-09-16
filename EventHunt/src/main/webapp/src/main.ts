// src/main.ts
import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';

import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { APP_ROUTES } from './app/app.routes';

import { provideAnimations } from '@angular/platform-browser/animations';

// AngularFire (uses environment.firebase)
import { provideFirebaseApp, initializeApp } from '@angular/fire/app';
import { provideAuth, getAuth } from '@angular/fire/auth';
import { environment } from './environments/environment';

// Raw Firebase SDK (only to “touch” Installations so you can verify init)
import { getApp, getApps, initializeApp as initRaw } from 'firebase/app';
import { getInstallations, getId } from 'firebase/installations';

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    provideRouter(APP_ROUTES),
    provideAnimations(),

    // AngularFire providers (official way)
    provideFirebaseApp(() => initializeApp(environment.firebase)),
    provideAuth(() => getAuth()),
  ]
})
  .then(() => {
    // Force-create Firebase IndexedDB entry so you can verify integration
    const app = getApps().length ? getApp() : initRaw(environment.firebase);
    getId(getInstallations(app))
      .then(id => {
        (window as any).firebaseInstallationId = id; // optional: check in DevTools console
        console.log('✅ Firebase installation ID:', id);
      })
      .catch(err => console.error('❌ Firebase init error:', err));
  })
  .catch(err => console.error('Error during app bootstrap', err));
