import { ICourse } from 'app/shared/model/course.model';

export interface ILivreur {
  id?: number;
  firstname?: string;
  lastname?: string;
  mail?: string;
  phone?: string;
  reviews?: number;
  courses?: ICourse[];
}

export class Livreur implements ILivreur {
  constructor(
    public id?: number,
    public firstname?: string,
    public lastname?: string,
    public mail?: string,
    public phone?: string,
    public reviews?: number,
    public courses?: ICourse[]
  ) {}
}
