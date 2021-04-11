import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.CoopCycleClientModule),
      },
      {
        path: 'commande',
        loadChildren: () => import('./commande/commande.module').then(m => m.CoopCycleCommandeModule),
      },
      {
        path: 'course',
        loadChildren: () => import('./course/course.module').then(m => m.CoopCycleCourseModule),
      },
      {
        path: 'livreur',
        loadChildren: () => import('./livreur/livreur.module').then(m => m.CoopCycleLivreurModule),
      },
      {
        path: 'produit',
        loadChildren: () => import('./produit/produit.module').then(m => m.CoopCycleProduitModule),
      },
      {
        path: 'commerce',
        loadChildren: () => import('./commerce/commerce.module').then(m => m.CoopCycleCommerceModule),
      },
      {
        path: 'cooperative',
        loadChildren: () => import('./cooperative/cooperative.module').then(m => m.CoopCycleCooperativeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class CoopCycleEntityModule {}
