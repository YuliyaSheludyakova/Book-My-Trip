import { Cuisine } from './cuisine.model';
import { Review } from './review.model';
import { DetailedContact } from './detailedContact.model';
import { City } from './city.model';

export interface Restaurant {
    
    id?: number;
    name: string;
    detailedContact: DetailedContact;
    city: City;
    reviews?: Review[];
    avrgRating?: number;
    numOfReviews?: number;

    cuisines: Cuisine[];
    priceLevel: number;
}