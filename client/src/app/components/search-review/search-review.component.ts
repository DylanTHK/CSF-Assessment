import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit{

  form!: FormGroup;

  constructor(
    private fb:FormBuilder,
    private router: Router,
    private movieSvc: MovieService
    ) { }

  ngOnInit(): void {
      this.form = this.fb.group({
        "movie-name": this.fb.control<string>('', [Validators.required, Validators.minLength(2)])
      })
  }

  // Task 3
  searchMovie() {
    const name = this.form.get("movie-name")?.value;
    console.info("Getting reviews for name: ", name);
    this.movieSvc.getReviews(name);
    this.router.navigate(['/review-list', name])
  }
}
