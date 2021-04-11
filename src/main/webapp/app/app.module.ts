import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CoopCycleSharedModule } from 'app/shared/shared.module';
import { CoopCycleCoreModule } from 'app/core/core.module';
import { CoopCycleAppRoutingModule } from './app-routing.module';
import { CoopCycleHomeModule } from './home/home.module';
import { CoopCycleEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    CoopCycleSharedModule,
    CoopCycleCoreModule,
    CoopCycleHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CoopCycleEntityModule,
    CoopCycleAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class CoopCycleAppModule {}
