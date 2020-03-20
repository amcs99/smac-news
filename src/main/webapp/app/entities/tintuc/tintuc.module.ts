import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SmacnewsSharedModule } from 'app/shared/shared.module';
import { TintucComponent } from './tintuc.component';
import { TintucDetailComponent } from './tintuc-detail.component';
import { TintucUpdateComponent } from './tintuc-update.component';
import { TintucDeleteDialogComponent } from './tintuc-delete-dialog.component';
import { tintucRoute } from './tintuc.route';

@NgModule({
  imports: [SmacnewsSharedModule, RouterModule.forChild(tintucRoute)],
  declarations: [TintucComponent, TintucDetailComponent, TintucUpdateComponent, TintucDeleteDialogComponent],
  entryComponents: [TintucDeleteDialogComponent]
})
export class SmacnewsTintucModule {}
