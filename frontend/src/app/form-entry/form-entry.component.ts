import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { MatCheckbox } from '@angular/material/checkbox';
import { Hotel } from '../entity-models/hotel.model';
import { Restaurant } from '../entity-models/restaurant.model';
import { Museum } from '../entity-models/museum.model';
import { CityService } from '../services/city-service/city.service';
import { CuisineService } from '../services/cuisine-service/cuisine.service';
import { MuseumTypeService } from '../services/museum-type-service/museum-type.service';
import { EntryService } from '../services/entry-service/entry.service';

@Component({
  selector: 'app-form-entry',
  templateUrl: './form-entry.component.html',
  styleUrls: ['./form-entry.component.css']
})
export class FormEntryComponent implements OnInit {

  cityURL: string;
  hasCityURL: boolean;
  entriesURL: string;
  hasEntriesURL: boolean;
  entryId: number;
  hasEntryId: boolean;

  entry: any;

  cities: string[] = [];
  types: string[];
  typesChecked: string[] = [];

  myControl = new FormControl();
  filteredOptions: Observable<string[]>;

  form: FormGroup;

  hasUnitNumber = false;

  constructor(private fb: FormBuilder,
              private cityService: CityService,
              private cuisineService: CuisineService,
              private museumTypeService: MuseumTypeService,
              private entryService: EntryService,
              private router: Router,
              private route: ActivatedRoute) {}

  ngOnInit() {
    this.cityURL = this.route.snapshot.params.city;
    this.hasCityURL = this.cityURL !== undefined;
    this.entriesURL = this.route.snapshot.params.entries;
    this.hasEntriesURL = this.entriesURL !== undefined;
    this.entryId = this.route.snapshot.params.id;
    this.hasEntryId = this.entryId !== undefined;

    if (this.entryId) {
      this.entryService.showById(this.cityURL, this.entriesURL, this.entryId)
      .subscribe(entry => {
        this.entry = entry; 

        if (this.entriesURL === 'restaurants') {
          this.typesChecked = this.entry.cuisines.map(cuisine => cuisine.type);
        } else if (this.entriesURL === 'museen') {
          this.typesChecked = this.entry.museumTypes.map(museumType => museumType.type);
        }

        this.form = this.fb.group({
          city: [this.cityURL],
          entryType: [this.entriesURL],
          company: [this.entry.name],
          streetName: [this.entry.detailedContact.streetName],
          houseNumber: [this.entry.detailedContact.houseNumber],
          postalCode: [this.entry.detailedContact.postalCode],
          phoneNumber: [this.entry.detailedContact.phoneNumber],
          priceLevel: [this.entry.priceLevel],
          stars: [this.entry.stars],
          breakfastIncl: [this.entry.breakfastIncl]
        })
      });
    }
    
    this.cityService.getAll().subscribe(cities => this.cities = cities);

    this.form = this.fb.group({
      city: [this.cityURL],
      entryType: [this.entriesURL],
      company: [''],
      streetName: [''],
      houseNumber: [''],
      postalCode: ['', [Validators.compose([
        Validators.required, Validators.minLength(5), Validators.maxLength(5)])
      ]],
      phoneNumber: [],
      priceLevel: [''],
      stars: [''],
      breakfastIncl: ['']
    });
    
    this.getTypes();    

    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.cities.filter(city => city.toLowerCase().startsWith(filterValue));
  }

  getTypes() {
    if (this.entriesURL === "restaurants") {
      this.cuisineService.getAll().subscribe(cuisines => this.types = cuisines);
    } else if (this.entriesURL === "museen") {
      this.museumTypeService.getAll().subscribe(museumTypes => this.types = museumTypes);
    }
  }

  onChange(checkbox: MatCheckbox, type: string) {
    if (checkbox.checked) {
      this.typesChecked.push(type);
    } else {
      this.typesChecked.splice(
        this.typesChecked.indexOf(type), 1);
    }
  }

  onCancel() {
    if (this.entriesURL) {
      this.router.navigate(['book-my-trip', this.cityURL, this.entriesURL])
    } else {
      this.router.navigate(['book-my-trip'])
    }
  }

  isTypeChecked(type: string) {
    let foundType: any;
    if (this.entryId) {
      if (this.entriesURL === 'museen') {
        foundType = this.entry.museumTypes.find(m => m.type === type);
      } else {
        foundType = this.entry.cuisines.find(c => c.type === type);
      }      
    }    
    if (foundType) {
      return true;   
    }
    return false;
  }

  transformTypesIntoObjects(): any[] {
    let types: any[] = [];
    for (let i = 0; i < this.typesChecked.length; i++) {
      types.push({type: this.typesChecked[i]});
    }
    return types;
  }

  createHotel(): Hotel {
    return {
      name: this.form.value.company,
      stars: this.form.value.stars,
      breakfastIncl: this.form.value.breakfastIncl,
      city: {
        name: this.form.value.city
      },
      detailedContact: {
        streetName: this.form.value.streetName,
        houseNumber: this.form.value.houseNumber,
        postalCode: this.form.value.postalCode,
        phoneNumber: this.form.value.phoneNumber,
      }
    }
  }

  createRestaurant(): Restaurant {
    return {
      name: this.form.value.company,
      priceLevel: this.form.value.priceLevel,
      cuisines: this.transformTypesIntoObjects(),
      city: {
        name: this.form.value.city
      },
      detailedContact: {
        streetName: this.form.value.streetName,
        houseNumber: this.form.value.houseNumber,
        postalCode: this.form.value.postalCode,
        phoneNumber: this.form.value.phoneNumber,
      }
    }
  }

  createMuseum(): Museum {
    return {
      name: this.form.value.company,
      priceLevel: this.form.value.priceLevel,
      museumTypes: this.transformTypesIntoObjects(),
      city: {
        name: this.form.value.city
      },
      detailedContact: {
        streetName: this.form.value.streetName,
        houseNumber: this.form.value.houseNumber,
        postalCode: this.form.value.postalCode,
        phoneNumber: this.form.value.phoneNumber,
      }
    }
  }

  isFormValid(): boolean {
    let hasTypes: boolean = true;
    if (this.entriesURL !== 'hotels') {
      hasTypes = this.typesChecked.length !== 0
    }
    return this.form.valid && hasTypes;
  }

  onSubmit() {
    let entry: any;
    switch(this.entriesURL) {
      case 'restaurants':
        entry = this.createRestaurant();
        break;
      case 'hotels':
        entry = this.createHotel();
        break;
      case 'museen':
        entry = this.createMuseum();
        break;
      default:
        break;
    }

    if (this.isFormValid()) {      
      if (this.entryId) {
        this.entryService.update(this.cityURL, this.entriesURL, this.entryId, entry)
          .subscribe(
            entry =>  this.router.navigate(['book-my-trip', this.cityURL, this.entriesURL])
          );
      } else {
        this.entryService.add(this.cityURL, this.entriesURL, entry)
          .subscribe(entry => this.router.navigate(['book-my-trip', this.cityURL, this.entriesURL]));
      }      
    }    
  }
}