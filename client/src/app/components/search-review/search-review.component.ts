import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit{

  form!: FormGroup;

  constructor(
    private fb:FormBuilder,
    private router: Router
    ) { }

  ngOnInit(): void {
      this.form = this.fb.group({
        // TODO: Remove initial value
        "movie-name": this.fb.control<string>('spider', [Validators.required, Validators.minLength(2)])
      })
  }

  // Task 3
  searchMovie() {
    this.router.navigate(['/review-list'])
  }
}
