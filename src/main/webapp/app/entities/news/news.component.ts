import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INews } from 'app/shared/model/news.model';
import { NewsService } from './news.service';
import { NewsDeleteDialogComponent } from './news-delete-dialog.component';

@Component({
  selector: 'jhi-news',
  templateUrl: './news.component.html'
})
export class NewsComponent implements OnInit, OnDestroy {
  news?: INews[];
  eventSubscriber?: Subscription;

  constructor(protected newsService: NewsService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.newsService.query().subscribe((res: HttpResponse<INews[]>) => (this.news = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNews();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INews): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNews(): void {
    this.eventSubscriber = this.eventManager.subscribe('newsListModification', () => this.loadAll());
  }

  delete(news: INews): void {
    const modalRef = this.modalService.open(NewsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.news = news;
  }
}
