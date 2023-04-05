import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Subject } from 'rxjs';
import { Review, Comment } from '../models/model';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  // reviews: Review[] = [];
  onReview = new Subject<Review[]>();

  constructor(private httpClient: HttpClient) { }

  // Task 3 Make API Call to Springboot (Link to search button)
  getReviews(movieName: string) {
    const url = "/api/search";
    const params = new HttpParams()
      .set("query", movieName);

    firstValueFrom(this.httpClient.get<Review[]>(url, {params}))
      .then(data => {
        const r = data as Review[];
        console.info("Review array: ", r);
        return r;
      }).then(data => 
        this.onReview.next(data));
  }

  postComment(comment: Comment) {
    const url = "/api/comment";
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded');

    const body = `title=${comment.title}&name=${comment.name}&rating=${comment.rating}&comment=${comment.comment}`

    firstValueFrom(
      this.httpClient.post<Comment>(url, body, {headers})
    ).then(response => {
      console.info("Response from post:", response);
    })
  }
}
