import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PortfolioComponent } from './components/portfolio/portfolio.component';

const routes: Routes = [
  {path: ':portfolioName/edit', component: PortfolioComponent},
  {path: ':portfolioName', component: PortfolioComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
