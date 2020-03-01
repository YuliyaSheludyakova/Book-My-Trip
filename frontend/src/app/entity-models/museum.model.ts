import { MuseumType } from './museumType.model';
import { Review } from './review.model';
import { DetailedContact } from './detailedContact.model';
import { City } from './city.model';

export interface Museum {
    
    id?: number;
    name: string;
    detailedContact: DetailedContact;
    city: City;
    reviews?: Review[];
    avrgRating?: number;
    numOfReviews?: number;

    museumTypes: MuseumType[];
    priceLevel: number;
}