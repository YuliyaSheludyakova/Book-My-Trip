import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { CityService } from 'src/app/services/city-service/city.service';
import { City } from 'src/app/entity-models/city.model';

@Component({
  selector: 'app-search-field-cities',
  templateUrl: './search-field-cities.component.html',
  styleUrls: ['./search-field-cities.component.css']
})
export class SearchFieldCitiesComponent implements OnInit {

  @Output() chosenCity = new EventEmitter<string>();
  cities: string[];
  searchText: string;

  constructor(private cityService: CityService,
              private router: Router) { }

  ngOnInit() {
    this.cityService.getAll()
      .subscribe(citiesList => this.cities = citiesList);        
  }

  chooseCity(city: string) {
    this.chosenCity.emit(city);
  }
}
