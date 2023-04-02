import { Component } from '@angular/core';
import {Article} from "../article/article.model";

@Component({
  selector: 'app-reddit',
  templateUrl: './reddit.component.html',
  styleUrls: ['./reddit.component.css']
})
export class RedditComponent {
  articles: Article[];

  constructor() {
    this.articles = [];
  }
  addArticle(title: HTMLInputElement, link: HTMLInputElement): boolean {
    if(title.value == '' || link.value == '') {
      alert('Please fill in both fields');
    } else {
      this.articles.push(new Article(title.value, link.value, 0));
      console.log(`Adding article title: ${title.value} and link: ${link.value}`);
      title.value = '';
      link.value = '';
    }
    return false;
  }

  sortedArticles(): Article[] {
    return this.articles.sort((a: Article, b: Article) => b.votes - a.votes);
  }
}
