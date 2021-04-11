import { Moment } from 'moment';
import { IClient } from 'app/shared/model/client.model';
import { ICourse } from 'app/shared/model/course.model';
import { ICommerce } from 'app/shared/model/commerce.model';
import { IProduit } from 'app/shared/model/produit.model';

export interface ICommande {
  id?: number;
  date?: Moment;
  price?: number;
  client?: IClient;
  course?: ICourse;
  commerce?: ICommerce;
  produit?: IProduit;
}

export class Commande implements ICommande {
  constructor(
    public id?: number,
    public date?: Moment,
    public price?: number,
    public client?: IClient,
    public course?: ICourse,
    public commerce?: ICommerce,
    public produit?: IProduit
  ) {}
}
