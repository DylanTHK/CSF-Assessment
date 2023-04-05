export interface Review {
    title: string;
	rating: number;
	headline: string;
	summary: string;
	reviewURL: string;
	image: string;
	commentCount: number;

	// not used?
	byline: string;
}

export interface Comment {
	title: string;
	name: string;
	rating: number;
	comment: string;
}