import { Variant } from './add-variant/variant';

export class Shoes {
  brand: string = null;
  model: string = null;
  price: number = null;
  gender: string = null;
  description: string = null;
  shoeType: string = null;
  variants: Array<Variant> = [];
}
