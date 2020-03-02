import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'showTypes'
})
export class ShowTypesPipe implements PipeTransform {
  types: any[];
  origin: string;
  length: number;
  concatinated: string;
  
  transform(types: any[], origin: string): string {
    this.types = types;
    this.origin = origin;
    if (!types) {
      return '';
    }
    this.concatinated = '';
    this.length = types.length;    
    if (this.isMuseumCard() || this.isRestaurantCard()) {
      this.transfromForCard();
    } else {
      this.transformForDetails();
    }
    return this.concatinated;
  }

  isMuseumCard(): boolean {
    return this.origin === 'museumCard';
  }

  isRestaurantCard(): boolean {
    return this.origin === 'restaurantCard';
  }

  transfromForCard() {
    this.concatinated += this.types[0].type.toLowerCase();
    if (this.length > 1) {
      this.concatinated += ' & mehr';
    }
  }

  transformForDetails() {
    for (let i = 0; i < this.length; i++) {
      if (this.isLastElement(i)) {
        this.concatinated += this.types[i].type.toLowerCase();
      } else {
        this.concatinated += this.types[i].type.toLowerCase() + ', ';
      }
    }
  }

  isLastElement(elementIndex: number): boolean {
    return elementIndex === (this.length - 1);
  }
}
