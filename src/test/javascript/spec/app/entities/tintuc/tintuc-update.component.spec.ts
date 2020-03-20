import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SmacnewsTestModule } from '../../../test.module';
import { TintucUpdateComponent } from 'app/entities/tintuc/tintuc-update.component';
import { TintucService } from 'app/entities/tintuc/tintuc.service';
import { Tintuc } from 'app/shared/model/tintuc.model';

describe('Component Tests', () => {
  describe('Tintuc Management Update Component', () => {
    let comp: TintucUpdateComponent;
    let fixture: ComponentFixture<TintucUpdateComponent>;
    let service: TintucService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SmacnewsTestModule],
        declarations: [TintucUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TintucUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TintucUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TintucService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Tintuc(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Tintuc();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
