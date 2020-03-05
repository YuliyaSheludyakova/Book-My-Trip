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

  private isMuseumCard(): boolean {
    return this.origin === 'museumCard';
  }

  private isRestaurantCard(): boolean {
    return this.origin === 'restaurantCard';
  }

  private transfromForCard(): void {
    this.concatinated += this.types[0].type.toLowerCase();
    if (this.length > 1) {
      this.concatinated += ' & mehr';
    }
  }

  private transformForDetails(): void {
    for (let i = 0; i < this.length; i++) {
      if (this.isLastElement(i)) {
        this.concatinated += this.types[i].type.toLowerCase();
      } else {
        this.concatinated += this.types[i].type.toLowerCase() + ', ';
      }
    }
  }

  private isLastElement(elementIndex: number): boolean {
    return elementIndex === (this.length - 1);
  }
}
