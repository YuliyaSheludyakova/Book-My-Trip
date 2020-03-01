import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from '../entity-models/review.model';
import { EntryService } from '../services/entry-service/entry.service';

@Component({
  selector: 'app-entry-details',
  templateUrl: './entry-details.component.html',
  styleUrls: ['./entry-details.component.css']
})
export class EntryDetailsComponent implements OnInit {

  city: string;
  entries: string;
  entryType: string;
  id: number;
  entry: any;
  reviews: Review[];
  entriesURL: string;

  constructor(private entryService: EntryService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.city = this.route.snapshot.params.city;
    this.entries = this.route.snapshot.params.entries;
    this.id = this.route.snapshot.params.id;

    this.entryService.showById(this.city, this.entries, this.id)
      .subscribe(entry => this.entry = entry);

    this.setEntryType();
  }

  setEntryType() {
    if(this.entries === 'restaurants') {
      this.entryType = 'Restaurant';
    } else if(this.entries === 'hotels') {
      this.entryType = 'Hotel';
    } else if(this.entries === 'museen') {
      this.entryType = 'Museum';
    }
  }

  entryTypeMatches(entryType: string): boolean {
    return this.entries === entryType;
  }

  isNull(element: any): boolean {
    return element === null || element === undefined || element === '';
  }

  toReviewForm() {
    this.router.navigate(['book-my-trip', this.city, this.entries, this.id, 'bewertungen', 'neu']);
  }

  handleBackButton(){
    this.router.navigate(['book-my-trip', this.city, this.entries]);
  }
}
