import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'news',
        loadChildren: () => import('./news/news.module').then(m => m.SmacnewsNewsModule)
      },
      {
        path: 'tintuc',
        loadChildren: () => import('./tintuc/tintuc.module').then(m => m.SmacnewsTintucModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class SmacnewsEntityModule {}
