import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search'
})
export class SearchCitiesPipe implements PipeTransform {
 
  transform(items: string[], searchText: string): string[] {
    if (!items) { return [] }

    if (!searchText) { return items }

    searchText = searchText.toLocaleLowerCase();

    return items.filter(item => 
      item.toLocaleLowerCase().startsWith(searchText));
  }
}