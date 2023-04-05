import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Review } from 'src/app/models/model';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit {
  
  reviewList: Review[] = [];

  constructor(
    private router: Router,
    private movieSvc: MovieService) { }

  // TODO: subscribe to changes in list values
  ngOnInit(): void {

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
