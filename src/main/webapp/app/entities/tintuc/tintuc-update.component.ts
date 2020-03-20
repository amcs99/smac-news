import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ITintuc, Tintuc } from 'app/shared/model/tintuc.model';
import { TintucService } from './tintuc.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-tintuc-update',
  templateUrl: './tintuc-update.component.html'
})
export class TintucUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tieuDe: [],
    imageThumbnail: [],
    thoiGianDang: [],
    noiDungTomTat: [null, [Validators.maxLength(1000)]],
    noiDungDayDu: [],
    tacGia: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected tintucService: TintucService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tintuc }) => {
      this.updateForm(tintuc);
    });
  }

  updateForm(tintuc: ITintuc): void {
    this.editForm.patchValue({
      id: tintuc.id,
      tieuDe: tintuc.tieuDe,
      imageThumbnail: tintuc.imageThumbnail,
      thoiGianDang: tintuc.thoiGianDang,
      noiDungTomTat: tintuc.noiDungTomTat,
      noiDungDayDu: tintuc.noiDungDayDu,
      tacGia: tintuc.tacGia
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('smacnewsApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tintuc = this.createFromForm();
    if (tintuc.id !== undefined) {
      this.subscribeToSaveResponse(this.tintucService.update(tintuc));
    } else {
      this.subscribeToSaveResponse(this.tintucService.create(tintuc));
    }
  }

  private createFromForm(): ITintuc {
    return {
      ...new Tintuc(),
      id: this.editForm.get(['id'])!.value,
      tieuDe: this.editForm.get(['tieuDe'])!.value,
      imageThumbnail: this.editForm.get(['imageThumbnail'])!.value,
      thoiGianDang: this.editForm.get(['thoiGianDang'])!.value,
      noiDungTomTat: this.editForm.get(['noiDungTomTat'])!.value,
      noiDungDayDu: this.editForm.get(['noiDungDayDu'])!.value,
      tacGia: this.editForm.get(['tacGia'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITintuc>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
