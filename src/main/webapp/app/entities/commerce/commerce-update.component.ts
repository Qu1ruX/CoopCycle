import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICommerce, Commerce } from 'app/shared/model/commerce.model';
import { CommerceService } from './commerce.service';
import { ICooperative } from 'app/shared/model/cooperative.model';
import { CooperativeService } from 'app/entities/cooperative/cooperative.service';

@Component({
  selector: 'jhi-commerce-update',
  templateUrl: './commerce-update.component.html',
})
export class CommerceUpdateComponent implements OnInit {
  isSaving = false;
  cooperatives: ICooperative[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(30), Validators.pattern('^[A-Z][a-z]+$')]],
    reviews: [null, [Validators.required, Validators.min(1), Validators.max(5)]],
    address: [null, [Validators.required, Validators.maxLength(100)]],
    cooperatives: [],
  });

  constructor(
    protected commerceService: CommerceService,
    protected cooperativeService: CooperativeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commerce }) => {
      this.updateForm(commerce);

      this.cooperativeService.query().subscribe((res: HttpResponse<ICooperative[]>) => (this.cooperatives = res.body || []));
    });
  }

  updateForm(commerce: ICommerce): void {
    this.editForm.patchValue({
      id: commerce.id,
      name: commerce.name,
      reviews: commerce.reviews,
      address: commerce.address,
      cooperatives: commerce.cooperatives,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commerce = this.createFromForm();
    if (commerce.id !== undefined) {
      this.subscribeToSaveResponse(this.commerceService.update(commerce));
    } else {
      this.subscribeToSaveResponse(this.commerceService.create(commerce));
    }
  }

  private createFromForm(): ICommerce {
    return {
      ...new Commerce(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      reviews: this.editForm.get(['reviews'])!.value,
      address: this.editForm.get(['address'])!.value,
      cooperatives: this.editForm.get(['cooperatives'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommerce>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICooperative): any {
    return item.id;
  }

  getSelected(selectedVals: ICooperative[], option: ICooperative): ICooperative {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
