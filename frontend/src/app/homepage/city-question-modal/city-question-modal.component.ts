import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-city-question-modal',
  templateUrl: './city-question-modal.component.html',
  styleUrls: ['./city-question-modal.component.css'],
  encapsulation: ViewEncapsulation.None,
  styles: [`
    .dark-modal .modal-content {
      background-color: #292b2c;
      color: white;
    }
    .dark-modal .close {
      color: white;
    }
    .light-blue-backdrop {
      background-color: #5cb3fd;
    }
  `]
})
export class CityQuestionModalComponent implements OnInit {

  city: string;
  chosenCity: string;

  constructor(private modalService: NgbModal,
              private router: Router) { }

  ngOnInit() { }

  openVerticallyCentered(content) {
    this.modalService.open(content, {centered: true});
  }

  setChosenCity(city: string) {
    this.chosenCity = city;
  }

  toEntriesList() {
    this.modalService.dismissAll('content');
    this.router.navigate(['book-my-trip', this.chosenCity, 'restaurants']);
  }
}
