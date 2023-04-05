import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit{

  form!: FormGroup;

  constructor(private fb:FormBuilder) { }

  ngOnInit(): void {
      this.form = this.fb.group({
        "movie-name": this.fb.control<string>('', [Validators.required, Validators.minLength(2)])
      })
  }

  // Task 2
  searchMovie() {

  }
}
