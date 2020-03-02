import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'showTypes'
})
export class ShowTypesPipe implements PipeTransform {

  transform(types: any[], origin: string): string {
    let concatTypes: string = '';
    if (!types) {
      return '';
    }
    length = types.length;
    for(let i = 0; i < length; i++) {
      if (this.isMoreThanOneMuseumType(origin)) {
        concatTypes += types[i].type.toLowerCase() + ' & mehr';
        break;
      }
      if (this.isThirdCuisine(i, origin)) {
        concatTypes += ' & mehr';
        break;
      }
      if (this.isSecondCuisine(i, origin) || this.isLastElement(i, length)) {
        concatTypes += types[i].type.toLowerCase();
      } else {
        concatTypes += types[i].type.toLowerCase() + ', ';
      }
    }
    return concatTypes;
  }

  isMoreThanOneMuseumType(origin: string): boolean {
    return length > 1 && this.isMuseumsList(origin);
  }

  isMuseumsList(origin: string): boolean {
    return origin === 'museumsList';
  }

  isThirdCuisine(elementIndex: number, origin: string): boolean {
    return elementIndex === 2 && this.isRestaurantsList(origin);
  }

  isSecondCuisine(elementIndex: number, origin: string): boolean {
    return elementIndex === 1  && this.isRestaurantsList(origin);
  }

  isRestaurantsList(origin: string): boolean {
    return origin === 'restaurantsList';
  }

  isLastElement(elementIndex: number, arrayLength: number): boolean {
    return elementIndex === (arrayLength - 1);
  }
}
