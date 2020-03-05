import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'showPriceLevel'
})
export class ShowPriceLevelPipe implements PipeTransform {

  transform(priceLevel: number): string {
    switch (priceLevel) {
      case 1:
        return 'günstig';
      case 2:
        return 'mittel';
      case 3:
        return 'teuer';
      default:
        break;
    }
  }
}