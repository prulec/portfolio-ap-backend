import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PortfolioComponent } from './components/portfolio/portfolio.component';
import { ProjectGalleryComponent } from './components/project-gallery/project-gallery.component';

const routes: Routes = [
  {path: '', redirectTo: 'portfolio', pathMatch: 'full'},
  {path: 'portfolio', component: PortfolioComponent},
  {path: 'gallery', component: ProjectGalleryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
