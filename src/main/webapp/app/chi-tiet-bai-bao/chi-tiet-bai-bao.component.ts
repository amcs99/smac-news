import { Component, OnInit } from '@angular/core';
import { ChiTietBaiBaoService } from 'app/chi-tiet-bai-bao.service';
import { ActivatedRoute } from '@angular/router';
import { ITintuc } from 'app/shared/model/tintuc.model';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'jhi-chi-tiet-bai-bao',
  templateUrl: './chi-tiet-bai-bao.component.html',
  styleUrls: ['./chi-tiet-bai-bao.component.scss']
})
export class ChiTietBaiBaoComponent implements OnInit {
  id: string;

  tintuc: ITintuc;
  constructor(private service: ChiTietBaiBaoService, private route: ActivatedRoute, private spinner: NgxSpinnerService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    if (this.id) {
      this.spinner.show();
      this.service.create(this.id).subscribe(data => {
        this.tintuc = data;
        this.spinner.hide();
      });
    }
  }
}
