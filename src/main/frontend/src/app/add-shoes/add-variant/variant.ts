export class Variant {
  id?: number = null;
  image: string = null;
  shankColor: string = null;
  soleColor: string = null;
  sizedShoes: Array<{ size: number, quantity: number }> = [];
}
