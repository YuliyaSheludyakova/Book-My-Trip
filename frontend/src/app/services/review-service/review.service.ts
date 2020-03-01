import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from 'src/app/entity-models/review.model';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private url = 'http://localhost:8080/book-my-trip/';
  constructor(private http: HttpClient) {}

  public getAll(city: string, entries: string, entryId: number): Observable<Review[]>{
    return this.http.get<Review[]>(this.url + city + '/' + entries + '/' + entryId  + '/bewertungen');
  }

  public showById(city: string, entries: string, entryId: number, reviewId: number): Observable<Review> {
    return this.http.get<Review>(this.url + city + '/' + entries + '/' + entryId  + '/bewertungen/' + reviewId);
  }

  public add(city: string, entries: string, entryId: number, review: Review): Observable<Review>{
    return this.http.post<Review>(this.url + city + '/' + entries + '/' + entryId  + '/bewertungen', review);
  }

  public update(city: string, entries: string, entryId: number, reviewId: number, review: Review): Observable<Review> {
    return this.http.put<Review>(this.url + city + '/' + entries + '/' + entryId  + '/bewertungen/' + reviewId, review);
  }

  public delete(city: string, entries: string, entryId: number, reviewId: number): Observable<any> {
    return this.http.delete<any>(this.url + city + '/' + entries + '/' + entryId  + '/bewertungen/' + reviewId);
  }
}
