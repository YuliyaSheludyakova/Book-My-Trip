import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EntryService } from '../services/entry-service/entry.service';

@Component({
  selector: 'app-entries-list',
  templateUrl: './entries-list.component.html',
  styleUrls: ['./entries-list.component.css']
})
export class EntriesListComponent {

  city: string;
  entriesURL: string;
  entries: any[];
  nameOfEntry: string;  
  //Query params
  avrgRating: string;
  priceLevel: string;
  cuisine: string;
  breakfastIncl: string;
  stars: string;
  museumType: string; 

  isMouseOverSortIcon: boolean;
  isMouseOverFilterIcon: boolean;
  selectedIcon: string;
  isSideNavOpen: boolean;
  sortBy: string;
  direction: string;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private entryService: EntryService) {}

  ngOnInit() {    
    this.setCity();
    this.setEntriesURL();
    this.subscribeToQueryParams();
    this.renderEntriesList();
    this.setSortBy('name');
    this.setDirection('ASC');
  }

  setCity(): void {
    this.city = this.route.snapshot.params.city;
  }

  setEntriesURL(): void {
    this.entriesURL = this.route.snapshot.params.entries;
  }

  subscribeToQueryParams(): void {
    this.route.queryParams.subscribe(params => {
      this.nameOfEntry = params['name'];
      this.avrgRating = params['bewertung'];
      this.priceLevel = params['preisstufe'];
      this.cuisine = params['küche'];
      this.breakfastIncl = params['frühstück'];
      this.stars = params['sterne'];
      this.museumType = params['museumsart']
    });
  }

  renderEntriesList(): void {
    if (this.nameOfEntry) {
      this.showByName(this.nameOfEntry);
    } else if (this.isQueryParamsActivated()) {
      this.showByFilter();
    } else {
      this.getAll();
    }
  }

  isQueryParamsActivated(): boolean {
    return this.avrgRating !== undefined || this.priceLevel !== undefined ||
           this.cuisine !== undefined || this.breakfastIncl !== undefined ||
           this.stars !== undefined || this.museumType !== undefined;
  }

  setSortBy(sortBy: string): void {
    this.sortBy = sortBy;
  }

  setDirection(direction: string): void {
    this.direction = direction;
  }

  getAll(): void {
    this.entryService.getAll(this.city, this.entriesURL)
      .subscribe(e => {
        this.entries = e;
        this.sortArray()
      });
  }

  showByName(searchText: string): void {
    this.router.navigate(['book-my-trip', this.city, this.entriesURL], 
      {queryParams: {name: searchText}})
    this.entryService.showByName(this.city, this.entriesURL, searchText)
      .subscribe(e => {
        this.entries = e;
        this.sortArray()
      });
  }

  showByFilter(): void {
    this.router.navigate(['book-my-trip', this.city, this.entriesURL], 
      {queryParams: {
        küche: this.cuisine,
        bewertung: this.avrgRating,
        preisstufe: this.priceLevel,
        frühstück: this.breakfastIncl,
        sterne: this.stars,
        museumsart: this.museumType
      }
    });
    this.entryService.showByFilter(
      this.city, this.entriesURL, this.cuisine, this.avrgRating,
      this.priceLevel, this.breakfastIncl, this.stars, this.museumType)
        .subscribe(e => {
          this.entries = e;
          this.sortArray()
        });
  }

  sortArray(): void {
    this.isSideNavOpen = false;
    this.entries = this.entries.sort((e1,e2) => {
      if (this.isFirstEntryBeforeSecond(e1, e2, this.sortBy)) {
        return this.directionCheck(this.direction);
      }
      if (!this.isFirstEntryBeforeSecond(e1, e2, this.sortBy)) {
        return -this.directionCheck(this.direction);
      }
      return 0;
    });
  }

  isFirstEntryBeforeSecond(
    firstEntry: any, secondEntry: any, sortBy: string): boolean {
    return this.transformForSort(firstEntry, this.sortBy) >
           this.transformForSort(secondEntry, this.sortBy);
  }

  transformForSort(entry: any, sortBy: string) { // what is the return type?
    if (sortBy === 'name') {
      return entry[sortBy].toLowerCase();
    }
    return entry[sortBy];
  }

  directionCheck(direction: string): number {
    if (direction === 'DESC') {
      return -1
    }
    return 1;
  }
  
  isFilter(isMouseOverFilterIcon: boolean) {
    this.isMouseOverFilterIcon = isMouseOverFilterIcon;
    if (this.isMouseOverFilterIcon) {
      this.selectedIcon = 'filter';
    }
  }

  isSort(isMouseOverSortIcon: boolean) {
    this.isMouseOverSortIcon = isMouseOverSortIcon;
    if (this.isMouseOverSortIcon) {
      this.selectedIcon = 'sort';
    }
  }
}
