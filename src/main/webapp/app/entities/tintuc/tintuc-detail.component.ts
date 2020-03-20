import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ITintuc } from 'app/shared/model/tintuc.model';

@Component({
  selector: 'jhi-tintuc-detail',
  templateUrl: './tintuc-detail.component.html'
})
export class TintucDetailComponent implements OnInit {
  tintuc: ITintuc | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tintuc }) => (this.tintuc = tintuc));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
