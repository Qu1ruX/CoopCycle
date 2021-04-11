import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILivreur, Livreur } from 'app/shared/model/livreur.model';
import { LivreurService } from './livreur.service';

@Component({
  selector: 'jhi-livreur-update',
  templateUrl: './livreur-update.component.html',
})
export class LivreurUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    firstname: [null, [Validators.required, Validators.maxLength(30), Validators.pattern('^[A-Z][a-z]+$')]],
    lastname: [null, [Validators.required, Validators.maxLength(30), Validators.pattern('^[A-Z][a-z]+$')]],
    mail: [null, [Validators.required, Validators.pattern('^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$')]],
    phone: [null, [Validators.required]],
    reviews: [null, [Validators.required, Validators.min(1), Validators.max(5)]],
  });

  constructor(protected livreurService: LivreurService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ livreur }) => {
      this.updateForm(livreur);
    });
  }

  updateForm(livreur: ILivreur): void {
    this.editForm.patchValue({
      id: livreur.id,
      firstname: livreur.firstname,
      lastname: livreur.lastname,
      mail: livreur.mail,
      phone: livreur.phone,
      reviews: livreur.reviews,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const livreur = this.createFromForm();
    if (livreur.id !== undefined) {
      this.subscribeToSaveResponse(this.livreurService.update(livreur));
    } else {
      this.subscribeToSaveResponse(this.livreurService.create(livreur));
    }
  }

  private createFromForm(): ILivreur {
    return {
      ...new Livreur(),
      id: this.editForm.get(['id'])!.value,
      firstname: this.editForm.get(['firstname'])!.value,
      lastname: this.editForm.get(['lastname'])!.value,
      mail: this.editForm.get(['mail'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      reviews: this.editForm.get(['reviews'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILivreur>>): void {
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
}
