import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_API_URL } from './app.constants';
import { Observable } from 'rxjs';
import { ITintuc } from './shared/model/tintuc.model';

@Injectable({
  providedIn: 'root'
})
export class DocBaoService {
  subUrl = 'api/tin-tuc/get';
  urlGetCount = 'api/tin-tuc/soluongtintuc';
  constructor(private http: HttpClient) {}

  create(start: number, length: number): Observable<ITintuc[]> {
    return this.http.get<ITintuc[]>(SERVER_API_URL + this.subUrl + '?start=' + start + '&length=' + length);
  }

  getCount(): Observable<number> {
    return this.http.get<number>(SERVER_API_URL + this.urlGetCount);
  }
}
