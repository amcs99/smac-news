import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITintuc, Tintuc } from 'app/shared/model/tintuc.model';
import { TintucService } from './tintuc.service';
import { TintucComponent } from './tintuc.component';
import { TintucDetailComponent } from './tintuc-detail.component';
import { TintucUpdateComponent } from './tintuc-update.component';

@Injectable({ providedIn: 'root' })
export class TintucResolve implements Resolve<ITintuc> {
  constructor(private service: TintucService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITintuc> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tintuc: HttpResponse<Tintuc>) => {
          if (tintuc.body) {
            return of(tintuc.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Tintuc());
  }
}

export const tintucRoute: Routes = [
  {
    path: '',
    component: TintucComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'smacnewsApp.tintuc.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TintucDetailComponent,
    resolve: {
      tintuc: TintucResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'smacnewsApp.tintuc.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TintucUpdateComponent,
    resolve: {
      tintuc: TintucResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'smacnewsApp.tintuc.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TintucUpdateComponent,
    resolve: {
      tintuc: TintucResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'smacnewsApp.tintuc.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
