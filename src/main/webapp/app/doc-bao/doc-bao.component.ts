import { Component, OnInit } from '@angular/core';
import { Tintuc } from 'app/shared/model/tintuc.model';
import { DocBaoService } from 'app/doc-bao.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-doc-bao',
  templateUrl: './doc-bao.component.html',
  styleUrls: ['./doc-bao.component.scss']
})
export class DocBaoComponent implements OnInit {
  tintucs?: Tintuc[];
  tintucshot?: Tintuc[];
  count: number;
  curPage: number;
  totalItems: number;
  loading = true;
  constructor(private docbaoService: DocBaoService, private spinner: NgxSpinnerService, private router: Router) {}

  ngOnInit(): void {
    this.spinner.show();
    this.docbaoService.getCount().subscribe(soluong => {
      this.count = soluong;
      if (this.count > 0) {
        this.totalItems = Math.floor(this.count / 24) * 15;
        this.getList(0, 24);
      }
    });
  }
  getList(start: number, length: number): void {
    this.docbaoService.create(start, length).subscribe(list => {
      this.tintucs = list.slice(0, 15);
      this.tintucshot = list.slice(15, 24);
      this.loading = false;
      this.spinner.hide();
    });
  }
  getLinkChiTiet(id: number): string {
    return '/chi-tiet-bai-bao/' + id;
  }

  changePage(p: number): void {
    this.curPage = p;
    this.spinner.show();
    this.getList((p - 1) * 24, 24);
  }
}
