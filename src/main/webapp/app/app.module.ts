import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { SmacnewsSharedModule } from 'app/shared/shared.module';
import { SmacnewsCoreModule } from 'app/core/core.module';
import { SmacnewsAppRoutingModule } from './app-routing.module';
import { SmacnewsHomeModule } from './home/home.module';
import { SmacnewsEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { DocBaoComponent } from './doc-bao/doc-bao.component';
import { HeaderComponent } from './header/header.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { NgxPaginationModule } from 'ngx-pagination';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ChiTietBaiBaoComponent } from './chi-tiet-bai-bao/chi-tiet-bai-bao.component';

@NgModule({
  imports: [
    BrowserModule,
    SmacnewsSharedModule,
    SmacnewsCoreModule,
    SmacnewsHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    SmacnewsEntityModule,
    SmacnewsAppRoutingModule,
    NgxSpinnerModule,
    BrowserAnimationsModule,
    NgxPaginationModule
  ],
  declarations: [
    MainComponent,
    NavbarComponent,
    ErrorComponent,
    PageRibbonComponent,
    ActiveMenuDirective,
    FooterComponent,
    DocBaoComponent,
    HeaderComponent,
    ChiTietBaiBaoComponent
  ],
  bootstrap: [MainComponent]
})
export class SmacnewsAppModule {}
