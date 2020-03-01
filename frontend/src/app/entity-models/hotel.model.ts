import { Review } from './review.model';
import { DetailedContact } from './detailedContact.model';
import { City } from './city.model';

export interface Hotel {
    
    id?: number;
    name: string;
    detailedContact: DetailedContact;
    city: City;
    reviews?: Review[];
    avrgRating?: number;
    numOfReviews?: number;

    stars: number;
    breakfastIncl: boolean;
}