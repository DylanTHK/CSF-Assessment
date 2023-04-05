import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Review } from 'src/app/models/model';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit {

  title!: string;
  commentForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private router: Router) { }

  ngOnInit(): void {
      this.commentForm = this.fb.group({
        name: this.fb.control('', [Validators.required, Validators.minLength(3)]),
        rating: this.fb.control(3),
        comment: this.fb.control('', [Validators.required])
      })
  }

  // TODO: go back to results + reset form
  toResults() {
    this.router.navigate(['/review-list']);
  }

  // Post request to SB
  postComment() {
    // TODO: post request to SB
    // TODO: reset form
    // bring back to view 1
    this.router.navigate(['/review-list']);
  }

}
