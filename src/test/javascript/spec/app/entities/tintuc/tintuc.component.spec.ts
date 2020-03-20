import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SmacnewsTestModule } from '../../../test.module';
import { TintucComponent } from 'app/entities/tintuc/tintuc.component';
import { TintucService } from 'app/entities/tintuc/tintuc.service';
import { Tintuc } from 'app/shared/model/tintuc.model';

describe('Component Tests', () => {
  describe('Tintuc Management Component', () => {
    let comp: TintucComponent;
    let fixture: ComponentFixture<TintucComponent>;
    let service: TintucService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SmacnewsTestModule],
        declarations: [TintucComponent]
      })
        .overrideTemplate(TintucComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TintucComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TintucService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Tintuc(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tintucs && comp.tintucs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
