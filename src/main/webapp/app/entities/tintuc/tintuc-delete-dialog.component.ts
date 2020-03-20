import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITintuc } from 'app/shared/model/tintuc.model';
import { TintucService } from './tintuc.service';

@Component({
  templateUrl: './tintuc-delete-dialog.component.html'
})
export class TintucDeleteDialogComponent {
  tintuc?: ITintuc;

  constructor(protected tintucService: TintucService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tintucService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tintucListModification');
      this.activeModal.close();
    });
  }
}
