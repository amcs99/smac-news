import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TintucService } from 'app/entities/tintuc/tintuc.service';
import { ITintuc, Tintuc } from 'app/shared/model/tintuc.model';

describe('Service Tests', () => {
  describe('Tintuc Service', () => {
    let injector: TestBed;
    let service: TintucService;
    let httpMock: HttpTestingController;
    let elemDefault: ITintuc;
    let expectedResult: ITintuc | ITintuc[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TintucService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Tintuc(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Tintuc', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Tintuc()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Tintuc', () => {
        const returnedFromService = Object.assign(
          {
            tieuDe: 'BBBBBB',
            imageThumbnail: 'BBBBBB',
            thoiGianDang: 'BBBBBB',
            noiDungTomTat: 'BBBBBB',
            noiDungDayDu: 'BBBBBB',
            tacGia: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Tintuc', () => {
        const returnedFromService = Object.assign(
          {
            tieuDe: 'BBBBBB',
            imageThumbnail: 'BBBBBB',
            thoiGianDang: 'BBBBBB',
            noiDungTomTat: 'BBBBBB',
            noiDungDayDu: 'BBBBBB',
            tacGia: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Tintuc', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
