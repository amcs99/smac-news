import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { SmacnewsTestModule } from '../../../test.module';
import { TintucDetailComponent } from 'app/entities/tintuc/tintuc-detail.component';
import { Tintuc } from 'app/shared/model/tintuc.model';

describe('Component Tests', () => {
  describe('Tintuc Management Detail Component', () => {
    let comp: TintucDetailComponent;
    let fixture: ComponentFixture<TintucDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ tintuc: new Tintuc(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SmacnewsTestModule],
        declarations: [TintucDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TintucDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TintucDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load tintuc on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tintuc).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
