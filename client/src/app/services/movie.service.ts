import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Subject } from 'rxjs';
import { Review } from '../models/model';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  // reviews: Review[] = [];

  onReview = new Subject<Review[]>();

  constructor(private httpClient: HttpClient) { }

  // TODO: Task 3 Make API Call to Springboot (Link to search button)
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

}
