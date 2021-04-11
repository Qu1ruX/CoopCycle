import { IProduit } from 'app/shared/model/produit.model';
import { ICommande } from 'app/shared/model/commande.model';
import { ICooperative } from 'app/shared/model/cooperative.model';

export interface ICommerce {
  id?: number;
  name?: string;
  reviews?: number;
  address?: string;
  produits?: IProduit[];
  commandes?: ICommande[];
  cooperatives?: ICooperative[];
}

export class Commerce implements ICommerce {
  constructor(
    public id?: number,
    public name?: string,
    public reviews?: number,
    public address?: string,
    public produits?: IProduit[],
    public commandes?: ICommande[],
    public cooperatives?: ICooperative[]
  ) {}
}
