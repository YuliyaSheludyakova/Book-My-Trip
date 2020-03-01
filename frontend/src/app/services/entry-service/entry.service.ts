import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EntryService {

  private url = 'http://localhost:8080/book-my-trip/';
  constructor(private http: HttpClient) {}

  public getAll(city: string, entries: string): Observable<any[]>{
    return this.http.get<any[]>(this.url + city + '/' + entries);
  }

  public showById(city: string, entries: string, id: number): Observable<any> {
    return this.http.get<any>(this.url + city + '/' + entries + '/' + id);
  }

  public showByName(city: string, entries:string, name: string): Observable<any[]> {
    return this.http.get<any[]>(this.url + city + '/' + entries + '/suchen?name=' + name);
  }

  public showByFilter(city: string,
                      entries: string,
                      cuisine: string,
                      rating: string,
                      priceLevel: string,
                      breakfastIncl: string,
                      stars: string,
                      museumType: string): Observable<any[]> {

    let params = new HttpParams();
    if (cuisine && entries === 'restaurants') {
      params = params.append('cuisine', cuisine);
    }
    if (museumType && entries === 'museen') {
      params = params.append('museumType', museumType);
    }
    if (breakfastIncl && entries === 'hotels') {
      params = params.append('breakfastIncl', breakfastIncl);
    }
    if (stars && entries === 'hotels') {
      params = params.append('stars', stars);
    }
    if (priceLevel && (entries === 'museen' || entries === 'restaurants')) {
      params = params.append('priceLevel', priceLevel);
    }
    if (rating) {
      params = params.append('rating', rating);
    }    
    return this.http.get<any[]>(this.url + city + '/' + entries + '/filter', {params: params});
  }

  public add(city: string, entries: string, entry: any): Observable<any>{
    return this.http.post<any>(this.url + city + '/' + entries, entry);
  }

  public update(city: string, entries: string, id: number, entry: any): Observable<any> {
    return this.http.put<any>(this.url + city + '/' + entries + '/' + id, entry);
  }

  public delete(city: string, entries: string, id: number): Observable<any> {
    return this.http.delete<any>(this.url + city + '/' + entries + '/' + id);
  }
}
