import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  private url = 'http://localhost:8080/book-my-trip';
  constructor(private http: HttpClient) { }

  public getAll(): Observable<string[]> {
    return this.http.get<string[]>(this.url);
  }
}
