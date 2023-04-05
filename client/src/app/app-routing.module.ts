import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovieReviewsListComponent } from './components/movie-reviews-list/movie-reviews-list.component';
import { PostCommentComponent } from './components/post-comment/post-comment.component';
import { SearchReviewComponent } from './components/search-review/search-review.component';

const routes: Routes = [
  {path: "", component: SearchReviewComponent},
  {path: "search", component: SearchReviewComponent},
  {path: "review-list", component: MovieReviewsListComponent},
  {path: "post-comment/:title", component: PostCommentComponent},
  {path: "**", redirectTo: "/", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
