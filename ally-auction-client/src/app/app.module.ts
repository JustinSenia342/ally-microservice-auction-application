import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { Routes, RouterModule, Router } from '@angular/router';
import { AppComponent } from './app.component';
import { CreateAuctionComponent } from './create-auction/create-auction.component';
import { AuctionDetailsComponent } from './auction-details/auction-details.component';
import { AuctionListComponent } from './auction-list/auction-list.component';
import { HttpClientModule } from '@angular/common/http';
import { UpdateAuctionComponent } from './update-auction/update-auction.component';
import { AuthRoutingModule } from './auth-routing.module';
import { ProtectedComponent } from './protected/protected.component';
import { LoginComponent } from './login/login.component';
import {
	OKTA_CONFIG,
	OktaAuthModule,
	OktaCallbackComponent,
	OktaAuthGuard
} from '@okta/okta-angular';

const config = {
  issuer: 'https://dev-5184859.okta.com/oauth2/default',
  redirectUri: window.location.origin + '/login/callback',
  clientId: '0oac4kj5oa5WzIz915d5',
  pkce: true
}

export function onAuthRequired(oktaAuth, injector) {
  const router = injector.get(Router);

  // Redirect the user to your custom login page
  router.navigate(['/login']);
}

const appRoutes: Routes = [
  {
    path: 'login/callback',
    component: OktaCallbackComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'protected',
    component: ProtectedComponent,
    canActivate: [ OktaAuthGuard ],
    data: {
      onAuthRequired
    }
  }
]

@NgModule({
  declarations: [
    AppComponent,
    CreateAuctionComponent,
    AuctionDetailsComponent,
    AuctionListComponent,
    UpdateAuctionComponent,
    ProtectedComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
	RouterModule.forRoot(appRoutes),
	OktaAuthModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AuthRoutingModule
  ],
  providers: [
  { provide: OKTA_CONFIG, useValue: config },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }