import { Cuisine } from './cuisine';

export interface Restaurant {
    id: number;
    city: string;
    name: string;
    priceLevel: number;
    cuisines: Cuisine[];
    avrgRating: number;
    numOfReviews: number;
}