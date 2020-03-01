import { Component, OnInit, ViewEncapsulation, Input, Output, EventEmitter } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-thank-you-modal-form-entry',
  templateUrl: './thank-you-modal-form-entry.component.html',
  styleUrls: ['./thank-you-modal-form-entry.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class ThankYouModalFormEntryComponent implements OnInit {

  @Input() valid: boolean;
  @Output() submit = new EventEmitter();

  constructor(private modalService: NgbModal) {}

  ngOnInit() {}

  openVerticallyCentered(content) {   
      this.modalService.open(content, { centered: true });
      setTimeout(() => 
        {this.modalService.dismissAll('content');
        this.submit.emit()
        }, 2500);      
  }
}
