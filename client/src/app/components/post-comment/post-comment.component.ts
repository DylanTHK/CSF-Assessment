import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Review, Comment } from 'src/app/models/model';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit {

  title!: string;
  commentForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private movieSvc: MovieService
    ) { }

  ngOnInit(): void {
      this.commentForm = this.fb.group({
        name: this.fb.control('', [Validators.required, Validators.minLength(3)]),
        rating: this.fb.control(3),
        comment: this.fb.control('', [Validators.required])
      })
      this.title = this.activatedRoute.snapshot.params['title'];
  }

  toResults() {
    this.router.navigate(['/review-list']);
    this.reset();
  }

  // Post request to SB
  postComment() {
    const comment = this.commentForm.value as Comment;
    comment.title = this.title;
    console.info("Form values: ", comment);
    this.movieSvc.postComment(comment);
    // reset form
    this.reset();
    // bring back to view 1
    this.router.navigate(['/review-list']);
    
  }

  reset() {
    this.commentForm = this.fb.group({
      name: this.fb.control('', [Validators.required, Validators.minLength(3)]),
      rating: this.fb.control(3),
      comment: this.fb.control('', [Validators.required])
    })
  }

}
