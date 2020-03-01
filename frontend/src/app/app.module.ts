import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HomepageComponent } from './homepage/homepage.component';
import { FooterComponent } from './footer/footer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { FormReviewComponent } from './form-review/form-review.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule  } from "@angular/forms";
import { EntryDetailsComponent } from './entry-details/entry-details.component';

import { AppRoutingModule } from './app-routing.module';
import { CityQuestionModalComponent } from './homepage/city-question-modal/city-question-modal.component';
import { CarouselCardsComponent } from './homepage/carousel-cards/carousel-cards.component';
import { StarRatingComponent } from './form-review/star-rating/star-rating.component';
import { CommentComponent } from './entry-details/comment/comment.component';
import { ThankYouModalComponent } from './form-review/thank-you-modal/thank-you-modal.component';
import { FormEntryComponent } from './form-entry/form-entry.component';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSelectModule } from '@angular/material/select';
import { ButtonNewEntryComponent } from './form-entry/button-new-entry/button-new-entry.component';
import { SearchFieldCitiesComponent } from './homepage/city-question-modal/search-field-cities/search-field-cities.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { SearchCitiesPipe } from './homepage/city-question-modal/search-field-cities/search-cities-pipe/search-cities.pipe';
//import { ShowAddressPipe } from './entry-details/show-address-pipe/show-address.pipe';
import { EntriesListComponent } from './entries-list/entries-list.component';
import { SidebarComponent } from './entries-list/sidebar/sidebar.component';
import { CardComponent } from './entries-list/card/card.component';
import { SearchFieldComponent } from './entries-list/search-field/search-field.component';
import { ShowTypesPipe } from './entries-list/card/show-types-pipe/show-types.pipe';
import { ModalMoreOptionsComponent } from './entries-list/sidebar/modal-more-options/modal-more-options.component';
import { ShowInclusivePipe } from './entries-list/card/show-inclusive-pipe/show-inclusive.pipe';
import { ShowPriceLevelPipe } from './entries-list/card/show-price-level-pipe/show-price-level.pipe';
import { DeleteModalComponent } from './entries-list/delete-modal/delete-modal.component';
import { ThankYouModalFormEntryComponent } from './form-entry/thank-you-modal-form-entry/thank-you-modal-form-entry.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    HomepageComponent,
    FooterComponent,
    CardComponent,
    CarouselCardsComponent,
    CityQuestionModalComponent,
    SearchFieldCitiesComponent,
    SearchCitiesPipe,
    EntriesListComponent,
    FormReviewComponent,
    StarRatingComponent,
    EntryDetailsComponent,
    CommentComponent,
    ThankYouModalComponent,
    SearchFieldComponent,
    FormEntryComponent,
    ShowTypesPipe,
    ButtonNewEntryComponent,
    ModalMoreOptionsComponent,
    ShowInclusivePipe,
    //ShowAddressPipe,
    ThankYouModalFormEntryComponent,
    ShowPriceLevelPipe,
    DeleteModalComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule, NgbModule, BrowserAnimationsModule,
    LayoutModule, MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule,
    MatListModule, MatGridListModule, MatCardModule, MatMenuModule, MatFormFieldModule,
    ReactiveFormsModule, AppRoutingModule, ReactiveFormsModule, MatInputModule, MatRadioModule,
    MatAutocompleteModule, MatSelectModule, MatCheckboxModule],
  providers: [SearchCitiesPipe],
  bootstrap: [AppComponent]
})
export class AppModule {}
