import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortfolioComponent } from './components/portfolio/portfolio.component';
import { HeaderComponent } from './components/header/header.component';
import { AboutComponent } from './components/about/about.component';
import { ExperienceComponent } from './components/experience/experience.component';
import { EducationComponent } from './components/education/education.component';
import { SkillsComponent } from './components/skills/skills.component';
import { ProjectsComponent } from './components/projects/projects.component';
import { ContactComponent } from './components/contact/contact.component';
import { ExperienceItemComponent } from './components/experience-item/experience-item.component';
import { EducationItemComponent } from './components/education-item/education-item.component';
import { ProjectsItemComponent } from './components/projects-item/projects-item.component';
import { SkillsItemComponent } from './components/skills-item/skills-item.component';
import { ProjectGalleryComponent } from './components/project-gallery/project-gallery.component';
import { ContactFormComponent } from './components/contact-form/contact-form.component';
import { LoginComponent } from './components/login/login.component';
import { EditItemComponent } from './components/edit-item/edit-item.component';
import { AddItemComponent } from './components/add-item/add-item.component';
import { DeleteItemComponent } from './components/delete-item/delete-item.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    PortfolioComponent,
    HeaderComponent,
    AboutComponent,
    ExperienceComponent,
    EducationComponent,
    SkillsComponent,
    ProjectsComponent,
    ContactComponent,
    ExperienceItemComponent,
    EducationItemComponent,
    ProjectsItemComponent,
    SkillsItemComponent,
    ProjectGalleryComponent,
    ContactFormComponent,
    LoginComponent,
    EditItemComponent,
    AddItemComponent,
    DeleteItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
