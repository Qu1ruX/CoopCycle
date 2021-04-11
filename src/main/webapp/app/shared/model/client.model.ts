import { ICommande } from 'app/shared/model/commande.model';

export interface IClient {
  id?: number;
  firstname?: string;
  lastname?: string;
  mail?: string;
  phone?: string;
  address?: string;
  commandes?: ICommande[];
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public firstname?: string,
    public lastname?: string,
    public mail?: string,
    public phone?: string,
    public address?: string,
    public commandes?: ICommande[]
  ) {}
}
