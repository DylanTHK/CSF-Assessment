import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Review } from 'src/app/models/model';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit, OnDestroy {
  
  reviewList: Review[] = [];
  reviewSub!: Subscription;

  constructor(
    private router: Router,
    private movieSvc: MovieService) { }

  // subscribe to changes in list values
  ngOnInit(): void {
    this.reviewSub = this.movieSvc.onReview.subscribe(
      data => this.reviewList = data
    )
  }

  ngOnDestroy(): void {
      this.reviewSub.unsubscribe();
  }

  // TODO: send data(title) to comments page
  toComment(idx: number) {

    this.router.navigate(['/post-comment'])
  }

  // route back to view 1 
  toSearchView() {
    this.router.navigate(['/search'])
  }


}
