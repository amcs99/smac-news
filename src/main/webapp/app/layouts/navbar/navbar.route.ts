import { Route } from '@angular/router';

import { DocBaoComponent } from 'app/doc-bao/doc-bao.component';

export const navbarRoute: Route = {
  path: '',
  component: DocBaoComponent,
  outlet: 'navbar'
};
