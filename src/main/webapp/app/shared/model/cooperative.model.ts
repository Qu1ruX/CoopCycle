import { ICommerce } from 'app/shared/model/commerce.model';

export interface ICooperative {
  id?: number;
  name?: string;
  address?: string;
  commerce?: ICommerce[];
}

export class Cooperative implements ICooperative {
  constructor(public id?: number, public name?: string, public address?: string, public commerce?: ICommerce[]) {}
}
