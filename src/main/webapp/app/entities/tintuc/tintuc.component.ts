import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITintuc } from 'app/shared/model/tintuc.model';
import { TintucService } from './tintuc.service';
import { TintucDeleteDialogComponent } from './tintuc-delete-dialog.component';

@Component({
  selector: 'jhi-tintuc',
  templateUrl: './tintuc.component.html'
})
export class TintucComponent implements OnInit, OnDestroy {
  tintucs?: ITintuc[];
  eventSubscriber?: Subscription;

  constructor(
    protected tintucService: TintucService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tintucService.query().subscribe((res: HttpResponse<ITintuc[]>) => (this.tintucs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTintucs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITintuc): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInTintucs(): void {
    this.eventSubscriber = this.eventManager.subscribe('tintucListModification', () => this.loadAll());
  }

  delete(tintuc: ITintuc): void {
    const modalRef = this.modalService.open(TintucDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tintuc = tintuc;
  }
}
