import { Injectable } from '@angular/core';
import { ITintuc } from './shared/model/tintuc.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from './app.constants';

@Injectable({
  providedIn: 'root'
})
export class ChiTietBaiBaoService {
  subUrl = 'api/tin-tuc/getbyid';
  constructor(private http: HttpClient) {}

  create(id: string): Observable<ITintuc> {
    return this.http.get<ITintuc>(SERVER_API_URL + this.subUrl + '?id=' + id);
  }
}
