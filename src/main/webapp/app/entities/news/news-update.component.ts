import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INews, News } from 'app/shared/model/news.model';
import { NewsService } from './news.service';

@Component({
  selector: 'jhi-news-update',
  templateUrl: './news-update.component.html'
})
export class NewsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tieuDe: [],
    imageThumbnail: [],
    thoiGianDang: [],
    noiDungTomTat: [null, [Validators.maxLength(1000)]],
    noiDungDayDu: [null, [Validators.maxLength(10000)]],
    tacGia: []
  });

  constructor(protected newsService: NewsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ news }) => {
      this.updateForm(news);
    });
  }

  updateForm(news: INews): void {
    this.editForm.patchValue({
      id: news.id,
      tieuDe: news.tieuDe,
      imageThumbnail: news.imageThumbnail,
      thoiGianDang: news.thoiGianDang,
      noiDungTomTat: news.noiDungTomTat,
      noiDungDayDu: news.noiDungDayDu,
      tacGia: news.tacGia
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const news = this.createFromForm();
    if (news.id !== undefined) {
      this.subscribeToSaveResponse(this.newsService.update(news));
    } else {
      this.subscribeToSaveResponse(this.newsService.create(news));
    }
  }

  private createFromForm(): INews {
    return {
      ...new News(),
      id: this.editForm.get(['id'])!.value,
      tieuDe: this.editForm.get(['tieuDe'])!.value,
      imageThumbnail: this.editForm.get(['imageThumbnail'])!.value,
      thoiGianDang: this.editForm.get(['thoiGianDang'])!.value,
      noiDungTomTat: this.editForm.get(['noiDungTomTat'])!.value,
      noiDungDayDu: this.editForm.get(['noiDungDayDu'])!.value,
      tacGia: this.editForm.get(['tacGia'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INews>>): void {
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
