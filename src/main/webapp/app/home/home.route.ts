import { Route } from '@angular/router';

import { DocBaoComponent } from 'app/doc-bao/doc-bao.component';

export const HOME_ROUTE: Route = {
  path: '',
  component: DocBaoComponent,
  data: {
    authorities: [],
    pageTitle: 'home.title'
  }
};
