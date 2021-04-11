import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CoopCycleSharedModule } from 'app/shared/shared.module';
import { LivreurComponent } from './livreur.component';
import { LivreurDetailComponent } from './livreur-detail.component';
import { LivreurUpdateComponent } from './livreur-update.component';
import { LivreurDeleteDialogComponent } from './livreur-delete-dialog.component';
import { livreurRoute } from './livreur.route';

@NgModule({
  imports: [CoopCycleSharedModule, RouterModule.forChild(livreurRoute)],
  declarations: [LivreurComponent, LivreurDetailComponent, LivreurUpdateComponent, LivreurDeleteDialogComponent],
  entryComponents: [LivreurDeleteDialogComponent],
})
export class CoopCycleLivreurModule {}
