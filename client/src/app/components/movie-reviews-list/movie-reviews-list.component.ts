import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Review } from 'src/app/models/model';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit, OnDestroy {
  
  query!: string;
  reviewList: Review[] = [];
  reviewSub!: Subscription;

  constructor(
    private router: Router,
    private movieSvc: MovieService,
    private activatedRoute: ActivatedRoute) { }

  // subscribe to changes in list values
  ngOnInit(): void {
    this.reviewSub = this.movieSvc.onReview.subscribe(
      data => this.reviewList = data
    )
    
  }

  ngOnDestroy(): void {
      this.reviewSub.unsubscribe();
  }

  toComment(idx: number) {
    const title = this.reviewList[idx].title;
    console.info("commenting on: ", title);
    this.router.navigate(['/post-comment', title])
  }

  // route back to view 1 
  toSearchView() {
    this.router.navigate(['/search'])
  }


}
