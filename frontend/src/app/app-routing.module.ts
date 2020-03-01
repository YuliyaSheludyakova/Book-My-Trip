import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { EntryDetailsComponent } from './entry-details/entry-details.component';
import { FormReviewComponent } from './form-review/form-review.component';
import { FormEntryComponent } from './form-entry/form-entry.component';
import { EntriesListComponent } from './entries-list/entries-list.component';

const routes: Routes = [
  {path: "", redirectTo: "/book-my-trip", pathMatch: "full"},
  {path: "book-my-trip", component: HomepageComponent},
  {path: "book-my-trip/neu", component: FormEntryComponent},
  {path: "book-my-trip/:city/:entries", component: EntriesListComponent},
  {path: "book-my-trip/:city/:entries/neu", component: FormEntryComponent},
  {path: "book-my-trip/:city/:entries/:id/editieren", component: FormEntryComponent},
  {path: "book-my-trip/:city/:entries/:id", component: EntryDetailsComponent},
  {path: "book-my-trip/:city/:entries/:id/bewertungen/neu", component: FormReviewComponent},
  {path: "book-my-trip/:city/:entries/:id/bewertungen/:reviewId/editieren", component: FormReviewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
