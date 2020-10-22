import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OktaCallbackComponent } from '@okta/okta-angular';
import { HomeComponent } from './home/home.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { OKTA_CONFIG, OktaAuthModule } from '@okta/okta-angular';
import { AuthInterceptor } from './shared/okta/auth.interceptor';
import { AuctionListComponent } from './auction-list/auction-list.component';
import { OktaAuthGuard } from '@okta/okta-angular';

const oktaConfig = {
  issuer: 'https://dev-5184859.okta.com/oauth2/default',
  redirectUri: window.location.origin + '/implicit/callback',
  clientId: '0oac4kj5oa5WzIz915d5',
  scopes: ['openid', 'profile']
};


const routes: Routes = [
//  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '', redirectTo: '/auctions', pathMatch: 'full' },
  {
	//component: HomeComponent
    path: 'home',
	component: AuctionListComponent,
	canActivate: [OktaAuthGuard]
  },
  {
	//component: OktaCallbackComponent
    path: 'callback',
	component: AuctionListComponent,
	canActivate: [OktaAuthGuard]
  }
];

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    OktaAuthModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    { provide: OKTA_CONFIG, useValue: oktaConfig },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
