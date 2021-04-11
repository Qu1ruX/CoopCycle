import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILivreur } from 'app/shared/model/livreur.model';
import { LivreurService } from './livreur.service';
import { LivreurDeleteDialogComponent } from './livreur-delete-dialog.component';

@Component({
  selector: 'jhi-livreur',
  templateUrl: './livreur.component.html',
})
export class LivreurComponent implements OnInit, OnDestroy {
  livreurs?: ILivreur[];
  eventSubscriber?: Subscription;

  constructor(protected livreurService: LivreurService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.livreurService.query().subscribe((res: HttpResponse<ILivreur[]>) => (this.livreurs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLivreurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILivreur): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLivreurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('livreurListModification', () => this.loadAll());
  }

  delete(livreur: ILivreur): void {
    const modalRef = this.modalService.open(LivreurDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.livreur = livreur;
  }
}
