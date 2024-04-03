import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home.component';
import { InboundComponent } from '../inbound/inbound.component';
import { InboundModule } from '../inbound/inbound.module';
import { ProductionComponent } from '../production/production.component';
import { OutboundProductionsComponent } from '../outbound-productions/outbound-productions.component';

const routes: Routes = [
  { path: '', component: HomeComponent, children : [
    { path: '', loadChildren: () => import('../inbound/inbound.module').then(m => m.InboundModule) },
    { path: 'inbound', loadChildren: () => import('../inbound/inbound.module').then(m => m.InboundModule) },
    { path: 'production', component: ProductionComponent},
    { path: 'outbound', component: OutboundProductionsComponent},
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
