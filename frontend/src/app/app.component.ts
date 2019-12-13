import { Component, OnInit, ViewChild } from '@angular/core';
import { Restaurant } from './restaurant/restaurant';
import { RestaurantService } from './restaurant/restaurant.service';
import { NgbSlideEvent, NgbSlideEventSource, NgbCarousel } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private restaurantService: RestaurantService){}

  restaurants: Restaurant[] = [];

  newRestaurantName: string = "";
  newCity: string = "";
  newPriceLevel: number;
  newId: number = 0;
  restaurantToUpdate: number;
  restaurantToDelete: number;

  @ViewChild('ngcarousel', { static: true }) ngCarousel: NgbCarousel;

  ngOnInit() {
    this.restaurantService.getAllRestaurants().subscribe(restaurantsFromBackend =>{
      console.log("Restaurants von Backend empfangen");
      console.log("Ich möchte daten sehen")
      console.log(restaurantsFromBackend);
      this.restaurants = restaurantsFromBackend;
    });
  }

  slideActivate(ngbSlideEvent: NgbSlideEvent) {
    console.log(ngbSlideEvent.source);
    console.log(ngbSlideEvent.paused);
    console.log(NgbSlideEventSource.INDICATOR);
    console.log(NgbSlideEventSource.ARROW_LEFT);
    console.log(NgbSlideEventSource.ARROW_RIGHT);
  }

  // Move to specific slide
  navigateToSlide(item) {
    this.ngCarousel.select(item);
    console.log(item);
  }

  // Move to previous slide
  getToPrev() {
    this.ngCarousel.prev();
  }

  // Move to next slide
  goToNext() {
    this.ngCarousel.next();
  }

  // Pause slide
  stopCarousel() {
    this.ngCarousel.pause();
  }

  // Restart carousel
  restartCarousel() {
    this.ngCarousel.cycle();
  }

  createNewRestaurant(){
    let newRestaurant = new Restaurant();
    newRestaurant.name = this.newRestaurantName;
    newRestaurant.city = this.newCity;
    newRestaurant.priceLevel = this.newPriceLevel;
    newRestaurant.id = this.newId;
    this.restaurantService.addRestaurant(newRestaurant).subscribe(newAddedRestaurant => {
      console.log("Neues Restaurant wurde hinzugefügt");
      this.restaurants.push(newAddedRestaurant);
    });
  }

  updateRestaurant() {
    let restaurant = this.restaurants.find(restaurantToFilter => restaurantToFilter.id === this.restaurantToUpdate);
    if(restaurant){
      console.log(restaurant)
      restaurant.name = restaurant.name + 'ABC';
    }
    this.restaurantService.updateRestaurant(this.restaurantToUpdate, restaurant).subscribe(returnValue => {
      this.restaurants = this.restaurants.filter(restaurantsToFilter => restaurantsToFilter.id !== this.restaurantToUpdate);
      this.restaurants.push(restaurant);
      console.log('Update hat funktioniert');
    }, error => {
      console.log(error)
      console.log('Jetzt ist ein Fehler passiert');
    });
  }

    deleteRestaurant(){
      this.restaurantService.deleteRestaurant(this.restaurantToDelete).subscribe(ok => {
        console.log('Entfernen hat funktioniert');
        this.restaurants = this.restaurants.filter(restaurantsToFilter => restaurantsToFilter.id !== this.restaurantToDelete);
      }, error => {
        console.log('Es kam zu einem Fehler beim Entfernen');
      })
    }
  }
