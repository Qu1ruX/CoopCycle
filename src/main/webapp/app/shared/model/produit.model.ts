import { ICommande } from 'app/shared/model/commande.model';
import { ICommerce } from 'app/shared/model/commerce.model';

export interface IProduit {
  id?: number;
  name?: string;
  description?: string;
  price?: number;
  commandes?: ICommande[];
  commerce?: ICommerce;
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public price?: number,
    public commandes?: ICommande[],
    public commerce?: ICommerce
  ) {}
}
