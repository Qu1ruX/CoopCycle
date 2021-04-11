import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CoopCycleTestModule } from '../../../test.module';
import { LivreurComponent } from 'app/entities/livreur/livreur.component';
import { LivreurService } from 'app/entities/livreur/livreur.service';
import { Livreur } from 'app/shared/model/livreur.model';

describe('Component Tests', () => {
  describe('Livreur Management Component', () => {
    let comp: LivreurComponent;
    let fixture: ComponentFixture<LivreurComponent>;
    let service: LivreurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CoopCycleTestModule],
        declarations: [LivreurComponent],
      })
        .overrideTemplate(LivreurComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LivreurComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LivreurService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Livreur(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.livreurs && comp.livreurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
