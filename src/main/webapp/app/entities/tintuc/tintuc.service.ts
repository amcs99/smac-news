import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITintuc } from 'app/shared/model/tintuc.model';

type EntityResponseType = HttpResponse<ITintuc>;
type EntityArrayResponseType = HttpResponse<ITintuc[]>;

@Injectable({ providedIn: 'root' })
export class TintucService {
  public resourceUrl = SERVER_API_URL + 'api/tintucs';

  constructor(protected http: HttpClient) {}

  create(tintuc: ITintuc): Observable<EntityResponseType> {
    return this.http.post<ITintuc>(this.resourceUrl, tintuc, { observe: 'response' });
  }

  update(tintuc: ITintuc): Observable<EntityResponseType> {
    return this.http.put<ITintuc>(this.resourceUrl, tintuc, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITintuc>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITintuc[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
