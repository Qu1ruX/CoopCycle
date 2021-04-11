import { ICommande } from 'app/shared/model/commande.model';
import { ILivreur } from 'app/shared/model/livreur.model';

export interface ICourse {
  id?: number;
  price?: number;
  distance?: number;
  commandes?: ICommande[];
  livreur?: ILivreur;
}

export class Course implements ICourse {
  constructor(
    public id?: number,
    public price?: number,
    public distance?: number,
    public commandes?: ICommande[],
    public livreur?: ILivreur
  ) {}
}
